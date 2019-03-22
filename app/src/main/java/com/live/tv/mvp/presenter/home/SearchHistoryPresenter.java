package com.live.tv.mvp.presenter.home;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.live.tv.App;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.home.ISearchHistoryView;
import com.live.tv.util.db.SearchHistorySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by taoh on 2017/7/20.
 */

public class SearchHistoryPresenter extends BasePresenter<ISearchHistoryView> {

    private SQLiteDatabase db;
    private SearchHistorySQLiteOpenHelper dbHelper;


    public SearchHistoryPresenter(App app, Context mContext) {
        super(app);
        dbHelper = new SearchHistorySQLiteOpenHelper(mContext);
    }

    public void insert(String word) {
        if (!hasData(word)) {
            insertData(word);
            getAllHistory();
        }
    }

    public void deleteAll() {
        deleteData();
        getView().showHistory(false, null);
    }

    public void getAllHistory() {
        List<String> histtory = getHisttory();
        getView().showHistory(true, histtory);
    }
    /**
     * 清空数据
     */
    private void deleteData() {
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE from records");
        db.close();
    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = dbHelper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        boolean b;
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "select id ,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        b = cursor.moveToNext();
        cursor.close();
        return b;
    }

    /**
     * 倒叙查询所有记录
     */
    private List<String> getHisttory() {

        List<String> historys = null;
        try {
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "select name from records order by id desc", null);
            historys = new ArrayList<>();
            while (cursor.moveToNext()) {
                historys.add(cursor.getString(cursor.getColumnIndex("name")));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return historys;
    }





}
