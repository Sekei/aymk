package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.EventBusMessage;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ImageEditPresenter;
import com.live.tv.mvp.view.mine.IImageEditView;
import com.live.tv.util.LoadingUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class ImageEditFragment extends BaseFragment<IImageEditView, ImageEditPresenter> implements IImageEditView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ed_price)
    EditText ed_price;
    Unbinder unbinder;
    private String doctor_id = "";
    private String price = "";

    public static ImageEditFragment newInstance(String price,String doctor_id) {

        Bundle args = new Bundle();

        ImageEditFragment fragment = new ImageEditFragment();
        fragment.doctor_id=doctor_id;
        fragment.price=price;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_image_edit;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.service_setting);
        tvRight.setText(R.string.save);
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));
        tvRight.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {

        if (!"".equals(price)){

            ed_price.setText(price);
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
    public ImageEditPresenter createPresenter() {
        return new ImageEditPresenter(getApp());
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
                map.put("graphic_price", ed_price.getText().toString().trim());
                getPresenter().setConsultPrice(map);

                LoadingUtil.showLoading(context,"保存中...");
                break;
        }
    }

    @Override
    public void onConsultPrice(String data) {

        LoadingUtil.hideLoading();
        if (data!=null){
            ToastUtils.showToast(context.getApplicationContext(),data);
            EventBus.getDefault().post(new EventBusMessage("setting_success"));
            finish();
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
