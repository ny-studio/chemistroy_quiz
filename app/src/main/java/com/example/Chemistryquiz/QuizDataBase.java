package com.example.Chemistryquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Console;

public class QuizDataBase extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 4;

    // データーベース情報を変数に格納
    private static final String DATABASE_NAME = "quizrepeat.db";
    private static final String TABLE_NAME0 = "quizdb0";//金属
    private static final String TABLE_NAME0_1 = "quizdb0_1";//金属イオン
    private static final String TABLE_NAME1 = "quizdb1";//非金属
    private static final String TABLE_NAME2 = "quizdb2";//有機
    private static final String TABLE_NAME3 = "quizdb3";//芳香
    private static final String TABLE_NAME4 = "quizdb4";//てんねん
    private static final String TABLE_NAME5 = "quizdb5";//ごうせい
    private static final String TABLE_NAME6 = "quizdb6";//気体
    private static final String TABLE_NAME7 = "quizdb7";//物質の構成
    private static final String TABLE_NAME8 = "quizdb8";//固体
    private static final String TABLE_NAME9 = "quizdb9";//酸化還元
    private static final String TABLE_NAME10 = "quizdb10";//電気分解
    private static final String TABLE_NAME11 = "quizdb11";//熱化学
    private static final String TABLE_NAME12 = "quizdb12";//化学平衡
    private static final String TABLE_NAME13 = "quizdb13";//工業的製法
    private static final String TABLE_NAME14 = "quizdb14";//法則
    private static final String TABLE_NAME15 = "quizdb15";
    private static final String TABLE_NAME16 = "quizdb16";
    private static final String TABLE_NAME17 = "quizdb17";
    private static final String TABLE_NAME18 = "quizdb18";
    private static final String TABLE_NAME19 = "quizdb19";
    private static final String TABLE_NAME20 = "quizdb20";
    private static final String TABLE_NAME21 = "quizdb21";
    private static final String right0 = "rightqs0";
    private static final String right0_1 = "rightqs0_1";
    private static final String right1 = "rightqs1";
    private static final String right2 = "rightqs2";
    private static final String right3 = "rightqs3";
    private static final String right4 = "rightqs4";
    private static final String right5 = "rightqs5";
    private static final String right6 = "rightqs6";
    private static final String right7 = "rightqs7";
    private static final String right8 = "rightqs8";
    private static final String right9 = "rightqs9";
    private static final String right10 = "rightqs10";
    private static final String right11 = "rightqs11";
    private static final String right12 = "rightqs12";
    private static final String right13 = "rightqs13";
    private static final String right14 = "rightqs14";
    private static final String right15 = "rightqs15";
    private static final String right16 = "rightqs16";
    private static final String right17 = "rightqs17";
    private static final String right18 = "rightqs18";
    private static final String right19 = "rightqs19";
    private static final String right20 = "rightqs20";
    private static final String right21 = "rightqs21";
    private static final String _ID = "_id";
    private static final String SQL_CREATE_ENTRIES0 = "CREATE TABLE " + TABLE_NAME0 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES0 = "DROP TABLE IF EXISTS " + TABLE_NAME0;

    private static final String SQL_CREATE_ENTRIES0_1 = "CREATE TABLE " + TABLE_NAME0_1 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES0_1 = "DROP TABLE IF EXISTS " + TABLE_NAME0_1;

    private static final String SQL_CREATE_ENTRIES1 = "CREATE TABLE " + TABLE_NAME1 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;

    private static final String SQL_CREATE_ENTRIES2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;

    private static final String SQL_CREATE_ENTRIES3 = "CREATE TABLE " + TABLE_NAME3 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES3 = "DROP TABLE IF EXISTS " + TABLE_NAME3;

    private static final String SQL_CREATE_ENTRIES4 = "CREATE TABLE " + TABLE_NAME4 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES4 = "DROP TABLE IF EXISTS " + TABLE_NAME4;

    private static final String SQL_CREATE_ENTRIES5 = "CREATE TABLE " + TABLE_NAME5 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES5 = "DROP TABLE IF EXISTS " + TABLE_NAME5;

    private static final String SQL_CREATE_ENTRIES6 = "CREATE TABLE " + TABLE_NAME6 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES6 = "DROP TABLE IF EXISTS " + TABLE_NAME6;

    private static final String SQL_CREATE_ENTRIES7 = "CREATE TABLE " + TABLE_NAME7 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES7 = "DROP TABLE IF EXISTS " + TABLE_NAME7;

    private static final String SQL_CREATE_ENTRIES8 = "CREATE TABLE " + TABLE_NAME8 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES8 = "DROP TABLE IF EXISTS " + TABLE_NAME8;

    private static final String SQL_CREATE_ENTRIES9 = "CREATE TABLE " + TABLE_NAME9 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES9 = "DROP TABLE IF EXISTS " + TABLE_NAME9;

    private static final String SQL_CREATE_ENTRIES10 = "CREATE TABLE " + TABLE_NAME10 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES10 = "DROP TABLE IF EXISTS " + TABLE_NAME10;

    private static final String SQL_CREATE_ENTRIES11 = "CREATE TABLE " + TABLE_NAME11 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES11 = "DROP TABLE IF EXISTS " + TABLE_NAME11;

    private static final String SQL_CREATE_ENTRIES12 = "CREATE TABLE " + TABLE_NAME12 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES12 = "DROP TABLE IF EXISTS " + TABLE_NAME12;

    private static final String SQL_CREATE_ENTRIES13 = "CREATE TABLE " + TABLE_NAME13 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES13 = "DROP TABLE IF EXISTS " + TABLE_NAME13;

    private static final String SQL_CREATE_ENTRIES15 = "CREATE TABLE " + TABLE_NAME15 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES15 = "DROP TABLE IF EXISTS " + TABLE_NAME15;

    private static final String SQL_CREATE_ENTRIES16 = "CREATE TABLE " + TABLE_NAME16 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES16 = "DROP TABLE IF EXISTS " + TABLE_NAME16;

    private static final String SQL_CREATE_ENTRIES17 = "CREATE TABLE " + TABLE_NAME17 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT,"+ " explanation" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES17 = "DROP TABLE IF EXISTS " + TABLE_NAME17;
    private static final String SQL_CREATE_ENTRIES18 = "CREATE TABLE " + TABLE_NAME18 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES18 = "DROP TABLE IF EXISTS " + TABLE_NAME18;
    private static final String SQL_CREATE_ENTRIES19 = "CREATE TABLE " + TABLE_NAME19 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES19 = "DROP TABLE IF EXISTS " + TABLE_NAME19;
    private static final String SQL_CREATE_ENTRIES20 = "CREATE TABLE " + TABLE_NAME20 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES20 = "DROP TABLE IF EXISTS " + TABLE_NAME20;
    private static final String SQL_CREATE_ENTRIES21 = "CREATE TABLE " + TABLE_NAME21 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_DELETE_ENTRIES21 = "DROP TABLE IF EXISTS " + TABLE_NAME21;


    private static final String SQL_RIGHTQS0 = "CREATE TABLE " + right0 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL0 = "DROP TABLE IF EXISTS " + right0;
    private static final String SQL_RIGHTQS0_1 = "CREATE TABLE " + right0_1 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL0_1 = "DROP TABLE IF EXISTS " + right0_1;
    private static final String SQL_RIGHTQS1 = "CREATE TABLE " + right1 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL1 = "DROP TABLE IF EXISTS " + right1;
    private static final String SQL_RIGHTQS2 = "CREATE TABLE " + right2 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL2 = "DROP TABLE IF EXISTS " + right2;
    private static final String SQL_RIGHTQS3 = "CREATE TABLE " + right3 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL3 = "DROP TABLE IF EXISTS " + right3;
    private static final String SQL_RIGHTQS4 = "CREATE TABLE " + right4 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL4 = "DROP TABLE IF EXISTS " + right4;
    private static final String SQL_RIGHTQS5 = "CREATE TABLE " + right5 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL5 = "DROP TABLE IF EXISTS " + right5;
    private static final String SQL_RIGHTQS6 = "CREATE TABLE " + right6 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL6 = "DROP TABLE IF EXISTS " + right6;
    private static final String SQL_RIGHTQS7 = "CREATE TABLE " + right7 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL7 = "DROP TABLE IF EXISTS " + right7;
    private static final String SQL_RIGHTQS8 = "CREATE TABLE " + right8 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL8 = "DROP TABLE IF EXISTS " + right8;
    private static final String SQL_RIGHTQS9 = "CREATE TABLE " + right9 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL9 = "DROP TABLE IF EXISTS " + right9;
    private static final String SQL_RIGHTQS10 = "CREATE TABLE " + right10 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL10 = "DROP TABLE IF EXISTS " + right10;
    private static final String SQL_RIGHTQS11 = "CREATE TABLE " + right11 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL11 = "DROP TABLE IF EXISTS " + right11;
    private static final String SQL_RIGHTQS12 = "CREATE TABLE " + right12 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL12 = "DROP TABLE IF EXISTS " + right12;
    private static final String SQL_RIGHTQS13 = "CREATE TABLE " + right13 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL13 = "DROP TABLE IF EXISTS " + right13;
    private static final String SQL_RIGHTQS14 = "CREATE TABLE " + right14 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL14 = "DROP TABLE IF EXISTS " + right14;
    private static final String SQL_RIGHTQS15 = "CREATE TABLE " + right15 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL15 = "DROP TABLE IF EXISTS " + right15;
    private static final String SQL_RIGHTQS16 = "CREATE TABLE " + right16 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT," + " explanation" + " TEXT)";
    private static final String SQL_RIGHTDEL16 = "DROP TABLE IF EXISTS " + right16;
    private static final String SQL_RIGHTQS17 = "CREATE TABLE " + right17 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_RIGHTDEL17 = "DROP TABLE IF EXISTS " + right17;
    private static final String SQL_RIGHTQS18 = "CREATE TABLE " + right18 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_RIGHTDEL18 = "DROP TABLE IF EXISTS " + right18;
    private static final String SQL_RIGHTQS19 = "CREATE TABLE " + right19 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_RIGHTDEL19 = "DROP TABLE IF EXISTS " + right19;
    private static final String SQL_RIGHTQS20 = "CREATE TABLE " + right20 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_RIGHTDEL20 = "DROP TABLE IF EXISTS " + right20;
    private static final String SQL_RIGHTQS21 = "CREATE TABLE " + right21 + " (" +
            _ID + " INTEGER PRIMARY KEY," + "qs" + " TEXT," + "quiz1" + " TEXT," + "quiz2" + " TEXT," + "quiz3" + " TEXT," + "quiz4" + " TEXT)";
    private static final String SQL_RIGHTDEL21 = "DROP TABLE IF EXISTS " + right21;

    QuizDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES0);
        db.execSQL(SQL_CREATE_ENTRIES0_1);
        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);
        db.execSQL(SQL_CREATE_ENTRIES3);
        db.execSQL(SQL_CREATE_ENTRIES4);
        db.execSQL(SQL_CREATE_ENTRIES5);
        db.execSQL(SQL_CREATE_ENTRIES6);
        db.execSQL(SQL_CREATE_ENTRIES7);
        db.execSQL(SQL_CREATE_ENTRIES8);
        db.execSQL(SQL_CREATE_ENTRIES9);
        db.execSQL(SQL_CREATE_ENTRIES10);
        db.execSQL(SQL_CREATE_ENTRIES11);
        db.execSQL(SQL_CREATE_ENTRIES12);
        db.execSQL(SQL_CREATE_ENTRIES13);
        db.execSQL(SQL_CREATE_ENTRIES15);
        db.execSQL(SQL_CREATE_ENTRIES16);
        db.execSQL(SQL_RIGHTQS0);
        db.execSQL(SQL_RIGHTQS0_1);
        db.execSQL(SQL_RIGHTQS1);
        db.execSQL(SQL_RIGHTQS2);
        db.execSQL(SQL_RIGHTQS3);
        db.execSQL(SQL_RIGHTQS4);
        db.execSQL(SQL_RIGHTQS5);
        db.execSQL(SQL_RIGHTQS6);
        db.execSQL(SQL_RIGHTQS7);
        db.execSQL(SQL_RIGHTQS8);
        db.execSQL(SQL_RIGHTQS9);
        db.execSQL(SQL_RIGHTQS10);
        db.execSQL(SQL_RIGHTQS11);
        db.execSQL(SQL_RIGHTQS12);
        db.execSQL(SQL_RIGHTQS13);
        db.execSQL(SQL_RIGHTQS15);
        db.execSQL(SQL_RIGHTQS16);
    }

    // 参考：https://sankame.github.io/blog/2017-09-05-android_sqlite_db_upgrade/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( oldVersion < 2){
            db.execSQL("ALTER TABLE " + TABLE_NAME0 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME0_1 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME1 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME2 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME3 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME4 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME5 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME6 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME7 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME8 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME9 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME10 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME11 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME12 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME13 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + TABLE_NAME15 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right0 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right0_1 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right1 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right2 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right3 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right4 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right5 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right6 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right7 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right8 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right9 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right10 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right11 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right12 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right13 + " ADD explanation TEXT DEFAULT ' '");
            db.execSQL("ALTER TABLE " + right15 + " ADD explanation TEXT DEFAULT ' '");
        }

        if( oldVersion < 4){
            db.execSQL(SQL_DELETE_ENTRIES16);
            db.execSQL(SQL_RIGHTDEL16);
            db.execSQL(SQL_CREATE_ENTRIES16);
            db.execSQL(SQL_RIGHTQS16);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
