package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.EditAllergyContentPresenter;
import com.live.tv.mvp.view.home.IEditAllergrContentView;
import com.live.tv.util.LoadingUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 编辑过敏
 * Created by sh-lx on 2017/7/12.
 */

public class editAllergyContentFragment extends BaseFragment<IEditAllergrContentView,EditAllergyContentPresenter>implements IEditAllergrContentView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.tv_show_textnumber)
            TextView mShowtextnumber;
    Unbinder unbinder;
    private String health_record_id;
    private String content;
    private int num=100;

    public static editAllergyContentFragment newInstance(String content,String health_record_id) {

        Bundle args = new Bundle();
        editAllergyContentFragment fragment = new editAllergyContentFragment();
        fragment.content = content;
        fragment.health_record_id = health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_edit_allergy_content;
    }

    @Override
    public void initUI() {
        tvTitle.setText("编辑过敏史");
        mShowtextnumber.setText("限定字数(0/100)");
    }

    @Override
    public void initData() {

        if (!content.equals("")){

           // ed1.setText(content);

            ed1.addTextChangedListener(new TextWatcher() {
                private CharSequence wordNum;//记录输入的字数
                private int selectionStart;
                private int selectionEnd;

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    wordNum = s;//实时记录输入的字数
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mShowtextnumber.setText("限定字数(" + s.length() + "/100)");
                    selectionStart = ed1.getSelectionStart();
                    selectionEnd = ed1.getSelectionEnd();
                    if (wordNum.length() > num) {
                        //删除多余输入的字（不会显示出来）
                        s.delete(selectionStart - 1, selectionEnd);
                        int tempSelection = selectionEnd;
                        ed1.setText(s);
                        ed1.setSelection(tempSelection);//设置光标在最后
                    }
                }
            });
        }
    }

    @OnClick({R.id.ivLeft,R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tv_submit:
                if (TextUtils.isEmpty(ed1.getText().toString())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入过敏史");
                    return;
                }

                Map<String,String> map =  new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("health_record_id",health_record_id);
                map.put("allergy_desc",ed1.getText().toString());
                getPresenter().UpdateRecord(map);
                LoadingUtil.showLoadingNew(context,"保存中...");
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
    public EditAllergyContentPresenter createPresenter() {
        return new EditAllergyContentPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onUpdateRecord(String data) {
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context.getApplicationContext(),data);
        getActivity().setResult(1,new Intent().putExtra("success","success"));
        finish();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LoadingUtil.hideLoading();
        errorHandle(e);
    }
}
