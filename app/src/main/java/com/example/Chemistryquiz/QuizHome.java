package com.example.Chemistryquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.Serializable;


public class QuizHome extends AppCompatActivity implements Serializable {
    public DonutProgress donutProgress;
    public Button startButton, repeatButton;
    public QuizStatus quizStatus;
    public QuizDataBase helper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prequiz);
        //レイアウトの設定
        donutProgress = findViewById(R.id.donut_progress);
        startButton = findViewById(R.id.start_button);
        repeatButton = findViewById(R.id.repeat_button);
        //コードを取得してクラスを選ぶ　インスタンス化
        String requestcode = getIntent().getStringExtra("CODE");

        MobileAds.initialize(this);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        quizStatus = setIntent(requestcode);
        helper = new QuizDataBase(getApplicationContext());
        db = helper.getReadableDatabase();

        //ツールバーのセット
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(quizStatus.getTitleText());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final Cursor wrongQuizDB = db.query(
                quizStatus.getWrongQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);
        //rightqsは正答率を出すために必要
        final Cursor rightQuizDB = db.query(
                quizStatus.getRightQuizDBName(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);

        startButton.setText("問題" + "(" + quizStatus.getQuizData().length + ")");
        repeatButton.setText("間違えた問題" + "(" + wrongQuizDB.getCount() + ")");
        donutProgress.setDonut_progress(String.valueOf((rightQuizDB.getCount() * 100) / quizStatus.getQuizData().length));//(正解数÷全問題数)*100=正答率

        //開始
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Quiz.class);
                intent.putExtra("QUIZSTATUS", quizStatus);
                startActivity(intent);
            }
        });
        //間違った問題を解く
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wrongQuizDB.getCount() != 0) {
                    Intent intent = new Intent(getApplication(), ReQuiz.class);
                    intent.putExtra("QUIZSTATUS", quizStatus);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "問題がありません。", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //どのクラスをインスタンス化するかえらぶメソッド
    private static QuizStatus setIntent(String requestcode) {
        QuizStatus quizStatus;
        switch (requestcode) {
            case "gousei":
                quizStatus = new 合成高分子();
                break;
            case "kotai":
                quizStatus = new 固体();
                break;
            case "tennen":
                quizStatus = new 天然高分子();
                break;
            case "yuuki":
                quizStatus = new 有機問題();
                break;
            case "kitai":
                quizStatus = new 気体溶液();
                break;
            case "netukagaku":
                quizStatus = new 熱化学();
                break;
            case "bussitu_kousei":
                quizStatus = new 物質の構成();
                break;
            case "houkou":
                quizStatus = new 芳香();
                break;
            case "sanka_kangen":
                quizStatus = new 酸化還元();
                break;
            case "kinzoku":
                quizStatus = new 金属();
                break;
            case "kinzokuion":
                quizStatus = new 金属イオン();
                break;
            case "denki_bunkai":
                quizStatus = new 電気分解();
                break;
            case "hikinzoku":
                quizStatus = new 非金属();
                break;
            case "seihou":
                quizStatus = new 工業的製法();
                break;
            case "housoku":
                quizStatus = new 法則();
                break;
            default:
                quizStatus = null;
        }
        return quizStatus;
    }

    // オプションメニューのアイテムが選択されたときに呼び出されるメソッド
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.item1:
                intent = new Intent(getApplication(), 設定.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                intent = new Intent(getApplicationContext(), 問題一覧.class);
                startActivity(intent);
                return true;
            case R.id.item3:
                intent = new Intent(getApplication(), Glaph.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                intent = new Intent(getApplicationContext(), quizStatus.getbeforeClass());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // アクションバーを表示するメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    //スマホのもどるボタンを押したときに呼び出されるメソッド
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(getApplicationContext(), quizStatus.getbeforeClass());
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

