package com.example.Chemistryquiz;

import android.graphics.Bitmap;

public class ListItem {
    private Bitmap mImage = null;
    private String mTitle_Text = null;
    private String mQuestion_Text = null;
    private String mQuestion_Answer = null;
    private String mQuestion_Explanation = null;

    //空のコンストラクタ

    public ListItem() {
    }

    ;

    /*コンストラクタ
        @param
        @param
    */

    public ListItem(Bitmap Image, String Title_Text, String Question_Text, String Question_Answer, String Question_Explanation) {
        mImage = Image;
        mTitle_Text = Title_Text;
        mQuestion_Text = Question_Text;
        mQuestion_Answer = Question_Answer;
        mQuestion_Explanation = Question_Explanation;
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
    public void setmTitle_Text(String Title_Text) {
        mTitle_Text = Title_Text;
    }

    //問題テキストを設定
    public void setmQuestion_Text(String Question_Text) {
        mQuestion_Text = Question_Text;
    }

    //問題答えを設定
    public void setmQuestion_Answer(String Question_Answer) {
        mQuestion_Answer = Question_Answer;
    }

    //サムネイル画像を取得
    public Bitmap getmImage() {
        return mImage;
    }

    //タイトルを取得
    public String getTitle() {
        return mTitle_Text;
    }

    //問題テキストを取得
    public String getQuestion_Text() {
        return mQuestion_Text;
    }

    //答えを取得
    public String getQuestion_Answer() {
        return mQuestion_Answer;
    }

    //解説を取得
    public String getQuestion_Explanation() {
        return mQuestion_Explanation;
    }
}
