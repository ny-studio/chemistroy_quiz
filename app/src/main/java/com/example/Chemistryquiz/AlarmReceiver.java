package com.example.Chemistryquiz;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    private Date_Data helper;
    private SQLiteDatabase db;
    private int TotalScore;
    private int CorrectScore;
    private int IncorrectScore;

    @Override
    public void onReceive(Context context, Intent intent) {

        TotalScore = 0;
        CorrectScore = 0;
        IncorrectScore = 0;

        PreferenceClass preferenceClass = new PreferenceClass(context);

        //ここに行いたいイベントを記載する
        //月日をとりだす。
        int Month = preferenceClass.getMonth();
        int Date = preferenceClass.getDate();

        //スコアを取り出す
        TotalScore = preferenceClass.getTotalScore();
        CorrectScore = preferenceClass.getCorrectScore();
        IncorrectScore = preferenceClass.getIncorrectScore();

        //データにスコアをいれる
        if (helper == null) {
            helper = new Date_Data(context);
        }
        if (db == null) {
            db = helper.getWritableDatabase();
        }

        //保存
        insertData(db, Month, Date, TotalScore, CorrectScore, IncorrectScore);

        //すべてを更新する
        preferenceClass.allclear();


        int t = preferenceClass.getTotalScore();
        int c = preferenceClass.getCorrectScore();
        int i = preferenceClass.getIncorrectScore();
 }

    private void insertData(SQLiteDatabase db, int month, int date, int ts, int cs, int is) {
        ContentValues values = new ContentValues();
        values.put("month", month);
        values.put("date", date);
        values.put("total_score", ts);
        values.put("correct_score", cs);
        values.put("incorrect_score", is);
        db.insert("testdb", null, values);
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