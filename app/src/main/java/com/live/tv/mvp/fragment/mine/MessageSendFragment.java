package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.bean.MemberMsgsBean;
import com.live.tv.mvp.adapter.home.OrderMessageAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MsgListPresenter;
import com.live.tv.mvp.view.home.IMsgListView;
import com.ysjk.health.iemk.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/8/1
 */

public class MessageSendFragment extends BaseFragment<IMsgListView, MsgListPresenter> implements IMsgListView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.et_new_title)
    EditText etNewTitle;
    @BindView(R.id.tv_new_time)
    TextView newTime;
    @BindView(R.id.et_msg)
    EditText etMsg;


    public static MessageSendFragment newInstance() {
        MessageSendFragment fragment = new MessageSendFragment();
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_message_send;
    }

    @Override
    public void initUI() {
        tvTitle.setText("消息提醒");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("发送");
    }

    private void showdata() {
        String msgName = etNewTitle.getText().toString().trim();
        String msgDesc = etMsg.getText().toString().trim();
        if ("".equals(msgName) || msgName.length() == 0) {
            ToastUtils.showToast(getActivity(), "标题不能为空");
            return;
        }
        if ("".equals(msgDesc) || msgDesc.length() == 0) {
            ToastUtils.showToast(getActivity(), "内容不能为空");
            return;
        }
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
        map.put("msg_name", msgName);
        map.put("msg_desc", msgDesc);
        getPresenter().getMemberMsgsSend(map);
    }

    @Override
    public void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        newTime.setText(simpleDateFormat.format(date));
    }

    @Override
    public MsgListPresenter createPresenter() {
        return new MsgListPresenter(getApp());
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


    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onCliclk(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                showdata();
                break;
        }
    }

    /***
     * 获取数据
     * @param plateListBeenlist
     */
    @Override
    public void onGetMemberMsgs(List<MemberMsgsBean> plateListBeenlist) {

    }

    @Override
    public void onSendMsg(String data) {
        if ("ok".equals(data)) {
            ToastUtils.showToast(getContext(), "消息提醒成功");
            finish();
        }
    }
}
