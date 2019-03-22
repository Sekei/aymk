package com.live.tv.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServiceDetaliPresenter;
import com.live.tv.mvp.view.mine.IServiceDetaliView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/27.
 * 服务详情
 */

public class ServiceDetaliFragment extends BaseFragment<IServiceDetaliView, ServiceDetaliPresenter> implements IServiceDetaliView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.iv_heard)
    ImageView ivHeard;
    @BindView(R.id.tv_title)
    TextView mtvTitle;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    Unbinder unbinder;

    public static ServiceDetaliFragment newInstance() {

        ServiceDetaliFragment fragment = new ServiceDetaliFragment();


        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_service_detials;
    }

    @Override
    public void initUI() {
        tvTitle.setText("服务详情");
        ivLeft.setVisibility(View.VISIBLE);

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 1:
                    llTime.addView(setview(1));
                    ;
                    break;
                case 2:
                    llTime.addView(setview(2));
                    ;

                    break;
                case 3:
                    llTime.addView(setview(3));
                    ;

                    break;
                case 0:
                    llTime.addView(setview(0));
                    ;


                    break;


            }


        }
    }

    @Override
    public void initData() {

    }

    private View setview(int m) {
        View aview = null;
        switch (m) {
            case 0:
                View view = LayoutInflater.from(context).inflate(R.layout.time_line_layout, null);
                TextView tv = (TextView) view.findViewById(R.id.tv_line);
                TextView tv1 = (TextView) view.findViewById(R.id.tv_line2);
                ImageView img = (ImageView) view.findViewById(R.id.iv_point);
                img.setBackgroundResource(R.mipmap.blue_point);
                tv1.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                aview = view;
                break;
            case 1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.time_line_layout, null);
                TextView t1v = (TextView) view1.findViewById(R.id.tv_line);
                TextView t1v1 = (TextView) view1.findViewById(R.id.tv_line2);
                ImageView i1mg = (ImageView) view1.findViewById(R.id.iv_point);
                i1mg.setBackgroundResource(R.mipmap.black_point);
                t1v1.setVisibility(View.VISIBLE);
                t1v.setVisibility(View.VISIBLE);

                aview = view1;

                break;
            case 2:
                View view2 = LayoutInflater.from(context).inflate(R.layout.time_line_layout, null);
                TextView t2v = (TextView) view2.findViewById(R.id.tv_line);
                TextView t2v1 = (TextView) view2.findViewById(R.id.tv_line2);
                ImageView i2mg = (ImageView) view2.findViewById(R.id.iv_point);
                i2mg.setBackgroundResource(R.mipmap.black_point);
                t2v1.setVisibility(View.VISIBLE);
                t2v.setVisibility(View.VISIBLE);
                aview = view2;

                break;
            case 3:
                View view3 = LayoutInflater.from(context).inflate(R.layout.time_line_layout, null);
                TextView t3v = (TextView) view3.findViewById(R.id.tv_line);
                TextView t3v1 = (TextView) view3.findViewById(R.id.tv_line2);
                ImageView i3mg = (ImageView) view3.findViewById(R.id.iv_point);
                i3mg.setBackgroundResource(R.mipmap.black_point);
                t3v1.setVisibility(View.GONE);
                t3v.setVisibility(View.VISIBLE);
                aview = view3;

                break;


        }
        return aview;
    }

    @Override
    public ServiceDetaliPresenter createPresenter() {
        return new ServiceDetaliPresenter(getApp());
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

    @OnClick({R.id.ivLeft})
    public void OnClick(View view) {

        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
