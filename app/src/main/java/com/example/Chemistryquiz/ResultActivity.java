package com.example.Chemistryquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ResultActivity extends AppCompatActivity {

    private Quiz_Theme q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        set_ad();

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);
        TextView TotalScoreLabel = findViewById(R.id.TotalScoreLabel);
        TextView correctScoreLabel = findViewById(R.id.correctScoreLabel);
        TextView incorrectScoreLabel = findViewById(R.id.incorrectScoreLabel);

        q = (Quiz_Theme) getIntent().getSerializableExtra("QUIZ_SUB_THEME");

        //正解数
        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        int count = getIntent().getIntExtra("QUIZ_COUNT", 0);
        int TotalScore = 0;
        int CorrectScore = 0;
        int IncorrectScore = 0;
        //トータルスコア
        SharedPreferences ScoreStore = getSharedPreferences("quizapp", Context.MODE_PRIVATE  | Context.MODE_MULTI_PROCESS);

        int totalScore = ScoreStore.getInt("totalScore", 0);//正解数(今までの)

        CorrectScore = ScoreStore.getInt("CorrectScore", 0);//正解数
        TotalScore = ScoreStore.getInt("TotalScore", 0);//回答数
        IncorrectScore = ScoreStore.getInt("InCorrectScore", 0);//不正解数


        //加算
        totalScore += score;
        CorrectScore += score;
        TotalScore += count;
        IncorrectScore += count - score;

        //TextViewに表示
        resultLabel.setText(score + "/" + count  );
        TotalScoreLabel.setText("トータルスコア : " + totalScore);
        totalScoreLabel.setText("回答数 : " + TotalScore);
        correctScoreLabel.setText("正解数 : " + CorrectScore);
        incorrectScoreLabel.setText("不正解数 : " + IncorrectScore);

        //トータルスコアを保存
        SharedPreferences.Editor editor = ScoreStore.edit();
        editor.putInt("totalScore", totalScore);
        editor.putInt("CorrectScore", CorrectScore);
        editor.putInt("TotalScore", TotalScore);
        editor.putInt("InCorrectScore", IncorrectScore);
        editor.apply();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void returnTop(View view){
       finish();
    }
    //次の問題
    public void next(View view) {
        Intent intent;
        int wrongquiznum = getIntent().getIntExtra("wrong_quiz_num",0);
        String code = getIntent().getStringExtra("KIND");
        if(code.indexOf("normal") != -1) {
            intent = new Intent(getApplication(), QuizApp.class);
            intent.putExtra("QUIZ_SUB_THEME", q);
            startActivity(intent);
            finish();
        }else if(code.indexOf("repeat") != -1){
            if(wrongquiznum != 0) {
                intent = new Intent(getApplication(), reQuiz.class);
                intent.putExtra("QUIZ_SUB_THEME",q);
                startActivity(intent);
                finish();
            }
        }
    }
    public void set_ad(){
        //広告表じ
        //ca-app-pub-3940256099942544/1033173712
        //ca-app-pub-3940256099942544~3347511713はテストアプリID
        MobileAds.initialize(this, getString(R.string.ads_AppId));
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
    @Override
    public void onBackPressed() {
        // 何もしない
    }
}
