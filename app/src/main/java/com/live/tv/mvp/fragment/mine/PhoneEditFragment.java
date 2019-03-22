package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.EventBusMessage;
import com.live.tv.mvp.adapter.mine.PhoneEditAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.home.ConsultWeek;
import com.live.tv.mvp.presenter.mine.PhoneEditPresenter;
import com.live.tv.mvp.view.mine.IPhoneEditView;
import com.live.tv.util.LoadingUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class PhoneEditFragment extends BaseFragment<IPhoneEditView, PhoneEditPresenter> implements IPhoneEditView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ed_price)
    EditText ed_price;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String doctor_id = "";
    private String price = "";
    private PhoneEditAdapter mAdapter;

    public static PhoneEditFragment newInstance(String price, String doctor_id) {

        Bundle args = new Bundle();

        PhoneEditFragment fragment = new PhoneEditFragment();
        fragment.doctor_id = doctor_id;
        fragment.price = price;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_phone_edit;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.service_setting);
        tvRight.setText(R.string.save);
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));
        tvRight.setVisibility(View.VISIBLE);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new PhoneEditAdapter(new ArrayList<ConsultWeek>());
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ConsultWeek consultWeek=mAdapter.getData().get(position);
                startTimeSettingFragment(consultWeek.getConsult_set_day(),"phone");
            }
        });

    }

    @Override
    public void initData() {

        if (!"".equals(price)) {

            ed_price.setText(price);
        }



    }


    @Override
    public void onResume() {
        super.onResume();

        Map<String, String> map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", doctor_id);
        map.put("consult_type", "phone");
        getPresenter().GetConsultTimePage(map);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public PhoneEditPresenter createPresenter() {
        return new PhoneEditPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:

                finish();
                break;
            case R.id.tvRight:
                Map<String, String> map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("doctor_id", doctor_id);
                map.put("phone_price", ed_price.getText().toString().trim());
                getPresenter().setConsultPrice(map);

                LoadingUtil.showLoading(context, "保存中...");
                break;
        }
    }

    @Override
    public void onConsultPrice(String data) {

        LoadingUtil.hideLoading();
        if (data != null) {
            ToastUtils.showToast(context.getApplicationContext(), data);
            EventBus.getDefault().post(new EventBusMessage("setting_success"));
        }
    }

    @Override
    public void onConsultTimePage(List<ConsultWeek> data) {

        if (data!=null){
            mAdapter.setNewData(data);
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

        errorHandle(e);
    }
}
