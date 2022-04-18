package com.example.Chemistryquiz;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Glaph extends AppCompatActivity {

    LineChart Chart;
    XAxis xAxis;
    List<Entry> entry1;
    List<Entry> entry2;
    List<Entry> entry3;
    private Date_Data helper;
    private SQLiteDatabase db;
    Cursor cursor;
    int color1;
    int color2;
    int color3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("グラフ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        entry1 = new ArrayList<>();
        entry2 = new ArrayList<>();
        entry3 = new ArrayList<>();

        color1 = getResources().getColor(android.R.color.holo_green_light);
        color2 = getResources().getColor(android.R.color.holo_red_light);
        color3 = getResources().getColor(android.R.color.holo_blue_light);

        Chart = (LineChart) findViewById(R.id.bar);


        // Chart.setOnChartGestureListener(Glaph.this);
        // Chart.setOnChartValueSelectedListener(Glaph.this);

        //グラフの土台
        Chart.setDragEnabled(true);
        Chart.setScaleXEnabled(true);
        Chart.setScaleYEnabled(false);
        Chart.getAxisLeft().setAxisMinValue(0);
        Chart.getAxisLeft().setStartAtZero(true);
        Chart.getAxisRight().setEnabled(false);
        //x軸の設定
        xAxis = Chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        set_cursor();

        //日付だけ先にセットして値だけ後から

        int rowcount = cursor.getCount();
        ArrayList<String> Value = new ArrayList<>();

        entry1.add(new Entry(0, 0));
        entry2.add(new Entry(0, 0));
        entry3.add(new Entry(0, 0));

        cursor.moveToFirst();
        //グラフ用データ
        for (int i = 0; i < rowcount; i++) {
            Value.add(cursor.getString(0) + "/" + cursor.getInt(1));
            entry1.add(new Entry(i, cursor.getInt(2)));
            entry2.add(new Entry(i, cursor.getInt(3)));
            entry3.add(new Entry(i, cursor.getInt(4)));
            cursor.moveToNext();
        }
        cursor.close();

        LineDataSet lDataSet1 = createSet("回答数", color1, entry1);
        LineDataSet lDataSet2 = createSet("正解数", color2, entry2);
        LineDataSet lDataSet3 = createSet("不正解数", color3, entry3);

        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Value));

        final LineData linedata = new LineData(lDataSet1, lDataSet2, lDataSet3);

        Chart.setData(linedata);
        Chart.setVisibleXRangeMaximum(5);
        Chart.moveViewToX(linedata.getEntryCount() - 6);
        Chart.invalidate();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void set_cursor() {
        if (helper == null) {
            helper = new Date_Data(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        cursor = db.query(
                "testdb", // DB名
                new String[]{"month", "date", "total_score", "correct_score", "incorrect_score"}, // 取得するカラム名
                null, // WHERE句の列名
                null, // WHERE句の値
                null, // GROUP BY句の値
                null, // HAVING句の値
                null // ORDER BY句の値
        );
    }

    private LineDataSet createSet(String label, int color, List<Entry> entry) {
        LineDataSet set = new LineDataSet(entry, label);
        set.setColor(color);
        set.setCircleColor(color);
        set.setCircleColorHole(color);
        set.setValueTextSize(12f);
        set.setCircleSize(5f);
        return set;
    }

    private void Today() {
        Calendar calendar = Calendar.getInstance();

        int Year = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH) + 1;//返り値が0-11であることに注意
        int Date = calendar.get(Calendar.DATE);

        //日付を保存
        DateStore(Month, Date);

        String Today = Year + "/" + Month + "/" + Date;

    }

    //今日の日付を取得するメソッド
    private int Today_Date() {
        Calendar calendar = Calendar.getInstance();
        int Date = calendar.get(Calendar.DATE);
        return Date;
    }

    //今日の月を取得するメソッド
    private int Today_Month() {
        Calendar calendar = Calendar.getInstance();
        int Month = calendar.get(Calendar.MONTH) + 1;//返り値が0-11であることに注意
        return Month;
    }

    //今日の月日を保存するメソッド
    public void DateStore(int Month, int Date) {
        SharedPreferences DateStore = getSharedPreferences("DateStore", MODE_PRIVATE);
        SharedPreferences.Editor editor = DateStore.edit();
        // Key: input, value: text
        editor.putInt("Date", Date);
        editor.putInt("Month", Month);
        //editor.commit();
        editor.apply();

        /* 読み出し
        String dateString = dateStore.getString("DateString", null);
        int dateInt = dateStore.getInt("DateInt", 0);
        */
    }
}

