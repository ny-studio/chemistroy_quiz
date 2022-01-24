package com.example.Chemistryquiz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = getSharedPreferences("quizapp", Context.MODE_PRIVATE).edit();

        al();

        //月日を更新
        editor.putInt("Month", Today_Month());
        editor.putInt("Date", Today_Date());
        editor.apply();


        //ツールバーのセット
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("高校化学");
        setSupportActionBar(toolbar);

        //ボタンのセット
        Button sendButton1 = findViewById(R.id.send_button);
        Button sendButton2 = findViewById(R.id.send_button1);
        Button sendButton3 = findViewById(R.id.send_button2);
        Button sendButton4 = findViewById(R.id.send_button3);
        Button sendButton5 = findViewById(R.id.send_button20);
        Button sendbutton6 = findViewById(R.id.send_button27);

        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 無機.class);
                startActivity(intent);
            }
        });
        sendButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 有機.class);
                startActivity(intent);
            }
        });
        sendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 高分子.class);
                startActivity(intent);
            }
        });
        sendButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 法則反応など.class);
                startActivity(intent);
            }
        });
        sendButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 物質の状態平衡.class);
                startActivity(intent);
            }
        });
        sendbutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 物質の反応.class);
                startActivity(intent);
            }
        });

        MobileAds.initialize(this, getString(R.string.ads_AppId));
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        // ad's lifecycle: loading, opening, closing, and so on
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("debug","Code to be executed when an ad finishes loading.");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("debug","Code to be executed when an ad request fails.");
            }

            @Override
            public void onAdOpened() {
                Log.d("debug","Code to be executed when an ad opens an overlay that covers the screen.");
            }

            @Override
            public void onAdLeftApplication() {
                Log.d("debug","Code to be executed when the user has left the app.");
            }

            @Override
            public void onAdClosed() {
                Log.d("debug","Code to be executed when when the user is about to return to the app after tapping on an ad.");
            }
        });

    }

    // アクションバーを表示するメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public void al() {
        Intent intent = new Intent(this, AlarmReceiver.class);

        //①Notificationを起動させる為。
        PendingIntent _sender = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);//9時にセット
        calendar.set(Calendar.MINUTE, 0);//0分
        calendar.set(Calendar.SECOND, 0);//0秒
        calendar.set(Calendar.MILLISECOND, 0);//カンマ0秒

        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }//もし時間が過去の場合は1日先でセット

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, _sender);
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
        }
        return super.onOptionsItemSelected(item);
    }

    //今日の日付を取得するメソッド
    private int Today_Date() {
        Calendar calendar = Calendar.getInstance();
        int Date = calendar.get(Calendar.DATE);
        return Date;
    }

    //今日の月を取得するメソッド
    private int Today_Month() {
        Calendar calendar = Calendar.getInstance();
        int Month = calendar.get(Calendar.MONTH) + 1;//返り値が0-11であることに注意
        return Month;
    }
}