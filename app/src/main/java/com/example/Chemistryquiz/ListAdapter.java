package com.example.Chemistryquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ListItem> {
    private int mResource;
    private List<ListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     *
     * @param context  コンテキスト
     * @param resource リソースID
     * @param items    リストビューの要素
     */
    public ListAdapter(Context context, int resource, List<ListItem> items) {
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
        ListItem item = mItems.get(position);
        //サムネイル画像を設定
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageBitmap(item.getmImage());

        // タイトルを設定
        TextView Title_Text = (TextView) view.findViewById(R.id.title_text);
        Title_Text.setText(item.getTitle());

        // 問題を設定
        TextView Question_Text = (TextView) view.findViewById(R.id.question_text);
        Question_Text.setText(item.getQuestion_Text());

        //答えを設定
        TextView Question_Answer = (TextView) view.findViewById(R.id.question_answer);
        Question_Answer.setText("答え : " + item.getQuestion_Answer());

        //解説を設定
        TextView Question_Exaplanation = (TextView) view.findViewById(R.id.question_explanation);
        if (item.getQuestion_Explanation() != "") {
            Question_Exaplanation.setText("解説 : " + item.getQuestion_Explanation());
        } else {
            Question_Exaplanation.setText("");
        }

        return view;
    }
}
