package com.live.tv.mvp.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.City;
import com.live.tv.util.city.CityAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.SeachCityPresenter;
import com.live.tv.mvp.view.ISeachCityView;
import com.live.tv.util.SearchCity.LetterComparator;
import com.live.tv.util.SearchCity.PinnedHeaderDecoration;
import com.live.tv.util.db.SearchHistorySQLiteOpenHelper;
import com.live.tv.view.WaveSideBarView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class SearchCityFragment extends BaseFragment<ISeachCityView, SeachCityPresenter> implements ISeachCityView {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.edit_search)
    EditText etKey;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.side_view)
    WaveSideBarView mSideBarView;

    private SQLiteDatabase db;
    private SearchHistorySQLiteOpenHelper dbHelper;
    CityAdapter adapter;
    private List<City> list = new ArrayList<>();


    public static SearchCityFragment newInstance() {
        Bundle args = new Bundle();
        SearchCityFragment fragment = new SearchCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_search_city;
    }

    @Override
    public void initUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        dbHelper = new SearchHistorySQLiteOpenHelper(context);
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);
        indataCity();
        mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = adapter.getLetterPosition(letter);

                if (pos != -1) {
                    mRecyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });

        etKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    if (adapter != null) {
                        adapter.setNewData(list);
                    }
                } else {
                    adapter.setNewData(getHisttory(s.toString()));
                }
            }
        });


    }


    private void indataCity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Type listType = new TypeToken<ArrayList<City>>() {
                }.getType();
                Gson gson = new Gson();
                list = gson.fromJson(City.DATA, listType);
                Collections.sort(list, new LetterComparator());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter == null) {
                            adapter = new CityAdapter(context, list);
                            mRecyclerView.setAdapter(adapter);
                            adapter.setSearchCityLinstener(new CityAdapter.onSearchCityLinstener() {
                                @Override
                                public void onClick(String name) {
                                    BlackCity(name);
                                    finish();
                                }
                            });
                        } else {
                            adapter.setNewData(list);
                        }
                    }
                });
                hasData(list);
            }
        }).start();
    }


    @Override
    public void initData() {

    }

    @Override
    public SeachCityPresenter createPresenter() {
        return new SeachCityPresenter(getApp(), context);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }


    /**
     * 检查数据库中是否已经有该条记录
     */
    private void hasData(List<City> list) {
        List<String> historys = null;
        try {
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "select name from city order by id desc", null);
            historys = new ArrayList<>();
            while (cursor.moveToNext()) {
                historys.add(cursor.getString(cursor.getColumnIndex("name")));
            }
            cursor.close();
            if (historys.size() == 0) {
                insertData(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     */
    private void insertData(List<City> list) {
        db = dbHelper.getWritableDatabase();
        for (int i = 0; i < list.size(); i++) {
            db.execSQL("insert into city(name,pys,pyf) values('" + list.get(i).name + "','" + list.get(i).pys + "','" + list.get(i).pyf + "')");
        }
        db.close();
    }

    /**
     * 倒叙查询所有记录
     */
    private List<City> getHisttory(String tempName) {

//        List<String> historys = null;
        List<City> historys = null;
        try {
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "select * from city  where name like ? or pys like ? or pyf like ?", new String[]{"%" + tempName + "%",
                            "%" + tempName + "%",
                            "%" + tempName + "%"});
            historys = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    City city = new City();
                    city.name = cursor.getString(cursor.getColumnIndex("name"));
                    if (TextUtils.isEmpty(city.name)||"null".equals(city.name)) {
                        continue;
                    }
                    city.pys = cursor.getString(cursor.getColumnIndex("pys"));
                    city.pyf = cursor.getString(cursor.getColumnIndex("pyf"));

//                historys.add(cursor.getString(cursor.getColumnIndex("name")));
                    historys.add(city);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return historys;
    }

    private void clickSearch() {
        if (checkInputKey()) {
            String trim = etKey.getText().toString().trim();
            adapter.setNewData(getHisttory(trim));
            hideInputMethod(etKey);

        }
    }

    private boolean checkInputKey() {
        if (StringUtils.isBlank(etKey.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.tips_search_keywords_cannot_be_empty);
            return false;
        }
        return true;
    }



    @OnClick({R.id.ivLeft, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.btn_search:
                clickSearch();
                break;

        }
    }
}
