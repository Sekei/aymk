package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.mvp.adapter.communicate.CommunityAppraiseAdapter;
import com.live.tv.mvp.adapter.communicate.CommunityImageAdapter;
import com.live.tv.mvp.adapter.communicate.CommunityLikeAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.HouseKeepDetailPresenter;
import com.live.tv.mvp.view.home.IHousekeepDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 家政服务详情
 * Created by sh-lx on 2017/7/12.
 */

public class HousekeepingDetailFragment extends BaseFragment<IHousekeepDetailView, HouseKeepDetailPresenter> implements IHousekeepDetailView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_quyu)
    TextView tvQuyu;
    @BindView(R.id.tv_Contacts)
    TextView tvContacts;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.title)
    TextView tv_title;
    @BindView(R.id.tv_nums)
    TextView tv_nums;
    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;
    @BindView(R.id.show_ll)
    RelativeLayout mshow_ll;
    Unbinder unbinder;
    @BindView(R.id.ct_miaoshu)
    ConstraintLayout ctMiaoshu;
    @BindView(R.id.easyRecyclerView_img)
    RecyclerView easyRecyclerViewImg;//商品图片的集合
    @BindView(R.id.ct_image)
    ConstraintLayout ctImage;
    @BindView(R.id.tv_appraise)
    TextView tvAppraise;//评论
    @BindView(R.id.recycler_appraise)
    RecyclerView recyclerAppraise;//评论
    @BindView(R.id.ct_pinglun)
    ConstraintLayout ctPinglun;
    @BindView(R.id.tv_like)
    TextView tvLike;
    @BindView(R.id.recycle_like)
    RecyclerView recycleLike;//猜你喜欢
    @BindView(R.id.ct_likelist)
    ConstraintLayout ctLikelist;
    @BindView(R.id.btn_shu)
    RadioButton btnShu;
    @BindView(R.id.btn_pu)
    RadioButton btnPu;
    @BindView(R.id.btn_ping)
    RadioButton btnPing;
    @BindView(R.id.btn_jian)
    RadioButton btnJian;
    @BindView(R.id.radioGroup)
    RadioGroup MradioGroup;

    @BindView(R.id.ll_call)
    LinearLayout llcall;

    private String housekeep_id = "";
    private int mHeight;
    private CommunityImageAdapter imageAdapter;//图片的
    private CommunityAppraiseAdapter appraiseAdapter;//评论的
    private CommunityLikeAdapter likeAdapter;//猜你喜欢
    private List<String> list = new ArrayList<>();
    private int a;
    private int b;
    private int c;
    private int d;
    private int consent;
    public static HousekeepingDetailFragment newInstance(String housekeep_id) {
        Bundle args = new Bundle();
        HousekeepingDetailFragment fragment = new HousekeepingDetailFragment();
        fragment.housekeep_id = housekeep_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_housekeeping_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("家政详情");

        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }

        /***
         * 图片
         *
         */

        easyRecyclerViewImg.setNestedScrollingEnabled(false);
        recyclerAppraise.setNestedScrollingEnabled(false);
        recycleLike.setNestedScrollingEnabled(false);
        imageAdapter = new CommunityImageAdapter(context);
        imageAdapter.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        easyRecyclerViewImg.setLayoutManager(manager);
        easyRecyclerViewImg.setHasFixedSize(true);
        easyRecyclerViewImg.setAdapter(imageAdapter);


        /***
         * 评论
         */

        appraiseAdapter = new CommunityAppraiseAdapter(context);
       // appraiseAdapter.addAll(list);

        LinearLayoutManager lunmanager=new LinearLayoutManager(context);
        lunmanager.setSmoothScrollbarEnabled(true);
        lunmanager.setAutoMeasureEnabled(true);
        recyclerAppraise.setLayoutManager(lunmanager);
        recyclerAppraise.setHasFixedSize(true);
        recyclerAppraise.setAdapter(appraiseAdapter);

        /***
         * 才喜欢
         */

        likeAdapter = new CommunityLikeAdapter(context);
       // likeAdapter.addAll(list);

        LinearLayoutManager likemanager=new LinearLayoutManager(context);
        likemanager.setSmoothScrollbarEnabled(true);
        likemanager.setAutoMeasureEnabled(true);
        recycleLike.setLayoutManager(likemanager);
        recycleLike.setHasFixedSize(true);
        recycleLike.setAdapter(likeAdapter);

        a = getMeasureHeight(ctMiaoshu);
        b = getMeasureHeight(ctImage);
        c = getMeasureHeight(ctPinglun);
        d = getMeasureHeight(ctLikelist);
        MradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.btn_shu:

                        //alphaTitle(0);
                       // mScrollView.scrollTo(0, 0);
                        if (consent>a+150||consent==a){

                            mScrollView.scrollTo(0, 0);

                      }


                        break;
                    case R.id.btn_pu:
                        //mScrollView.scrollTo(0, a);

                        if (Math.abs(consent-a)>150) {
                            mScrollView.scrollTo(0, a);
                        }
                        break;
                    case R.id.btn_ping:
                      //  mScrollView.scrollTo(0, (a + b));

                        if (Math.abs(consent-(a+b))>150) {
                            mScrollView.scrollTo(0, (a + b));

                        }
                        break;
                    case R.id.btn_jian:

                       // mScrollView.scrollTo(0, (a + b+c));

                        if (Math.abs(consent-(a+b+c))>150){
                            mScrollView.scrollTo(0, (a + b+c));

                        }

                        break;


                }


            }
        });

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(">>>>>", ">>" + scrollY);

               consent=scrollY;

                   if (scrollY == 0) {
                       alphaTitle(0);
                   } else {
                       int alpha = (int) (scrollY * 0.5);
                       if (alpha < 0) {
                           alpha = 0;
                       } else if (alpha > 255) {
                           alpha = 255;
                       }
                       alphaTitle(alpha);
                   }


                    if (scrollY < a) {
                       /* **
                         * 第一个*/

                        if (!btnShu.isChecked()){
                            btnShu.setChecked(true);

                        }
                    } else if (scrollY >= a && scrollY < (a + b)) {
                        /***
                         * 第二个
                         */
                        if (!btnPu.isChecked()){
                            btnPu.setChecked(true);
                        }

                    } else if (scrollY >= (a + b) && scrollY < (a + b + c)) {
                       /* **
                         * 第三个
                         */
                        if (!btnPing.isChecked()){
                            btnPing.setChecked(true);

                        }

                    } else {
                       /* **
                         * 第四个
                         */
                        if (!btnJian.isChecked()){
                            btnJian.setChecked(true);
                        }
                    }




            }
        });
    }

    @Override
    public void initData() {

        map.clear();
        map.put("housekeep_id", housekeep_id);
        getPresenter().getHousekeepDetail(map);

    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    @Override
    public HouseKeepDetailPresenter createPresenter() {
        return new HouseKeepDetailPresenter(getApp());
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

    @Override
    public void onGetHousekeepDetail(HousekeepBean data) {
  /*      if (data != null) {

            Glide.with(getContext()).load(Constants.BASE_URL + data.getHouse_service_image()).placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults)
                    .centerCrop()
                    .into(img);
            tvDesc.setText(data.getHouse_service_name());
            //tvAddress.setText(data.getService_city() + "--" + data.getService_country());
            tvType.setText(data.getService_type());
            tvQuyu.setText(data.getHouse_address());
            tvContacts.setText(data.getContact_member());
            tvPhone.setText(data.getContact_phone());
            tvContent.setText(data.getService_desc());
            tv_nums.setText(data.getCount() + "人浏览");
        }*/


    }

    @Override
    public void onHouseKeeps(List<HousekeepBean> list) {

    }

    @Override
    public void OnServiceCope(List<ServiceCopeBean> data) {

    }

    @Override
    public void OnserviceComment(List<ServiceCommentBean> data) {

    }

    @Override
    public void onCollectiohouse(String data) {

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

    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    private void alphaTitle(int alpha) {
        ColorDrawable drawable = (ColorDrawable) MradioGroup.getBackground();
        drawable.mutate().setAlpha(alpha);

          if (alpha<115){
              setbtnEnabled(false);
          }else {
              setbtnEnabled(true);
          }

         for (int i=0;i<MradioGroup.getChildCount();i++){

         RadioButton btn= (RadioButton) MradioGroup.getChildAt(i);

            if (btn.isChecked()){
                btn.setTextColor(Color.argb(alpha,110,185,43));


            }else {
                btn.setTextColor(Color.argb(alpha,102,102,102));
            }

         }
    }


    public void setbtnEnabled(boolean b){

        if (b){
          for (int i=0;i<MradioGroup.getChildCount();i++){
              MradioGroup.getChildAt(i).setEnabled(b);

          }

        }else {

            for (int i=0;i<MradioGroup.getChildCount();i++){
                MradioGroup.getChildAt(i).setEnabled(b);
            }


        }
    }

    @OnClick({R.id.ll_call})
    public void OnCliickListener(View view){
        switch(view.getId()){
            case R.id.ll_call:


                break;
        }

    }
    public void diallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
