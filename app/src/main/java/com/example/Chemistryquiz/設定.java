package com.example.Chemistryquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class 設定 extends AppCompatActivity {

    private TextView textView;
    private int i;
    boolean check;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("設定");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //数
        SharedPreferences preferences = getSharedPreferences("num",MODE_PRIVATE);
        i = preferences.getInt("num",10);
        textView = findViewById(R.id.edittext);
        textView.setText(String.valueOf(i));

        //効果音
        Switch switchButton = findViewById(R.id.sound);
        SharedPreferences prefs = getSharedPreferences("soundpref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        check = prefs.getBoolean("boolean",check);
        switchButton.setChecked(check);
        // switchButtonのオンオフが切り替わった時の処理を設定
        switchButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener(){
                    public void onCheckedChanged(CompoundButton comButton, boolean isChecked) {
                        // 表示する文字列をスイッチのオンオフで変える
                        // オンなら
                        // オフなら
                        check = isChecked;
                        editor.putBoolean("boolean", check);
                        editor.apply();
                    }

                }
        );
    }
    public void plus(View view){
        //出題数
        ++i;
        SharedPreferences preferences = getSharedPreferences("num",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("num",i);
        editor.apply();
        textView = findViewById(R.id.edittext);
        textView.setText(String.valueOf(i));
    }
    public void minus(View view){
        //出題数
        if (i>5){
            --i;
        }else if(i==5){
        }
        SharedPreferences preferences = getSharedPreferences("num",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("num",i);
        editor.apply();
        textView = findViewById(R.id.edittext);
        textView.setText(String.valueOf(i));
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
