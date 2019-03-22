package com.live.tv.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wql on 2016/12/14.
 */

public class SearchHistorySQLiteOpenHelper extends SQLiteOpenHelper {
    private static String name = "temp.db";
    private static Integer version = 1;

    public SearchHistorySQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
        sqLiteDatabase.execSQL("create table city   (id integer primary key autoincrement,name varchar(200),pys varchar(10),pyf varchar(50))");
        sqLiteDatabase.execSQL("create table string (id integer primary key autoincrement,name varchar(200),isOn varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
