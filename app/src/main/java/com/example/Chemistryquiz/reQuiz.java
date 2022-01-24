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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class reQuiz extends AppCompatActivity {
    ImageView questionImage;
    private TextView countLabel;
    private TextView question;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    private String rightAnswer, explanation;
    private int rightAnswerCount = 0;
    private Database helper;
    boolean check;
    Answersound answersound;
    private int quizCount = 1;
    Quiz_Theme q;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    ArrayList<ArrayList<String>> setArray = new ArrayList<>();
    ArrayList<String> mainArray = new ArrayList<>();
    ArrayList<Button> AnswerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requiz);
        helper = new Database(getApplicationContext());
        answersound = new Answersound(getApplicationContext());
        countLabel = findViewById(R.id.repeatcountLabel);
        question = findViewById(R.id.requestion);
        answerBtn1 = findViewById(R.id.repeatBtn1);
        answerBtn2 = findViewById(R.id.repeatBtn2);
        answerBtn3 = findViewById(R.id.repeatBtn3);
        answerBtn4 = findViewById(R.id.repeatBtn4);

        AnswerBtn = new ArrayList<>(Arrays.asList(answerBtn1, answerBtn2, answerBtn3, answerBtn4));

        //広告設定
        MobileAds.initialize(this);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        //前のクラスから値を受け取る
        q = (Quiz_Theme) getIntent().getSerializableExtra("QUIZ_SUB_THEME");
        readData(q.gettable_c1());
        showNextQuiz();
    }

    public void readData(String table_name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                table_name,
                new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            // 新しいArrayListを準備
            ArrayList<String> pArray1 = new ArrayList<>();
            ArrayList<String> pArray2 = new ArrayList<>();
            Collections.addAll(pArray1, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            Collections.addAll(pArray2, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));    //ArrayをquizArrayに追加する
            quizArray.add(pArray1);
            setArray.add(pArray2);
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void showNextQuiz() {
        // クイズカウントラベルを更新
        questionImage = findViewById(R.id.questionImage);
        questionImage.setImageResource(0);
        countLabel.setText("Q" + quizCount);
        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        // randomNumを使って、quizArrayからクイズを一つ取り出す
        ArrayList<String> quiz = quizArray.get(randomNum);
        mainArray = setArray.get(randomNum);

        // 問題文を表示

        int a = getResources().getIdentifier(quiz.get(0), "drawable", getPackageName());
        if (a != 0) {
            questionImage.setImageResource(a);
            question.setText("");
        } else {
            question.setText(quiz.get(0));
        }
        // 正解をrightAnswerにセット
        explanation = quiz.get(5);
        rightAnswer = quiz.get(1);
        // クイズ配列から解説文を削除
        quiz.remove(5);
        // クイズ配列から問題文を削除
        quiz.remove(0);
        Collections.shuffle(quiz);

        for (int i = 0; i < 4; i++) {
            AnswerBtn.get(i).setVisibility(View.VISIBLE);
            AnswerBtn.get(i).setText(quiz.get(i)); // 回答ボタンに正解と選択肢３つを表示
            if (quiz.get(i).equals("no")) {
                AnswerBtn.get(i).setVisibility(View.GONE);//非表示
            }
        }
        // このクイズをquizArrayから削除
        quizArray.remove(randomNum);
        setArray.remove(randomNum);
    }

    public void checkAnswer(View view) {
        SharedPreferences preferences = getSharedPreferences("num", MODE_PRIVATE);
        final int QUIZ_COUNT = preferences.getInt("num", 10);
        // どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String alertTitle;
        ContentValues values = new ContentValues();
        SharedPreferences prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        boolean bool = prefs.getBoolean("boolean", check);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (btnText.equals(rightAnswer)) {
            rightAnswerCount++;
            alertTitle = "正解!";
            values.put("qs", mainArray.get(0));
            values.put("explanation", mainArray.get(5));
            values.put("quiz1", mainArray.get(1));
            values.put("quiz2", mainArray.get(2));
            values.put("quiz3", mainArray.get(3));
            values.put("quiz4", mainArray.get(4));
            //ONの時音鳴る
            if (bool) {
                answersound.正解();
            } else {
            }

            Cursor c = db.query(q.gettable_c2(),
                    new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                    null, null, null, null, null);
            //データの削除
            db.delete(q.gettable_c1(), "qs=?", new String[]{mainArray.get(0)});
            //rightqsに追加
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
                db.insert(q.gettable_c2(), null, values);
            }
        } else {
            alertTitle = "不正解...";
            if (bool) {
                answersound.不正解();
            } else {
            }
        }
        // wqn宣言
        final Cursor wqn = db.query(q.gettable_c1(),
                new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);
        // ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え : " + rightAnswer + "\r\n解説 : " + explanation);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT || quizArray.size() == 0) {
                    // 結果画面へ移動
                    Intent intent = new Intent(getApplication(), ResultActivity.class);
                    intent.putExtra("QUIZ_SUB_THEME", q);
                    intent.putExtra("KIND", "repeat");
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    intent.putExtra("QUIZ_COUNT", quizCount);
                    intent.putExtra("wrong_quiz_num", wqn.getCount());
                    startActivity(intent);
                    finish();
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (quizCount == QUIZ_COUNT || quizArray.size() == 0) {
                    // 結果画面へ移動
                    Intent intent = new Intent(getApplication(), ResultActivity.class);
                    intent.putExtra("QUIZ_SUB_THEME", q);
                    intent.putExtra("KIND", "repeat");
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    intent.putExtra("QUIZ_COUNT", quizCount);
                    intent.putExtra("wrong_quiz_num", wqn.getCount());
                    startActivity(intent);
                    finish();
                } else {
                    //続く
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.show();
    }

    public void onBackPressed() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // ダイアログの設定
        alertDialog.setTitle("中断");      //タイトル設定
        alertDialog.setMessage("問題を終了しますか？");  //内容(メッセージ)設定
        //table宣言
        SQLiteDatabase db = helper.getWritableDatabase();

        final Cursor wqn = db.query(
                q.gettable_c1(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理
                Intent intent = new Intent(getApplication(), ResultActivity.class);
                intent.putExtra("QUIZ_SUB_THEME", q);
                intent.putExtra("KIND", "repeat");
                intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                intent.putExtra("QUIZ_COUNT", quizCount - 1);
                intent.putExtra("wrong_quiz_num", wqn.getCount());
                startActivity(intent);
                finish();
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
}

