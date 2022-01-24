package com.example.Chemistryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class 総合 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sougouensyu);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("総合演習");
        setSupportActionBar(toolbar);
        Button sendButton = findViewById(R.id.send_button17);
        //開始クリックで問題
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestcode = "valuesougou";
                Intent intent = new Intent(getApplication(), QuizApp.class);
                intent.putExtra("FORM_REQUESTCODE",requestcode);
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
        TextView varTextView = findViewById(R.id.textView);
        switch (item.getItemId()) {
            case R.id.item1:
                varTextView.setText(R.string.menu_item3);
                Intent intent = new Intent(getApplication(), 設定.class);
                startActivity(intent);
                return true;


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
