package com.example.Chemistryquiz;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;

public class Quiz_Theme extends AppCompatActivity implements Serializable {

    public Database helper;
    public Toolbar toolbar;
    public QuizData quizData = new QuizData();
    private String table_c1;
    private String table_c2;
    private String titletext;
    private String[][] quizdata;
    private Class BeforeClass;

    public Quiz_Theme() {

    }

    public Quiz_Theme(String[][] q, String tc1, String tc2, String ti) {
        quizdata = q;
        table_c1 = tc1;
        table_c2 = tc2;
        titletext = ti;
    }

    //問題をえらぶメソッド
    public void setquizdata(String[][] quizdata) {
        this.quizdata = quizdata;
    }

    //問題をとりだすメソッド
    protected String[][] getquizdata() {
        return this.quizdata;
    }

    public void settable_c1(String table_c1) {
        this.table_c1 = table_c1;
    }

    public String gettable_c1() {
        return this.table_c1;
    }

    public void settable_c2(String table_c2) {
        this.table_c2 = table_c2;
    }

    public String gettable_c2() {
        return this.table_c2;
    }

    protected void setTitletext(String titletext) {
        this.titletext = titletext;
    }

    protected String getTitletext() {
        return this.titletext;
    }

    //前のクラスをえらぶ
    public void setbeforeClass(Class BeforeClass) {
        this.BeforeClass = BeforeClass;
    }

    //とりだす
    public Class getbeforeClass() {
        return this.BeforeClass;
    }

    // アクションバーを表示するメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    //広告をつけるメソッド
    final void set_ad() {
        MobileAds.initialize(this, getString(R.string.ads_AppId));
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    //ツールバーを設定するメソッド
    final void set_Toolbar(String titletext) {
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle(titletext);
        setSupportActionBar(toolbar);
    }
}