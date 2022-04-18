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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ReQuiz extends AppCompatActivity {
    ImageView questionImage;
    TextView countLabel,imageCountLabel, question;
    Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    String rightAnswer, explanation;
    int rightAnswerCount = 0;
    QuizDataBase helper;
    SQLiteDatabase db;
    boolean check, touched = false;
    SoundManager soundManager;
    int count = 1;
    QuizStatus quizStatus;
    ArrayList<ArrayList<String>> allQuizData = new ArrayList<>();
    ArrayList<ArrayList<String>> allSolvedQuizData = new ArrayList<>();//{問題番号,問題文,解説文,答え,選んだ答え,合否}
    ArrayList<String> quizArray, copiedArray;
    ArrayList<Button> answerBtnList;
    Cursor wrongQuizDB, rightQuizDB;
    AlertDialog.Builder builder;


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
        answerBtnList = new ArrayList<>(Arrays.asList(answerBtn1, answerBtn2, answerBtn3, answerBtn4));
        db = helper.getWritableDatabase();
        builder = new AlertDialog.Builder(this);


        //前のクラスから値を受け取る
        quizStatus = (QuizStatus) getIntent().getSerializableExtra("QUIZSTATUS");

        wrongQuizDB = db.query(quizStatus.getWrongQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);
        rightQuizDB = db.query(quizStatus.getRightQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);

        readData(quizStatus.getWrongQuizDBName());
        showNextQuiz();
    }

    public void readData(String table_name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(table_name,
                new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            // 新しいArrayListを準備
            ArrayList<String> arrayList = new ArrayList<>();
            Collections.addAll(arrayList, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            allQuizData.add(arrayList);
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void showNextQuiz() {
        touched = false;
        // クイズカウントラベルを更新
        questionImage = findViewById(R.id.questionImage);
        questionImage.setImageResource(0);
        countLabel.setText("Q" + count);
        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(allQuizData.size());
        // randomNumを使って、quizArrayからクイズを一つ取り出す
        quizArray = allQuizData.get(randomNum);
        copiedArray = (ArrayList<String>) quizArray.clone();

        // 問題文を表示
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

        // 正解をrightAnswerにセット
        explanation = quizArray.get(5);
        rightAnswer = quizArray.get(1);
        // クイズ配列から解説文を削除
        quizArray.remove(5);
        // クイズ配列から問題文を削除
        quizArray.remove(0);

        Collections.shuffle(quizArray);

        for (int i = 0; i < 4; i++) {
            answerBtnList.get(i).setVisibility(View.VISIBLE);
            answerBtnList.get(i).setText(quizArray.get(i)); // 回答ボタンに正解と選択肢３つを表示
            if (quizArray.get(i).equals("no")) {
                answerBtnList.get(i).setVisibility(View.GONE);//非表示
            }
        }
        //出題した問題を削除
        allQuizData.remove(randomNum);
    }

    public void checkAnswer(View view) {
        if (touched) {
            return;
        }

        touched = true;

        SharedPreferences preferences = getSharedPreferences("num", MODE_PRIVATE);
        final int QUIZ_COUNT = preferences.getInt("num", 10);

        // どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String selectedText = answerBtn.getText().toString();
        String alertTitle;

        ContentValues values = new ContentValues();
        SharedPreferences prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        boolean bool = prefs.getBoolean("boolean", check);

        ArrayList<String> solvedQuizData = new ArrayList<>();

        solvedQuizData.add(String.valueOf(count));
        solvedQuizData.add(copiedArray.get(0));
        solvedQuizData.add(rightAnswer);
        solvedQuizData.add(copiedArray.get(5));
        solvedQuizData.add(selectedText);

        if (selectedText.equals(rightAnswer)) {
            solvedQuizData.add("〇");
            if (bool) {
                soundManager.正解();
            }
            rightAnswerCount++;
            alertTitle = "正解!";

            Cursor c = db.query(quizStatus.getRightQuizDBName(),
                    new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                    null, null, null, null, null);

            //データの削除
            db.delete(quizStatus.getWrongQuizDBName(), "qs=?", new String[]{copiedArray.get(0)});

            if (!isDBContain(c, copiedArray.get(0))) {
                values.put("qs", copiedArray.get(0));
                values.put("explanation", copiedArray.get(5));
                values.put("quiz1", copiedArray.get(1));
                values.put("quiz2", copiedArray.get(2));
                values.put("quiz3", copiedArray.get(3));
                values.put("quiz4", copiedArray.get(4));
                db.insert(quizStatus.getRightQuizDBName(), null, values);
            }
        } else {
            solvedQuizData.add("×");
            alertTitle = "不正解...";
            if (bool) {
                soundManager.不正解();
            }
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
                    resultIntent(quizStatus, rightAnswerCount, count);
                } else {
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
                    resultIntent(quizStatus, rightAnswerCount, count);
                } else {
                    //続く
                    count++;
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

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理
                resultIntent(quizStatus, rightAnswerCount, count - 1);
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

    public void resultIntent(QuizStatus quizStatus, int rightAnswerCount, int quizCount) {
        if (quizCount != 0) {
            Intent intent = new Intent(getApplication(), ResultActivity.class);
            intent.putExtra("QUIZSTATUS", quizStatus);
            intent.putExtra("SOLVED_QUIZ", allSolvedQuizData);
            intent.putExtra("KIND", "repeat");
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
            intent.putExtra("QUIZ_COUNT", quizCount);
            startActivity(intent);
            finish();
        } else {
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

