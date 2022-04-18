package com.example.Chemistryquiz;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Quiz extends AppCompatActivity implements Serializable {
    ImageView questionImage;
    private TextView countLabel,imageCountLabel,question;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;//解答ﾎﾞﾀﾝ
    private String rightAnswer, explanation;//答えと解説
    private int rightAnswerCount = 0;
    private QuizDataBase helper;
    SQLiteDatabase db;
    SoundManager soundManager;
    boolean sound,touched = false;
    private int count = 1;
    ArrayList<ArrayList<String>> allQuizData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> allSolvedQuizData = new ArrayList<>();
    ArrayList<String> quizArray,copiedArray;
    ArrayList<Button> answerBtnArray;
    QuizStatus quizStatus;//intentでテーマをもってくる
    Cursor wrongQuizDB, rightQuizDB;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        helper = new QuizDataBase(getApplicationContext());
        soundManager = new SoundManager(getApplicationContext());
        countLabel = findViewById(R.id.countLabel);
        imageCountLabel = findViewById(R.id.imageCountLabel);
        question = findViewById(R.id.question);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        answerBtnArray = new ArrayList<>(Arrays.asList(answerBtn1, answerBtn2, answerBtn3, answerBtn4));
        db = helper.getWritableDatabase();
        //効果音の確認
        prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        sound = prefs.getBoolean("boolean", false);

        quizStatus = (QuizStatus) getIntent().getSerializableExtra("QUIZSTATUS");
        wrongQuizDB = db.query(quizStatus.getWrongQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);
        rightQuizDB = db.query(quizStatus.getRightQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);

        // クイズデータquizDataからクイズ出題用のquizArrayを作成する
        setQuizData(quizStatus.getQuizData());
        showNextQuiz();
    }

    public void showNextQuiz() {
        touched = false;
        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(allQuizData.size());
        // randomNumを使って、quizArrayからクイズを一つ取り出す
        quizArray = allQuizData.get(randomNum);
        copiedArray = (ArrayList<String>)quizArray.clone();

        // 問題文または画像を表示
        questionImage = findViewById(R.id.questionImage);
        questionImage.setImageResource(0);
        int imgResID = getResources().getIdentifier(quizArray.get(0), "drawable", getPackageName());
        if (imgResID != 0) {
            questionImage.setImageResource(imgResID);
            question.setText("");
            countLabel.setText("");
            imageCountLabel.setText("Q" + count);
        } else {
            imageCountLabel.setText("");
            question.setText(quizArray.get(0));
            countLabel.setText("Q" + count);
        }

        explanation = quizArray.get(1);
        rightAnswer = quizArray.get(2);
        // クイズ配列から問題文を削除
        quizArray.remove(0);
        // クイズ配列から解説文を削除
        quizArray.remove(0);

        Collections.shuffle(quizArray);

        for (int i = 0; i < 4; i++) {
            answerBtnArray.get(i).setVisibility(View.VISIBLE);
            answerBtnArray.get(i).setText(quizArray.get(i)); // 回答ボタンに正解と選択肢３つを表示
            if (quizArray.get(i).equals("no")) {
                answerBtnArray.get(i).setVisibility(View.GONE);
            }
        }

        // このクイズをquizArrayから削除
        allQuizData.remove(randomNum);
    }

    public void checkAnswer(View view) {
        if(touched){
            return;
        }

        touched = true;

        //出題数
        SharedPreferences preferences = getSharedPreferences("num", MODE_PRIVATE);
        final int QUIZ_COUNT = preferences.getInt("num", 10);
        //どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String selectedBtnText = answerBtn.getText().toString();
        String alertTitle;

        //databaseの用意
        ContentValues values = new ContentValues();
        ArrayList<String> solvedQuizData = new ArrayList<>();

        solvedQuizData.add(String.valueOf(count));
        solvedQuizData.add(copiedArray.get(0));
        solvedQuizData.add(rightAnswer);
        solvedQuizData.add(copiedArray.get(1));
        solvedQuizData.add(selectedBtnText);

        values.put("qs", copiedArray.get(0));
        values.put("explanation", copiedArray.get(1));
        values.put("quiz1", copiedArray.get(2));
        values.put("quiz2", copiedArray.get(3));
        values.put("quiz3", copiedArray.get(4));
        values.put("quiz4", copiedArray.get(5));

        //正誤確認
        if (selectedBtnText.equals(rightAnswer)) {
            solvedQuizData.add("〇");

            rightAnswerCount++;
            if (sound) soundManager.正解();
            alertTitle = "正解!";
            //rigth_tableを用意
            Cursor c = db.query(quizStatus.getRightQuizDBName(),
                    new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                    null, null, null, null, null);

            if(!isDBContain(c,copiedArray.get(0))) {
                db.delete(quizStatus.getWrongQuizDBName(), "qs=?", new String[]{copiedArray.get(0)});
            }
        } else {
            solvedQuizData.add("×");
            if (sound) soundManager.不正解();
            alertTitle = "不正解...";

            Cursor c = db.query(quizStatus.getWrongQuizDBName(),
                    new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                    null, null, null, null, null);

            if (!isDBContain(c, copiedArray.get(0))) {
                db.insert(quizStatus.getWrongQuizDBName(), null, values);
            }

            //正解DBから削除
            db.delete(quizStatus.getRightQuizDBName(), "qs=?", new String[]{copiedArray.get(0)});
        }
        allSolvedQuizData.add(solvedQuizData);

        // ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え : " + rightAnswer + "\r\n解説 : " + explanation);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (count == QUIZ_COUNT || allQuizData.size() == 0) {
                    // 結果画面へ移動
                    startResultActivuty(quizStatus, rightAnswerCount, count);
                } else {
                    //続く
                    count++;
                    showNextQuiz();
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (count == QUIZ_COUNT || allQuizData.size() == 0) {
                    // 結果画面へ移動
                    startResultActivuty(quizStatus, rightAnswerCount, count);
                } else {
                    //続く
                    count++;
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

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理
                startResultActivuty(quizStatus, rightAnswerCount, count - 1);
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

    public void setQuizData(String[][] quiz) {
        for (int i = 0; i < quiz.length; i++) {
            // 新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();
            Collections.addAll(tmpArray, quiz[i][0], quiz[i][1],
                    quiz[i][2], quiz[i][3], quiz[i][4], quiz[i][5]);
            // tmpArrayをquizArrayに追加する
            allQuizData.add(tmpArray);
        }
    }

    public void startResultActivuty(QuizStatus quizStatus, int rightAnswerCount, int quizCount) {
        if(sound) soundManager.終了();

        if(quizCount != 0) {
            Intent intent = new Intent(getApplication(), ResultActivity.class);
            intent.putExtra("QUIZSTATUS", quizStatus);
            intent.putExtra("KIND", "normal");
            intent.putExtra("SOLVED_QUIZ", allSolvedQuizData);
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
            intent.putExtra("QUIZ_COUNT", quizCount);
            startActivity(intent);
            finish();
        }else{
            finish();
        }

    }

    public boolean isDBContain(Cursor db, String s) {
        //被り確認
        boolean a = false;
        db.moveToFirst();
        for (int i = 0; i < db.getCount(); i++) {
            if (s.equals(db.getString(0))) {
                a = true;
                break;
            }
            db.moveToNext();
        }
        db.close();
        return a;
    }
}
