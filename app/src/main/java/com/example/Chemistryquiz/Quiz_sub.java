package com.example.Chemistryquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.io.Serializable;

public class Quiz_sub extends Quiz_Theme implements Serializable {
    public DonutProgress DonutProgress;
    public Button Start_Button;
    public Button Repeat_Button;
    public Quiz_Theme quiz_sub_theme;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_sub);
        //レイアウトの設定
        DonutProgress = findViewById(R.id.donut_progress);
        Start_Button = findViewById(R.id.start_button);
        Repeat_Button = findViewById(R.id.repeat_button);
        //コードを取得してクラスを選ぶ　インスタンス化
        String requestcode = getIntent().getStringExtra("CODE");

        try {
            quiz_sub_theme = setintent(requestcode);
        } catch (NullPointerException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("問題が存在しません")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            builder.setCancelable(false);
                        }
                    });
            builder.show();
        }

        //広告をおく
        set_ad();
        //ツールバーをおく
        set_Toolbar(quiz_sub_theme.getTitletext());
        helper = new Database(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        //quizqsは間違えた問題を出す

        final Cursor c1 = db.query(
                quiz_sub_theme.gettable_c1(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);
        //rightqsは正答率を出すために必要
        final Cursor c2 = db.query(
                quiz_sub_theme.gettable_c2(), new String[]{"qs", "quiz1", "quiz2", "quiz3", "quiz4", "explanation"}, null, null, null, null, null);

        Start_Button.setText("問題" + "(" + quiz_sub_theme.getquizdata().length + ")");
        Repeat_Button.setText("間違えた問題" + "(" + c1.getCount() + ")");
        DonutProgress.setDonut_progress(String.valueOf((c2.getCount() * 100) / quiz_sub_theme.getquizdata().length));//(正解数÷全問題数)*100=正答率

        //開始
        Start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start(quiz_sub_theme);
            }
        });
        //間違った問題を解く
        Repeat_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repeat(c1, quiz_sub_theme);
            }
        });

    }

    //問題をはじめる
    public void Start(Quiz_Theme q) {
        //qのデータをシリアライズ化
        Quiz_Theme quiz_sub_theme = new Quiz_Theme(q.getquizdata(), q.gettable_c1(), q.gettable_c2(), q.getTitletext());
        //インテントで送る
        Intent intent = new Intent(getApplication(), QuizApp.class);
        intent.putExtra("QUIZ_SUB_THEME", quiz_sub_theme);
        startActivity(intent);
    }

    //間違えた問題をはじめる
    public void Repeat(Cursor c1, Quiz_Theme q) {
        if (c1.getCount() != 0) {
            Quiz_Theme quiz_sub_theme = new Quiz_Theme(q.getquizdata(), q.gettable_c1(), q.gettable_c2(), q.getTitletext());
            Intent intent = new Intent(getApplication(), reQuiz.class);
            intent.putExtra("QUIZ_SUB_THEME", quiz_sub_theme);
            startActivity(intent);
        }
    }

    //どのクラスをインスタンス化するかえらぶメソッド
    private static Quiz_Theme setintent(String requestcode) {
        Quiz_Theme q;
        switch (requestcode) {
            case "gousei":
                q = new 合成高分子();
                break;
            case "kotai":
                q = new 固体();
                break;
            case "tennen":
                q = new 天然高分子();
                break;
            case "yuuki":
                q = new 有機問題();
                break;
            case "kitai":
                q = new 気体溶液();
                break;
            case "netukagaku":
                q = new 熱化学();
                break;
            case "bussitu_kousei":
                q = new 物質の構成();
                break;
            case "houkou":
                q = new 芳香();
                break;
            case "sanka_kangen":
                q = new 酸化還元();
                break;
            case "kinzoku":
                q = new 金属();
                break;
            case "kinzokuion":
                q = new 金属イオン();
                break;
            case "denki_bunkai":
                q = new 電気分解();
                break;
            case "hikinzoku":
                q = new 非金属();
                break;
            case "seihou":
                q = new 工業的製法();
                break;
            case "housoku":
                q = new 法則();
                break;
            default:
                q = null;
        }
        return q;
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
                intent = new Intent(getApplicationContext(), quiz_sub_theme.getbeforeClass());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //スマホのもどるボタンを押したときに呼び出されるメソッド
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            intent = new Intent(getApplicationContext(), quiz_sub_theme.getbeforeClass());
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

