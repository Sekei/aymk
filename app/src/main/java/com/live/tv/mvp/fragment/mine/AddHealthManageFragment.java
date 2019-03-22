package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.AddHealthManagePresenter;
import com.live.tv.mvp.view.mine.IAddHealthManageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/9/18.
 * 添加健康数据
 */

public class AddHealthManageFragment extends BaseFragment<IAddHealthManageView, AddHealthManagePresenter> implements IAddHealthManageView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private String mtype;
    @BindView(R.id.ed_content)
    EditText editText;
    private HealthManagerBeans beans;
    String[] languages;

    Unbinder unbinder;

    public static AddHealthManageFragment newInstance(HealthManagerBeans healthId) {

        AddHealthManageFragment fragment = new AddHealthManageFragment();

        fragment.beans = healthId;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_addhealth;
    }

    @Override
    public void initUI() {
        languages = getResources().getStringArray(R.array.spinner_content);

        if (beans != null) {
            mtype=beans.getService_title();
            for (int i = 0; i < languages.length; i++) {

                if (beans.getService_title().equals(languages[i])) {
                    spinner.setSelection(i, true);
                    return;
                }

                etPrice.setText(beans.getService_price());
                editText.setText(beans.getService_desc());
            }

        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mtype = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public AddHealthManagePresenter createPresenter() {
        return new AddHealthManagePresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(getActivity(), e.getMessage());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.ivLeft, R.id.tv_save})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty(mtype)) {
                    ToastUtils.showToast(getActivity(), "请选择服务期限");
                    return;
                }
                if (TextUtils.isEmpty(etPrice.getText().toString())) {
                    ToastUtils.showToast(getActivity(), "请输入价格");

                    return;
                }
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    ToastUtils.showToast(getActivity(), "请输入服务内容");


                    return;
                }


                try {
                    Map<String, String> map = new HashMap<>();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("service_price", etPrice.getText().toString());
                    map.put("service_title", mtype);
                    map.put("service_desc", editText.getText().toString());
                    map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
                    if (beans != null) {
                        map.put("health_manager_id", beans.getHealth_manager_id());
                        getPresenter().getChangeservice(map);
                    } else {
                        getPresenter().getaddservice(map);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    startLogin();
                    finish();
                }


                break;
        }
    }

    @Override
    public void onaddservice(String str) {
        ToastUtils.showToast(getActivity(), str);
        finish();
    }
}
