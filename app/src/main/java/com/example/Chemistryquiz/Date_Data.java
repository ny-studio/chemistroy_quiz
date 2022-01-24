package com.example.Chemistryquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Date_Data extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    //でーたべーす名
    private static final String DATABASE_NAME = "DATE.db";
    private static final String TABLE_NAME = "testdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_MONTH = "month";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_TOTAL_SCORE = "total_score";
    private static final String COLUMN_NAME_CORRECT_SCORE = "correct_score";
    private static final String COLUMN_NAME_INCORRECT_SCORE = "incorrect_score";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_MONTH + " INTEGER," +
                    COLUMN_NAME_DATE + " INTEGER," +
                    COLUMN_NAME_TOTAL_SCORE + " INTEGER," +
                    COLUMN_NAME_CORRECT_SCORE + " INTEGER," +
                    COLUMN_NAME_INCORRECT_SCORE
                     + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    Date_Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}