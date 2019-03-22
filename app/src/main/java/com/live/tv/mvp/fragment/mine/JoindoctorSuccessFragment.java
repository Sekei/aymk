package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.JoinSuccessPresenter;
import com.live.tv.mvp.view.mine.IJoinSuccessView;
import com.live.tv.util.CustomDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 医生入住  成功信息
 *
 * @author Created by stone
 * @since 2018/2/1
 */

public class JoindoctorSuccessFragment extends BaseFragment<IJoinSuccessView, JoinSuccessPresenter> implements IJoinSuccessView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private String type;
    private String htmlurl;
    public static JoindoctorSuccessFragment newInstance(String type) {
        Bundle args = new Bundle();
        JoindoctorSuccessFragment fragment = new JoindoctorSuccessFragment();
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_joindoctor_success;
    }

    @Override
    public void initUI() {
        switch (type) {
            case "1":
                tvTitle.setText(R.string.shop_join);
                break;
            case "2":
                tvTitle.setText(R.string.doctor_join);
                break;
            case "3":
                tvTitle.setText(R.string.house_join);
                break;
        }

    }

    @Override
    public void initData() {
        Map<String, String> mMap = new HashMap<>();
        mMap.put("member_id", userBean.getMember_id());
        mMap.put("member_token", userBean.getMember_token());
        getPresenter().getUserDetail(mMap);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public JoinSuccessPresenter createPresenter() {
        return new JoinSuccessPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:
                switch (type) {

                    case "1":
                        if ("0".equals(state)) {

                            exit("merchants");

                        } else if ("1".equals(state)) {


                        } else if ("2".equals(state)) {
                           startJoinShopFragment(htmlurl);
                            finish();
                        }



                        break;
                    case "2":
                        if ("0".equals(state)) {

//                            Map<String,String> mMap = new HashMap<>();
//                            mMap.put("member_id",userBean.getMember_id());
//                            mMap.put("member_token",userBean.getMember_token());
//                            mMap.put("cancel_type","doctor");
//                            getPresenter().CancelApplyMearchants(mMap);
                            exit("doctor");
                        } else if ("1".equals(state)) {


                        } else if ("2".equals(state)) {
                            startJoinDoctorFragment(htmlurl);
                            finish();
                        }
                        break;
                    case "3":
                        if ("0".equals(state)) {



//                            Map<String,String> mMap = new HashMap<>();
//                            mMap.put("member_id",userBean.getMember_id());
//                            mMap.put("member_token",userBean.getMember_token());
//                            mMap.put("cancel_type","houseService");
//                            getPresenter().CancelApplyMearchants(mMap);

                            exit("houseService");

                        } else if ("1".equals(state)) {


                        } else if ("2".equals(state)) {
                            startJoinHouseFragment(htmlurl);
                            finish();
                        }
                        break;
                }

                break;
        }
    }
    private void exit(final String  type) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否撤销申请？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Map<String,String> mMap = new HashMap<>();
                mMap.put("member_id",userBean.getMember_id());
                mMap.put("member_token",userBean.getMember_token());
                mMap.put("cancel_type",type);
                getPresenter().CancelApplyMearchants(mMap);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }

    String state;
    String state_str;

    @Override
    public void onGetUserDetail(UserBean data) {

        switch (type) {

            case "1":
                state = data.getMerchants_state();
                break;
            case "2":
                state = data.getDoctor_state();
                break;
            case "3":
                state = data.getHouseService_state();
                break;
        }

        if ("0".equals(state)) {
            state_str = "信息审核中";
            tvSubmit.setVisibility(View.VISIBLE);
            tvSubmit.setText("撤销申请");

        } else if ("1".equals(state)) {
            state_str = "已接受";
            tvSubmit.setVisibility(View.GONE);

        } else if ("2".equals(state)) {
            state_str = "已拒绝";
            tvSubmit.setVisibility(View.VISIBLE);
            tvSubmit.setText("重新申请");
        }

        tvState.setText(state_str);


    }

    @Override
    public void onCancelApplyMearchants(String data) {

        ToastUtils.showToast(context.getApplicationContext(),data);
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

        errorHandle(e);
    }
}
