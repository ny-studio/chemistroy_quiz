package com.example.Chemistryquiz;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class PreferenceClass {
    private final SharedPreferences sharedpreferences;

    public PreferenceClass(Context context) {
        sharedpreferences = context.getSharedPreferences("quizapp", Context.MODE_PRIVATE);
    }

    public int getMonth(){
        //月をとりだす。
        int Month = sharedpreferences.getInt("Month", Today_Month());//保存された月
        return Month;
    }
    public int getDate(){
        //日をとりだす。
        int Date = sharedpreferences.getInt("Date", Today_Date());//保存された日付
        return Date;
    }
    public int gettotalScore(){
        //スコアを取り出す
        int totalScore = sharedpreferences.getInt("totalScore", 0);//回答数
        return totalScore;
    }
    public int getTotalScore(){
        //スコアを取り出す
        int TotalScore = sharedpreferences.getInt("TotalScore", 0);//回答数
        return TotalScore;
    }
    public int getCorrectScore(){
        //スコアを取り出す
        int CorrectScore = sharedpreferences.getInt("CorrectScore", 0);//正解数
        return CorrectScore;
    }
    public int getIncorrectScore(){
        int IncorrectScore = sharedpreferences.getInt("InCorrectScore", 0);//不正解数
        return IncorrectScore;
    }
    public void allclear(){
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //スコアを初期化(毎日更新)
        editor.putInt("TotalScore", 0);
        editor.putInt("CorrectScore", 0);
        editor.putInt("InCorrectScore", 0);

        //月日を更新
        editor.putInt("Month",Today_Month());
        editor.putInt("Date",Today_Date());

        editor.commit();
    }
    public void store(int totalScore,int CorrectScore,int TotalScore,int IncorrectScore){
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt("totalScore", totalScore);
        editor.putInt("CorrectScore", CorrectScore);
        editor.putInt("TotalScore", TotalScore);
        editor.putInt("InCorrectScore", IncorrectScore);
        editor.commit();
    }

    //今日の日付を取得するメソッド
    private int Today_Date(){
        Calendar calendar = Calendar.getInstance();
        int Date = calendar.get(Calendar.DATE);
        return Date;
    }

    //今日の月を取得するメソッド
    private int Today_Month(){
        Calendar calendar = Calendar.getInstance();
        int Month = calendar.get(Calendar.MONTH)+1;//返り値が0-11であることに注意
        return Month;
    }
}
