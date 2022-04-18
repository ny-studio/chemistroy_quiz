package com.example.Chemistryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class 物質の反応 extends AppCompatActivity {
    private String CODE;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussituhannou);
        MobileAds.initialize(this);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("物質の反応･平衡");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //ﾎﾞﾀﾝ
        Button sendButton1 = findViewById(R.id.send_button28);
        Button sendButton2 = findViewById(R.id.send_button29);
        Button sendButton3 = findViewById(R.id.send_button30);
        //Button sendButton4 = findViewById(R.id.send_button31);

        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CODE= "sanka_kangen";
                Intent intent = new Intent(getApplication(), QuizHome.class);
                intent.putExtra("CODE",CODE);
                startActivity(intent);
            }
        });
        sendButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CODE= "denki_bunkai";
                Intent intent = new Intent(getApplication(), QuizHome.class);
                intent.putExtra("CODE",CODE);
                startActivity(intent);
            }
        });
        sendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CODE= "netukagaku";
                Intent intent = new Intent(getApplication(), QuizHome.class);
                intent.putExtra("CODE",CODE);
                startActivity(intent);
            }
        });


    }
    // アクションバーを表示するメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    // オプションメニューのアイテムが選択されたときに呼び出されるメソッド
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(getApplication(), 設定.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                intent = new Intent(getApplication(), 問題一覧.class);
                startActivity(intent);
                return true;
            case R.id.item3:
                intent = new Intent(getApplication(), Glaph.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                Intent intent1 = new Intent(getApplication(),MainActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンが押されたときの処理
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}