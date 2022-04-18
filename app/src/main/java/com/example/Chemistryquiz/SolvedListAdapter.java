package com.example.Chemistryquiz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SolvedListAdapter extends ArrayAdapter<SolvedListItem> {
    private int mResource;
    private List<SolvedListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     *
     * @param context  コンテキスト
     * @param resource リソースID
     * @param items    リストビューの要素
     */
    public SolvedListAdapter(Context context, int resource, List<SolvedListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }
        // リストビューに表示する要素を取得
        SolvedListItem item = mItems.get(position);

        ImageView image = (ImageView) view.findViewById(R.id.questionImage);
        TextView QuestionText = (TextView) view.findViewById(R.id.question_text);

        if(item.getmImage() == null) {
            image.setImageBitmap(null);
            //問題を設定
            QuestionText.setText(item.getQuestionText());
        }else{
            image.setImageBitmap(item.getmImage());
            //問題を設定
            QuestionText.setText("");
        }

        //答えを設定
        TextView QuestionAnswer = (TextView) view.findViewById(R.id.question_answer);
        QuestionAnswer.setText("答え : " + item.getQuestionAnswer());

        //解説を設定
        TextView QuestionExaplanation = (TextView) view.findViewById(R.id.question_explanation);
        if (!item.getQuestionExplanation().equals("")) {
            QuestionExaplanation.setText("解説 : " + item.getQuestionExplanation());
        } else {
            QuestionExaplanation.setText("");
        }

        //あなたの回答を設定
        TextView selectedAnswer = (TextView) view.findViewById(R.id.question_selected_answer);
        selectedAnswer.setText("あなたの回答 : " + item.getSelectedAnswer());

        TextView judge = (TextView) view.findViewById(R.id.quiz_mark);
        judge.setText(item.getJudge());
        if (item.getJudge().equals("〇")) {
            judge.setTextColor(Color.RED);
            selectedAnswer.setTextColor(Color.RED);
        } else {
            judge.setTextColor(Color.BLUE);
            selectedAnswer.setTextColor(Color.BLUE);
        }

        return view;
    }
}
