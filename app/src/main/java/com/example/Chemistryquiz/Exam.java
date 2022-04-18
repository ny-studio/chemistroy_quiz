package com.example.Chemistryquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Exam extends AppCompatActivity implements Serializable {
    ImageView questionImage;
    TextView countLabel, imageCountLabel,question;
    Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;//解答ﾎﾞﾀﾝ
    String rightAnswer, explanation;//答えと解説
    int rightAnswerCount = 0, count = 1;
    QuizDataBase helper;
    SoundManager soundManager;
    boolean bool, touched = false;
    SQLiteDatabase db;
    ArrayList<ArrayList<String>> allQuizData = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> allSolvedQuizData = new ArrayList<ArrayList<String>>();
    ArrayList<String> quizArray, copiedArray;
    ArrayList<Button> answerBtnList;
    Random random = new Random();
    AlertDialog.Builder builder;
    static String[] KEY = {
            "JOUTAI", "KITAI", "KOTAI", "SANKAKANGEN", "DENKIBUNKAI",
            "NETUKAGAKU", "HIKINZOKU", "KINZOKU", "ION", "YUKI",
            "HOUKOU", "TENNEN", "GOUSEI", "HOUSOKU", "KOUGYOU"};
    //出題数
    SharedPreferences preferences, prefs;
    int QUIZ_COUNT;

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

        prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        bool = prefs.getBoolean("boolean", false);

        preferences = getSharedPreferences("examnum", MODE_PRIVATE);
        QUIZ_COUNT = preferences.getInt("examnum", 10);
        //前のクラスから問題を受け取る
        for (int i = 0; i < KEY.length; i++) {
            // クイズデータquizDataからクイズ出題用のquizArrayを作成する
            if (getIntent().getSerializableExtra(KEY[i]) != null) {
                setQuiz((String[][]) getIntent().getSerializableExtra(KEY[i]));
            }
        }

        showNextQuiz();
    }

    public void showNextQuiz() {
        touched = false;
        // クイズカウントラベルを更新
        countLabel.setText("Q" + count);

        // ランダムな数字を取得
        int randomNum = random.nextInt(allQuizData.size());
        // randomNumを使って、quizArrayからクイズを一つ取り出す
        quizArray = allQuizData.get(randomNum);

        copiedArray = (ArrayList<String>) quizArray.clone();

        // 問題文または画像を表示
        questionImage = findViewById(R.id.questionImage);
        questionImage.setImageResource(0);


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

        //選択肢をシャッフルしてボタンに表示
        Collections.shuffle(quizArray);

        for (int i = 0; i < 4; i++) {
            answerBtnList.get(i).setVisibility(View.VISIBLE);
            answerBtnList.get(i).setText(quizArray.get(i)); // 回答ボタンに正解と選択肢３つを表示
            if (quizArray.get(i).equals("no")) {
                answerBtnList.get(i).setVisibility(View.GONE);
            }
        }

        //出題した問題を除去
        allQuizData.remove(randomNum);
    }

    public void checkAnswer(View view) {
        //効果音の確認
        if (bool) soundManager.選択();

        if (touched) {
            return;
        }

        touched = true;
        Button answerBtn = findViewById(view.getId());
        String selectedBtnText = answerBtn.getText().toString();

        ArrayList<String> solvedQuizData = new ArrayList<>();

        solvedQuizData.add(String.valueOf(count));
        solvedQuizData.add(copiedArray.get(0));
        solvedQuizData.add(rightAnswer);
        solvedQuizData.add(copiedArray.get(1));
        solvedQuizData.add(selectedBtnText);

        //正解の場合
        if (selectedBtnText.equals(rightAnswer)) {
            solvedQuizData.add("〇");
            rightAnswerCount++;
        } else { //不正解の場合
            solvedQuizData.add("×");
        }
        allSolvedQuizData.add(solvedQuizData);

        if (count == QUIZ_COUNT || allQuizData.size() == 0) {
            // 結果画面へ移動
            resultIntent(rightAnswerCount, count);
        } else {
            //続く
            count++;
            showNextQuiz();
        }
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
                resultIntent(rightAnswerCount, count - 1);
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

    public void setQuiz(String[][] quiz) {
        for (int i = 0; i < quiz.length; i++) {
            // 新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();
            Collections.addAll(tmpArray, quiz[i][0], quiz[i][1],
                    quiz[i][2], quiz[i][3], quiz[i][4], quiz[i][5]);
            // tmpArrayをquizArrayに追加する
            allQuizData.add(tmpArray);
        }
    }

    public void resultIntent(int rightAnswerCount, int quizCount) {
        if (bool) soundManager.終了();

        if (quizCount != 0) {
            Intent intent = new Intent(getApplication(), ExamResult.class);

            //問題を送る
            for (int i = 0; i < KEY.length; i++) {
                intent.putExtra(KEY[i], getIntent().getSerializableExtra(KEY[i]));
            }
            intent.putExtra("SOLVED_QUIZ", allSolvedQuizData);
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
            intent.putExtra("QUIZ_COUNT", quizCount);
            startActivity(intent);
            finish();
        } else {
            finish();
        }
    }
}
