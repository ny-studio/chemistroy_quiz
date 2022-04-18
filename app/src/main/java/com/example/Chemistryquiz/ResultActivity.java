package com.example.Chemistryquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private QuizStatus quizStatus;
    ArrayList<ArrayList<String>> allSolvedQuizData;
    String[] message = {"おつかれさま！", "すごいね！", "がんばったね！", "よくできました！", "いいね！", "その調子！", "ファイト！"};
    SoundManager soundManager;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(Math.random() < 0.4) {
            //広告の用意
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                }
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(this, getResources().getString(R.string.ads_InterstitialId), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            mInterstitialAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            Log.d("TAG", "Ad was loaded.");
                            super.onAdLoaded(interstitialAd);
                            mInterstitialAd = interstitialAd;
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(ResultActivity.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }
                        }
                    });
        }

        TextView resultLabel = findViewById(R.id.resultLabel);
        quizStatus = (QuizStatus) getIntent().getSerializableExtra("QUIZSTATUS");
        allSolvedQuizData = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("SOLVED_QUIZ");
        soundManager = new SoundManager(getApplicationContext());
        //正解数
        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        int count = getIntent().getIntExtra("QUIZ_COUNT", 0);

        Random random = new Random();
        //TextViewに表示
        resultLabel.setText(message[random.nextInt(message.length)] + "\r\n" + score + "/" + count);

        int TotalScore = 0;
        int CorrectScore = 0;
        int IncorrectScore = 0;
        //トータルスコア
        SharedPreferences ScoreStore = getSharedPreferences("quizapp", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        int totalScore = ScoreStore.getInt("totalScore", 0);//正解数(今までの)

        CorrectScore = ScoreStore.getInt("CorrectScore", 0);//正解数
        TotalScore = ScoreStore.getInt("TotalScore", 0);//回答数
        IncorrectScore = ScoreStore.getInt("InCorrectScore", 0);//不正解数

        //加算
        totalScore += score;
        CorrectScore += score;
        TotalScore += count;
        IncorrectScore += count - score;

        //トータルスコアを保存
        SharedPreferences.Editor editor = ScoreStore.edit();
        editor.putInt("totalScore", totalScore);
        editor.putInt("CorrectScore", CorrectScore);
        editor.putInt("TotalScore", TotalScore);
        editor.putInt("InCorrectScore", IncorrectScore);
        editor.apply();

        ListView listView = findViewById(R.id.solvedList);
        ArrayList<SolvedListItem> listItems = new ArrayList<>();
        addItems(listItems);

        //効果音の確認
        SharedPreferences prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        boolean bool = prefs.getBoolean("boolean", false);
        if (bool) soundManager.終了();

        //表示
        SolvedListAdapter adapter = new SolvedListAdapter(this, R.layout.solved_list, listItems);
        listView.setAdapter(adapter);
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

    public void returnTop(View view) {
        finish();
    }

    //次の問題
    public void next(View view) {
        Intent intent;
        String code = getIntent().getStringExtra("KIND");
        QuizDataBase helper = new QuizDataBase(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor wrongQuizDB = db.query(quizStatus.getWrongQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"},
                null, null, null, null, null);
        int wrongquiznum = wrongQuizDB.getCount();

        if (code.indexOf("normal") != -1) {
            intent = new Intent(getApplication(), Quiz.class);
            intent.putExtra("QUIZSTATUS", quizStatus);
            startActivity(intent);
            finish();
        } else if (code.indexOf("repeat") != -1) {
            if (wrongquiznum != 0) {
                intent = new Intent(getApplication(), ReQuiz.class);
                intent.putExtra("QUIZSTATUS", quizStatus);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "問題がありません。", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void addItems(ArrayList<SolvedListItem> listItems) {
        for (int i = 0; i < allSolvedQuizData.size(); i++) {
            ArrayList<String> solvedQuizData = allSolvedQuizData.get(i);

            int imageid = getResources().getIdentifier(solvedQuizData.get(1), "drawable", getPackageName());
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageid);

            if (imageid != 0) {//画像の問題の場合
                SolvedListItem item = new SolvedListItem(
                        bmp,
                        String.valueOf(i + 1),
                        solvedQuizData.get(1),
                        solvedQuizData.get(2),
                        solvedQuizData.get(3),
                        solvedQuizData.get(4),
                        solvedQuizData.get(5)
                );
                listItems.add(item);
            } else {//文字の問題の場合
                SolvedListItem item = new SolvedListItem(
                        null,
                        String.valueOf(i + 1),
                        solvedQuizData.get(1),
                        solvedQuizData.get(2),
                        solvedQuizData.get(3),
                        solvedQuizData.get(4),
                        solvedQuizData.get(5)
                );
                listItems.add(item);
            }
        }
    }
}
