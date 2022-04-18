package com.example.Chemistryquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ExamSetting extends AppCompatActivity {

    int questionNumber;
    TextView textView;
    CheckBox checkAll;
    ArrayList<CheckBox> checkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButton);
        Button startButton = findViewById(R.id.start_button);

        CheckBox checkAll = findViewById(R.id.checkAll);

        CheckBox checkJoutai = findViewById(R.id.checkJoutai);
        CheckBox checkJotaiKousei = findViewById(R.id.checkJoutaiKousei);
        CheckBox checkKitai = findViewById(R.id.checkKitai);
        CheckBox checkKotai = findViewById(R.id.checkKotai);

        CheckBox checkHannouHeikou = findViewById(R.id.checkHannouHeikou);
        CheckBox checkSankakangen = findViewById(R.id.checkSankakangenhannou);
        CheckBox checkDenkibunkai = findViewById(R.id.checkDenkibunkai);
        CheckBox checkNetukagaku = findViewById(R.id.checkNetukagaku);

        CheckBox checkMuki = findViewById(R.id.checkMuki);
        CheckBox checkHikinzoku = findViewById(R.id.checkHikinzoku);
        CheckBox checkKinzoku = findViewById(R.id.checkKinzoku);
        CheckBox checkIon = findViewById(R.id.checkIon);

        CheckBox checkYuki = findViewById(R.id.checkYuki);
        CheckBox checkYukikagoubutu = findViewById(R.id.checkYukikagoubutu);
        CheckBox checkHoukou = findViewById(R.id.checkHoukou);

        CheckBox checkKoubunsi = findViewById(R.id.checkKoubunsi);
        CheckBox checkTennen = findViewById(R.id.checkTennen);
        CheckBox checkGousei = findViewById(R.id.checkGousei);

        CheckBox checkHousoku = findViewById(R.id.checkHousoku);
        CheckBox checkKougyou = findViewById(R.id.checkKougyoutekiseihou);
        CheckBox checkHousokumondai = findViewById(R.id.checkHousokumondai);

        checkList.add(checkJotaiKousei);
        checkList.add(checkKitai);
        checkList.add(checkKotai);
        checkList.add(checkSankakangen);
        checkList.add(checkDenkibunkai);
        checkList.add(checkNetukagaku);
        checkList.add(checkHikinzoku);
        checkList.add(checkKinzoku);
        checkList.add(checkIon);
        checkList.add(checkYukikagoubutu);
        checkList.add(checkHoukou);
        checkList.add(checkTennen);
        checkList.add(checkGousei);
        checkList.add(checkHousokumondai);
        checkList.add(checkKougyou);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SharedPreferences preferences = getSharedPreferences("examnum", MODE_PRIVATE);
        questionNumber = preferences.getInt("examnum", 10);
        textView = findViewById(R.id.numText);
        textView.setText(String.valueOf(questionNumber));

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAll.isChecked()) {
                    for (int i = 0; i < checkList.size(); i++) {
                        checkList.get(i).setChecked(true);
                    }
                } else {
                    for (int i = 0; i < checkList.size(); i++) {
                        checkList.get(i).setChecked(false);
                    }
                }
            }
        });

        checkJoutai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkJoutai.isChecked()) {
                    checkJotaiKousei.setChecked(true);
                    checkKitai.setChecked(true);
                    checkKotai.setChecked(true);
                } else {
                    checkJotaiKousei.setChecked(false);
                    checkKitai.setChecked(false);
                    checkKotai.setChecked(false);
                }
            }
        });

        checkHannouHeikou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkHannouHeikou.isChecked()) {
                    checkSankakangen.setChecked(true);
                    checkDenkibunkai.setChecked(true);
                    checkNetukagaku.setChecked(true);
                } else {
                    checkSankakangen.setChecked(false);
                    checkDenkibunkai.setChecked(false);
                    checkNetukagaku.setChecked(false);
                }
            }
        });

        checkMuki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkMuki.isChecked()) {
                    checkHikinzoku.setChecked(true);
                    checkKinzoku.setChecked(true);
                    checkIon.setChecked(true);
                } else {
                    checkHikinzoku.setChecked(false);
                    checkKinzoku.setChecked(false);
                    checkIon.setChecked(false);
                }
            }
        });

        checkYuki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkYuki.isChecked()) {
                    checkYukikagoubutu.setChecked(true);
                    checkHoukou.setChecked(true);
                } else {
                    checkYukikagoubutu.setChecked(false);
                    checkHoukou.setChecked(false);
                }
            }
        });

        checkKoubunsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkKoubunsi.isChecked()) {
                    checkTennen.setChecked(true);
                    checkGousei.setChecked(true);
                } else {
                    checkTennen.setChecked(false);
                    checkGousei.setChecked(false);
                }
            }
        });

        checkHousoku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkHousoku.isChecked()) {
                    checkKougyou.setChecked(true);
                    checkHousokumondai.setChecked(true);
                } else {
                    checkKougyou.setChecked(false);
                    checkHousokumondai.setChecked(false);
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionNumber += 5;
                SharedPreferences preferences = getSharedPreferences("examnum", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("examnum", questionNumber);
                editor.apply();
                textView.setText(String.valueOf(questionNumber));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionNumber > 5) {
                    questionNumber -= 5;
                    SharedPreferences preferences = getSharedPreferences("examnum", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("examnum", questionNumber);
                    editor.apply();
                    textView.setText(String.valueOf(questionNumber));
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Exam.class);

                if (isNotCheckAll()) {
                    Toast.makeText(getApplicationContext(), "問題がセットされていません", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (checkJotaiKousei.isChecked()) {
                    intent.putExtra("JOUTAI", QuizData.getQuizData_bussitu());
                }
                if (checkKitai.isChecked()) {
                    intent.putExtra("KITAI", QuizData.getQuizData_kitai());
                }
                if (checkKotai.isChecked()) {
                    intent.putExtra("KOTAI", QuizData.getQuizData_kotai());
                }
                if (checkSankakangen.isChecked()) {
                    intent.putExtra("SANKAKANGEN", QuizData.getQuizData_sankakangen());
                }
                if (checkDenkibunkai.isChecked()) {
                    intent.putExtra("DENKIBUNKAI", QuizData.getQuizData_denki());
                }
                if (checkNetukagaku.isChecked()) {
                    intent.putExtra("NETUKAGAKU", QuizData.getQuizData_netsukagaku());
                }
                if (checkHikinzoku.isChecked()) {
                    intent.putExtra("HIKINZOKU", QuizData.getQuizData_hikinzoku());
                }
                if (checkKinzoku.isChecked()) {
                    intent.putExtra("KINZOKU", QuizData.getQuizData_kinzoku());
                }
                if (checkIon.isChecked()) {
                    intent.putExtra("ION", QuizData.getQuizData_kinzoku_ion());
                }
                if (checkYukikagoubutu.isChecked()) {
                    intent.putExtra("YUKI", QuizData.getQuizData_yuuki());
                }
                if (checkHoukou.isChecked()) {
                    intent.putExtra("HOUKOU", QuizData.getQuizData_houkou());
                }
                if (checkTennen.isChecked()) {
                    intent.putExtra("TENNEN", QuizData.getQuizData_tennen());
                }
                if (checkGousei.isChecked()) {
                    intent.putExtra("GOUSEI", QuizData.getQuizData_gousei());
                }
                if (checkHousokumondai.isChecked()) {
                    intent.putExtra("HOUSOKU", QuizData.getQuizData_housoku());
                }
                if (checkKougyou.isChecked()) {
                    intent.putExtra("KOUGYOU", QuizData.getQuizData_seihou());
                }

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isNotCheckAll() {
        for (int i = 0; i < checkList.size(); i++) {
            if (checkList.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }
}