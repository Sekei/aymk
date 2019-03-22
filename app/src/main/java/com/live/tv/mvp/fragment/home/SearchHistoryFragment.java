package com.live.tv.mvp.fragment.home;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.SearchHistoryPresenter;
import com.live.tv.mvp.view.home.ISearchHistoryView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class SearchHistoryFragment extends BaseFragment<ISearchHistoryView,SearchHistoryPresenter>implements ISearchHistoryView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.search_clear)
    TextView searchClear;
    Unbinder unbinder;
    @BindView(R.id.mFlexboxLayout2)
    FlexboxLayout mFlexboxLayout2;

    public static SearchHistoryFragment newInstance() {
        Bundle args = new Bundle();
        SearchHistoryFragment fragment = new SearchHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
        }
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_search_history;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        getPresenter().getAllHistory();
    }

    @OnClick({R.id.ivLeft,R.id.btn_search,R.id.search_clear})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.btn_search:
                if (checkInputKey()) {
                    getPresenter().insert(editSearch.getText().toString());
                    startSearchFragment(editSearch.getText().toString());
                }
                break;
            case R.id.search_clear:
                //删除历史搜索
                getPresenter().deleteAll();
                getPresenter().getAllHistory();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SearchHistoryPresenter createPresenter() {
        return new SearchHistoryPresenter(getApp(),context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showHistory(boolean show, List<String> data) {
        if (data!=null){
            addHistory(data,mFlexboxLayout2);
        }
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


    private boolean checkInputKey() {
        if (StringUtils.isBlank(editSearch.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入关键搜索");
            return false;
        }

        return true;
    }
    public void addHistory(List<String> data,
                           FlexboxLayout mFlexboxLayout_hot) {
        if (mFlexboxLayout_hot == null) return;
        mFlexboxLayout_hot.removeAllViews();
        for (final String bean : data) {
            final TextView word = new TextView(getContext());
            word.setTextColor(getContext().getResources().getColor(R.color.tx_l));
            word.setText(bean);
            word.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.y26));
            word.setBackgroundResource(R.drawable.shape_search_hui);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(12, 5, 12, 5);
            word.setLayoutParams(lp);

            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editSearch.setText(bean);
                   //跳转搜索总页面
                    startSearchFragment(editSearch.getText().toString());
                }
            });

            mFlexboxLayout_hot.addView(word);
        }
    }


}
