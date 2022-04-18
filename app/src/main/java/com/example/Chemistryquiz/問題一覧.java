package com.example.Chemistryquiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

public class 問題一覧 extends AppCompatActivity {

    private QuizData quizData = new QuizData();
    private EditText editText;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai_list);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("問題一覧");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        if (Math.random() < 0.4) {
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
                                mInterstitialAd.show(問題一覧.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }
                        }
                    });
        }

        //クリックされたときに行う
        editText = (EditText) findViewById(R.id.Search_Text);
        findViewById(R.id.Search_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //リストビューに表示する要素を決定
                String search_word = editText.getText().toString();
                if (search_word.isEmpty()) {
                    search_word = null;
                    editText.setHint("文字を入力してください");
                }
                ArrayList<ListItem> listItems1 = new ArrayList<>();
                ListView listView1 = (ListView) findViewById(R.id.mondai_list);
                Search_addItems(listItems1, search_word);

                ListAdapter adapter = new ListAdapter(問題一覧.this, R.layout.simple_list_item_1, listItems1);
                listView1.setAdapter(adapter);
            }
        });
        //レイアウトからリストビューを取得
        ListView listView = (ListView) findViewById(R.id.mondai_list);

        //リストビューに表示する要素を決定
        ArrayList<ListItem> listItems = new ArrayList<>();

        addItems(listItems);

        //表示
        ListAdapter adapter = new ListAdapter(this, R.layout.simple_list_item_1, listItems);

        listView.setAdapter(adapter);
    }

    private void addItems(ArrayList<ListItem> listItems) {
        addItem(listItems, quizData.getQuizData_bussitu(), "物質の状態・構成");
        addItem(listItems, quizData.getQuizData_kitai(), "気体・溶液の性質");
        addItem(listItems, quizData.getQuizData_kotai(), "固体の性質");
        addItem(listItems, quizData.getQuizData_sankakangen(), "酸化還元反応");
        addItem(listItems, quizData.getQuizData_denki(), "電池・電気分解");
        addItem(listItems, quizData.getQuizData_netsukagaku(), "熱化学");
        addItem(listItems, quizData.getQuizData_hikinzoku(), "非金属元素");
        addItem(listItems, quizData.getQuizData_kinzoku(), "金属元素");
        addItem(listItems, quizData.getQuizData_kinzoku_ion(), "金属イオンの沈殿");
        addItem(listItems, quizData.getQuizData_yuuki(), "有機化合物");
        addItem(listItems, quizData.getQuizData_houkou(), "芳香族化合物");
        addItem(listItems, quizData.getQuizData_tennen(), "天然高分子化合物");
        addItem(listItems, quizData.getQuizData_gousei(), "合成高分子化合物");
        addItem(listItems, quizData.getQuizData_seihou(), "工業的製法");
        addItem(listItems, quizData.getQuizData_housoku(), "法則");
    }

    private void Search_addItems(ArrayList<ListItem> listItems, String search_word) {
        SearchItem(listItems, quizData.getQuizData_bussitu(), "物質の状態・構成", search_word);
        SearchItem(listItems, quizData.getQuizData_kitai(), "気体・溶液の性質", search_word);
        SearchItem(listItems, quizData.getQuizData_kotai(), "固体の性質", search_word);
        SearchItem(listItems, quizData.getQuizData_sankakangen(), "酸化還元反応", search_word);
        SearchItem(listItems, quizData.getQuizData_denki(), "電池・電気分解", search_word);
        SearchItem(listItems, quizData.getQuizData_netsukagaku(), "熱化学", search_word);
        SearchItem(listItems, quizData.getQuizData_hikinzoku(), "非金属元素", search_word);
        SearchItem(listItems, quizData.getQuizData_kinzoku(), "金属元素", search_word);
        SearchItem(listItems, quizData.getQuizData_kinzoku_ion(), "金属イオンの沈殿", search_word);
        SearchItem(listItems, quizData.getQuizData_yuuki(), "有機化合物", search_word);
        SearchItem(listItems, quizData.getQuizData_houkou(), "芳香族化合物", search_word);
        SearchItem(listItems, quizData.getQuizData_tennen(), "天然高分子化合物", search_word);
        SearchItem(listItems, quizData.getQuizData_gousei(), "合成高分子化合物", search_word);
        SearchItem(listItems, quizData.getQuizData_seihou(), "工業的製法", search_word);
        SearchItem(listItems, quizData.getQuizData_housoku(), "法則", search_word);
    }

    private void addItem(ArrayList<ListItem> listItems, String[][] a, String title) {
        for (int i = 0; i < a.length; i++) {
            int imageid = getResources().getIdentifier(a[i][0], "drawable", getPackageName());
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageid);

            if (imageid != 0) {//画像の問題の場合
                ListItem item = new ListItem(bmp, title, "", a[i][2], a[i][1]);
                listItems.add(item);
            } else {//文字の問題の場合
                ListItem item = new ListItem(null, title, a[i][0], a[i][2], a[i][1]);
                listItems.add(item);
            }

        }
    }

    private void SearchItem(ArrayList<ListItem> listItems, String[][] a, String title, String search_word) {
        for (int i = 0; i < a.length; i++) {
            int imageid = getResources().getIdentifier(a[i][0], "drawable", getPackageName());
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageid);
            if (imageid != 0) {//画像の問題の場合
                if (title.matches(".*" + search_word + ".*") || a[i][1].matches(".*" + search_word + ".*")) {
                    ListItem item = new ListItem(bmp, title, "", a[i][2], a[i][1]);
                    listItems.add(item);
                }
            } else {//文字の問題の場合
                if (title.matches(".*" + search_word + ".*") || a[i][0].matches(".*" + search_word + ".*") || a[i][2].matches(".*" + search_word + ".*")) {
                    ListItem item = new ListItem(null, title, a[i][0], a[i][2], a[i][1]);//解説をいれるため
                    listItems.add(item);
                }
            }

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
