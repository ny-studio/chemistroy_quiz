package com.example.Chemistryquiz;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class QuizApp extends AppCompatActivity implements Serializable {
    ImageView questionImage;
    private TextView countLabel;
    private TextView question;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;//解答ﾎﾞﾀﾝ
    private String rightAnswer, explanation;//答えと解説
    private int rightAnswerCount = 0;
    private Database helper;
    Answersound answersound;
    boolean check;
    private int Count = 1;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> setArray = new ArrayList<>();
    ArrayList<String> mainArray = new ArrayList<>();
    ArrayList<Button> AnswerBtn;
    Quiz_Theme q;//intentでテーマをもってくる

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizapp);
        helper = new Database(getApplicationContext());
        answersound = new Answersound(getApplicationContext());
        countLabel = findViewById(R.id.countLabel);
        question = findViewById(R.id.question);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        AnswerBtn = new ArrayList<>(Arrays.asList(answerBtn1, answerBtn2, answerBtn3, answerBtn4));

        //広告を表示
        set_ad();
        //前のクラスから値を受け取る
        q = (Quiz_Theme) getIntent().getSerializableExtra("QUIZ_SUB_THEME");
        // クイズデータquizDataからクイズ出題用のquizArrayを作成する
        quizset(q.getquizdata());
        showNextQuiz();
    }

    public void showNextQuiz() {
        // クイズカウントラベルを更新
        countLabel.setText("Q" + Count);
        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        // randomNumを使って、quizArrayからクイズを一つ取り出す
        ArrayList<String> quiz = quizArray.get(randomNum);

        mainArray = setArray.get(randomNum);
        // 問題文または画像を表示
        questionImage = findViewById(R.id.questionImage);
        questionImage.setImageResource(0);
        int a = getResources().getIdentifier(quiz.get(0), "drawable", getPackageName());
        if (a != 0) {
            questionImage.setImageResource(a);
            question.setText("");
        } else {
            question.setText(quiz.get(0));
        }
        // 解説を
        explanation = quiz.get(1);
        // 正解をrightAnswerにセット
        rightAnswer = quiz.get(2);

        // クイズ配列から問題文を削除
        quiz.remove(0);
        // クイズ配列から解説文を削除
        quiz.remove(0);

        Collections.shuffle(quiz);

        for (int i = 0; i < 4; i++) {
            AnswerBtn.get(i).setVisibility(View.VISIBLE);
            AnswerBtn.get(i).setText(quiz.get(i)); // 回答ボタンに正解と選択肢３つを表示
            if (quiz.get(i).equals("no")) {
                AnswerBtn.get(i).setVisibility(View.GONE);
            }
        }

        // このクイズをquizArrayから削除
        quizArray.remove(randomNum);
        setArray.remove(randomNum);
    }

    public void checkAnswer(View view) {
        //出題数
        SharedPreferences preferences = getSharedPreferences("num", MODE_PRIVATE);
        final int QUIZ_COUNT = preferences.getInt("num", 10);
        //どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String alertTitle;

        //databaseの用意
        ContentValues values = new ContentValues();
        SQLiteDatabase db = helper.getWritableDatabase();
        //効果音の確認
        SharedPreferences prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        boolean bool = prefs.getBoolean("boolean", check);

        values.put("qs", mainArray.get(0));
        values.put("explanation", mainArray.get(1));
        values.put("quiz1", mainArray.get(2));
        values.put("quiz2", mainArray.get(3));
        values.put("quiz3", mainArray.get(4));
        values.put("quiz4", mainArray.get(5));

        String table_name = q.gettable_c1();
        String right_table_name = q.gettable_c2();

        //間違えた問題の残り数を確認
        //ResultActivityにおくる
        final Cursor wqn = db.query(table_name,
                new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);

        //正誤確認
        if (btnText.equals(rightAnswer)) {
            rightAnswerCount++;
            if (bool) answersound.正解();
            alertTitle = "正解!";
            //rigth_tableを用意
            Cursor c = db.query(right_table_name,
                    new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                    null, null, null, null, null);
            //被り確認
            boolean judge = false;
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                if (mainArray.get(0).equals(c.getString(0))) {
                    judge = true;
                    break;
                }
                c.moveToNext();
            }
            c.close();
            //rightqsに追加
            if (judge) {
                //被りあり
            } else {
                //被りなし
                db.insert(right_table_name, null, values);
            }
            //間違えた問題から削除
            db.delete(table_name, "qs=?", new String[]{mainArray.get(0)});
        } else {
            if (bool) answersound.不正解();
            alertTitle = "不正解...";
            //quizdb(間違えた問題)へ追加
            Cursor c = db.query(table_name,
                    new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                    null, null, null, null, null);
            //被り確認
            boolean judge = false;
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                if (mainArray.get(0).equals(c.getString(0))) {
                    judge = true;
                    break;
                }
                c.moveToNext();
            }
            c.close();

            if (judge) {
                //被りあり
            } else {
                //被りなし
                db.insert(table_name, null, values);
            }
            //もし正解にdataがあれば削除
            db.delete(right_table_name, "qs=?", new String[]{mainArray.get(0)});
        }
        // ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え : " + rightAnswer + "\r\n解説 : " + explanation);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Count == QUIZ_COUNT || quizArray.size() == 0) {
                    // 結果画面へ移動
                    result_intent(q, rightAnswerCount, Count, wqn);
                } else {
                    //続く
                    Count++;
                    showNextQuiz();
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (Count == QUIZ_COUNT || quizArray.size() == 0) {
                    // 結果画面へ移動
                    result_intent(q, rightAnswerCount, Count, wqn);
                } else {
                    //続く
                    Count++;
                    showNextQuiz();
                }
            }
        });
        builder.show();
    }

    //回答中に戻るボタンを押したときの処理
    public void onBackPressed() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // ダイアログの設定
        alertDialog.setTitle("終了");      //タイトル設定
        alertDialog.setMessage("問題を終了しますか？");  //内容(メッセージ)設定
        //ResultActivityにおくる
        SQLiteDatabase db = helper.getWritableDatabase();

        String table_name = q.gettable_c1();
        final Cursor wqn = db.query(
                table_name, new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);
        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理
                result_intent(q, rightAnswerCount, Count - 1, wqn);
            }
        });
        // NG(否定的な)ボタンの設定
        alertDialog.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // NGボタン押下時の処理
                alertDialog.setCancelable(false);
            }
        });

        // ダイアログの作成と描画
        alertDialog.show();
    }

    public void quizset(String[][] quiz) {
        for (int i = 0; i < quiz.length; i++) {
            // 新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();
            ArrayList<String> mpArray = new ArrayList<>();
            Collections.addAll(tmpArray, quiz[i][0], quiz[i][1],
                    quiz[i][2], quiz[i][3], quiz[i][4], quiz[i][5]);
            Collections.addAll(mpArray, quiz[i][0], quiz[i][1],
                    quiz[i][2], quiz[i][3], quiz[i][4], quiz[i][5]);
            // tmpArrayをquizArrayに追加する
            quizArray.add(tmpArray);
            setArray.add(mpArray);
        }
    }

    public void result_intent(Quiz_Theme q, int rightAnswerCount, int quizCount, Cursor wqn) {
        Intent intent = new Intent(getApplication(), ResultActivity.class);
        intent.putExtra("QUIZ_SUB_THEME", q);
        intent.putExtra("KIND", "normal");
        intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
        intent.putExtra("QUIZ_COUNT", quizCount);
        intent.putExtra("wrong_quiz_num", wqn.getCount());
        startActivity(intent);
        finish();
    }

    public void set_ad() {
        MobileAds.initialize(this, getString(R.string.ads_AppId));
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
