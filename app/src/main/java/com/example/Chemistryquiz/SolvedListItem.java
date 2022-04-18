package com.example.Chemistryquiz;

import android.graphics.Bitmap;

public class SolvedListItem {
    private Bitmap mImage = null;
    private String mQuizNumber = null;
    private String mQuestionText = null;
    private String mQuestionAnswer = null;
    private String mSelectedAnswer = null;
    private String mQuestionExplanation = null;
    private String mJudge;

    /*コンストラクタ
        @param
        @param
    */

    public SolvedListItem(Bitmap Image, String quizNumber, String questionText, String questionAnswer, String questionExplanation, String selectedAnswer, String judge) {
        mImage = Image;
        mQuizNumber = quizNumber;
        mQuestionText = questionText;
        mQuestionAnswer = questionAnswer;
        mQuestionExplanation = questionExplanation;
        mSelectedAnswer = selectedAnswer;
        mJudge = judge;
    }

    /**
     * サムネイル画像を設定
     *
     * @param thumbnail サムネイル画像
     */
    public void setThumbnail(Bitmap Image) {
        mImage = Image;
    }

    //タイトルを設定
    public void setQuizNumber(String Title_Text) {
        mQuizNumber = Title_Text;
    }

    //問題テキストを設定
    public void setQuestionText(String Question_Text) {
        mQuestionText = Question_Text;
    }

    //問題答えを設定
    public void setQuestionAnswer(String Question_Answer) {
        mQuestionAnswer = Question_Answer;
    }

    //サムネイル画像を取得
    public Bitmap getmImage() {
        return mImage;
    }

    //問題テキストを取得
    public String getQuestionText() {
        return mQuestionText;
    }

    //答えを取得
    public String getQuestionAnswer() {
        return mQuestionAnswer;
    }

    //解説を取得
    public String getQuestionExplanation() {
        return mQuestionExplanation;
    }

    public String getQuizNumber() {
        return mQuizNumber;
    }

    public String getSelectedAnswer() {
        return mSelectedAnswer;
    }

    public String getJudge() {
        return mJudge;
    }
}
