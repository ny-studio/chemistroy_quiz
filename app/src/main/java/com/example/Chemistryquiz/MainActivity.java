package com.example.Chemistryquiz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = getSharedPreferences("quizapp", Context.MODE_PRIVATE).edit();

        al();

        MobileAds.initialize(this);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //月日を更新
        editor.putInt("Month", Today_Month());
        editor.putInt("Date", Today_Date());
        editor.apply();

        //ツールバーのセット
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("高校化学");
        setSupportActionBar(toolbar);

        //ボタンのセット
        Button mukiButton = findViewById(R.id.muki);
        Button yukiButton = findViewById(R.id.yuki);
        Button koubunsiButton = findViewById(R.id.koubunsi);
        Button housokuButton = findViewById(R.id.housoku);
        Button joutaiButton = findViewById(R.id.joutaiheikou);
        Button hannouButton = findViewById(R.id.hannou);
        Button examButton = findViewById(R.id.exam);

        mukiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 無機.class);
                startActivity(intent);
            }
        });
        yukiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 有機.class);
                startActivity(intent);
            }
        });
        koubunsiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 高分子.class);
                startActivity(intent);
            }
        });
        housokuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 法則反応など.class);
                startActivity(intent);
            }
        });
        joutaiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 物質の状態平衡.class);
                startActivity(intent);
            }
        });
        hannouButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), 物質の反応.class);
                startActivity(intent);
            }
        });
        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),ExamSetting.class);
                startActivity(intent);
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
        PendingIntent _sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

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