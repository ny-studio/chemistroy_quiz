package com.example.Chemistryquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

import static com.example.Chemistryquiz.Exam.KEY;

public class ExamResult extends AppCompatActivity {

    ArrayList<ArrayList<String>> allSolvedQuizData;
    String[] message = {"おつかれさま！", "すごいね！", "がんばったね！", "よくできました！", "いいね！", "その調子！", "ファイト！"};
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);

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
                                mInterstitialAd.show(ExamResult.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }
                        }
                    });
        }
        SoundManager soundManager = new SoundManager(getApplicationContext());

        TextView resultLabel = findViewById(R.id.resultLabel);
        allSolvedQuizData = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("SOLVED_QUIZ");

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        int count = getIntent().getIntExtra("QUIZ_COUNT", 0);

        Random random = new Random();
        //TextViewに表示
        resultLabel.setText(message[random.nextInt(message.length)] + "\r\n" + score + "/" + count);

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
        Intent intent = new Intent(getApplication(), Exam.class);

        //クイズ問題を送信
        for (int i = 0; i < KEY.length; i++) {
            intent.putExtra(KEY[i], getIntent().getSerializableExtra(KEY[i]));
        }

        startActivity(intent);
        finish();
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
