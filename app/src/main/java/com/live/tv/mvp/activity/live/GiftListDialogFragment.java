package com.live.tv.mvp.activity.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GiftBean;
import com.live.tv.mvp.activity.live.adapter.HomeClaseeGridViewAdapter;
import com.live.tv.mvp.activity.live.adapter.HomeClassViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/3/12.
 */

public class GiftListDialogFragment extends BottomSheetDialogFragment {


    @BindView(R.id.gift_view_pager)
    ViewPager giftViewPager;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.ll_dots)
    LinearLayout ll_dots;
    Unbinder unbinder;
    private GiftBean giftBean;

    private List<GiftBean> list;
    public static final double PAGER_SIZE = 10;
    private boolean isRunning;


    public static GiftListDialogFragment newInstances(List<GiftBean> list) {
        GiftListDialogFragment giftListDialogFragment=new GiftListDialogFragment();
        giftListDialogFragment.list=list;

        return giftListDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_dialog_gift, container,false);
        unbinder = ButterKnife.bind(this, view);




        setData();

        return view;
    }

    private void setData() {
        if (list!=null&&list.size()>0){
            /**
             *       礼物占用的页数
             */
            int pagerCount = list.size()/10;
            if (list.size() % 10 != 0) {
                pagerCount++;
            }


            /**
             * 循环建立总共多少gridlayout
             */
            List<View> mlist = new ArrayList<>();
           final List<HomeClaseeGridViewAdapter> homeClaseeGridViewAdapterArrayList = new ArrayList<>();

            for (int i = 0; i < pagerCount; i++) {
                GridView gridView = new GridView(getActivity());
                gridView.setGravity(Gravity.CENTER);
                gridView.setNumColumns(5);
                HomeClaseeGridViewAdapter homeClaseeGridViewAdapter=new HomeClaseeGridViewAdapter(getActivity(), list, i);
                gridView.setAdapter(homeClaseeGridViewAdapter);
                mlist.add(gridView);
                homeClaseeGridViewAdapterArrayList.add(homeClaseeGridViewAdapter);


            }
            giftViewPager.setAdapter(new HomeClassViewPagerAdapter(mlist));

            /**
             * 点的布局
             */

            for (int i = 0; i < list.size() / PAGER_SIZE; i++) {
                ImageView imageView = new ImageView(getActivity());
                if (0 == i) {
                    imageView.setBackgroundResource(R.drawable.white_dot);
                } else {
                    imageView.setBackgroundResource(R.drawable.gray_dots);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.x10), getResources().getDimensionPixelOffset(R.dimen.x10));
                params.leftMargin = getResources().getDimensionPixelOffset(R.dimen.x10);
                imageView.setLayoutParams(params);
                ll_dots.addView(imageView);
            }
            giftViewPager.setCurrentItem(0);
            giftViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < list.size() / PAGER_SIZE; i++) {
                        homeClaseeGridViewAdapterArrayList.get(i).notifyDataSetChanged();

                        if (i == position) {
                            ll_dots.getChildAt(i).setBackgroundResource(R.drawable.white_dot);

                        } else {
                            ll_dots.getChildAt(i).setBackgroundResource(R.drawable.gray_dots);

                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        //设置圆角
        window.findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundResource(R.color.transparent);
        WindowManager.LayoutParams windowParams = window.getAttributes();
        //这里设置透明度
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_amount, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_amount:
                if (onclickListener!=null){
                    onclickListener.addAmount();
                }
                dismiss();
                break;
            case R.id.tv_send:
                giftBean = null;
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).ischecked()) {
                            if (list.get(i).getIs_running().equals("1")) {
                                // 1是不可连击
                                isRunning = false;
                            } else if (list.get(i).getIs_running().equals("2")) {
                                // 2是可以连击
                                isRunning = true;
                            }
                            giftBean = list.get(i);
                        }
                    }
                }

                if (giftBean == null) {
                    ToastUtils.showToast(getActivity(), "请先选择礼物");
                    return;
                }
                if (!isRunning) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIschecked(false);
                    }
                }

                if (giftBean != null) {
                    if (giftBean.getIs_running().equals("1")) {
                        // 不可连击  隐藏送礼框
                        dismiss();
                    }
                    onclickListener.send(giftBean);
                   // int money = Integer.parseInt(giftBean.getGift_price());
                   /* if (maxmoney >= money) {
                        maxmoney = maxmoney - money;
                        dimends_count.setText("" + maxmoney);
                    }*/
                }
                break;
        }

    }

    public GiftListDialogFragment setOnclickListener(GiftListDialogFragment.onclickListener onclickListener) {
        this.onclickListener = onclickListener;
        return this;
    }

    private onclickListener onclickListener;

    public interface onclickListener{
        void addAmount();
        void send(GiftBean giftBean);

    }



}
