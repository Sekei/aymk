package com.live.tv.mvp.fragment.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.GoodsSpecificationBeans;
import com.live.tv.bean.ServiceCopeBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by taoh on 2018/5/8.
 */

public class DialogHouseKeepFragment extends DialogFragment {

    private String mserviceid;//id
    private String mprice;//价格
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.stock)
    TextView stock;
    @BindView(R.id.select)
    TextView select;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.btnAction)
    TextView btnAction;
    @BindView(R.id.addCar)
    TextView addCar;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.fblayout)
    FlexboxLayout flexboxLayout;
    @BindView(R.id.design_bottom_sheet)
    ConstraintLayout designBottomSheet;
    @BindView(R.id.tv_content)
    TextView tvcontent;
    private ArrayList<ServiceCopeBean> list;
    Unbinder unbinder;
    public SelectListener selectListener;
    private String  imgurl="";
    private String  phone="";

    public interface SelectListener {
        void OnSelectListener(String id,String prace);

    }

    public void setOnSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list = getArguments().getParcelableArrayList("ServiceCopeBean");
        imgurl=getArguments().getString("ShowImage");
        phone=getArguments().getString("phone");
        final View view = inflater.inflate(R.layout.dialog_housekeep, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        Glide.with(getContext())
                .load(Constants.BASE_URL+imgurl)
                .placeholder(R.drawable.pic_defaults)
                .into(img);
          getActivity().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  setfbLayout();
              }
          });
        stock = (TextView) view.findViewById(R.id.stock);
        select = (TextView) view.findViewById(R.id.select);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDownAnimation(view);
            }
        });
        startUpAnimation(view);
        return view;

    }

    private void setfbLayout() {

        if (list != null) {
            flexboxLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
               final int id= i;
                final TextView word = new TextView(getContext());
                word.setTextColor(getContext().getResources().getColor(R.color.tx_l));
                word.setText(list.get(i).getService_name());
                word.setPadding(20, 10, 20, 10);
                word.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.y26));
                if (i == 0) {
                   mserviceid=list.get(i).getService_id()+"";
                   mprice=list.get(i).getService_money();
                    word.setBackgroundResource(R.drawable.newshape_line_bg_green);

                    price.setText("¥  " + list.get(i).getService_money()+"/"+list.get(i).getService_type());
                    tvcontent.setText(list.get(i).getService_desc());
                } else {
                    word.setBackgroundResource(R.drawable.newshape_line);

                }
                word.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flexboxLayout.getChildAt(id).setBackgroundResource(R.drawable.newshape_line_bg_green);
                        for (int j=0;j<list.size();j++){
                            if (j==id){
                                price.setText("¥  " + list.get(j).getService_money()+"/"+list.get(j).getService_type());
                                tvcontent.setText(list.get(j).getService_desc());
                                mserviceid=list.get(j).getService_id()+"";
                                mprice=list.get(j).getService_money();

                            }else {
                                flexboxLayout.getChildAt(j).setBackgroundResource(R.drawable.newshape_line);

                            }


                        }

                    }
                });

                FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(30, 18, 30, 18);
                word.setLayoutParams(lp);
                flexboxLayout.addView(word);
            }
        }


    }

    private void startUpAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
    }

    private void startDownAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(slide);

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM; // 显示在底部
            params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
            window.setAttributes(params);
            window.setBackgroundDrawable(new ColorDrawable(0x00000000));
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);


        }

    }

    GoodsSpecificationBeans goodsSpecificationBeans;

    @Subscribe
    public void onEventMainThread(GoodsSpecificationBeans event) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btnAction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAction:
                if (selectListener != null) {
                    selectListener.OnSelectListener(mserviceid,mprice);
                }
                break;

        }
    }


}
