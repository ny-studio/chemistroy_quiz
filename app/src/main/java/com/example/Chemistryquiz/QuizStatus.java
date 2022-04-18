package com.example.Chemistryquiz;

import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class QuizStatus extends AppCompatActivity implements Serializable {
    private String wrongQuizDBName, rightQuizDBName, titleText;
    private String[][] quizData;
    private Class previousClass;

    public QuizStatus() {
    }

    public QuizStatus(String[][] quizData, String wrongQuizDBName, String rightQuizDBName, String titleText, Class previousClass) {
        this.quizData = quizData;
        this.wrongQuizDBName = wrongQuizDBName;
        this.rightQuizDBName = rightQuizDBName;
        this.titleText = titleText;
        this.previousClass = previousClass;
    }

    //問題をえらぶメソッド
    public void setQuizData(String[][] quizdata) {
        this.quizData = quizdata;
    }

    //問題をとりだすメソッド
    protected String[][] getQuizData() {
        return this.quizData;
    }

    public String getWrongQuizDBName() {
        return this.wrongQuizDBName;
    }

    public String getRightQuizDBName() {
        return this.rightQuizDBName;
    }

    protected String getTitleText() {
        return this.titleText;
    }

    //とりだす
    public Class getbeforeClass() {
        return this.previousClass;
    }

    // アクションバーを表示するメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}