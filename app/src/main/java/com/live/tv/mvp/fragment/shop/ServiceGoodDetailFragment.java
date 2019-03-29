package com.live.tv.mvp.fragment.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.GoodsImgBean;
import com.live.tv.bean.GoodsSpecificationBeans;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.huanxin.ChatActivity;
import com.live.tv.mvp.presenter.shop.GoodDetailPresenter;
import com.live.tv.mvp.view.shop.IGoodDetailView;

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
 * 服务商品详情
 * Created by sh-lx on 2017/7/12.
 */

public class ServiceGoodDetailFragment extends BaseFragment<IGoodDetailView, GoodDetailPresenter> implements IGoodDetailView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_add_cart)
    TextView tvAddCart;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.convenietnBanner)
    ConvenientBanner convenietnBanner;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_good_price)
    TextView tvGoodPrice;
    @BindView(R.id.tv_haoping)
    TextView tvHaoping;
    @BindView(R.id.tv_yuexiao)
    TextView tvYuexiao;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.img_merchants)
    ImageView imgMerchants;
    @BindView(R.id.tv_merchants_name)
    TextView tvMerchantsName;
    @BindView(R.id.tv_merchants_address)
    TextView tvMerchantsAddress;
    @BindView(R.id.tv_merchants_desc)
    TextView tvMerchantsDesc;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.webView)
    WebView WVH5;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    Unbinder unbinder;
    private String good_id = "";
    private List<GoodsImgBean> listBanner;
    private GoodsBean goodsBean;


    public static ServiceGoodDetailFragment newInstance(String good_id) {
        Bundle args = new Bundle();
        ServiceGoodDetailFragment fragment = new ServiceGoodDetailFragment();
        fragment.good_id = good_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_service_good_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("商品详情");
        AddWebView();
        scrollView.smoothScrollTo(0, 20);

    }

    @Override
    public void initData() {
        listBanner = new ArrayList<>();
        map.clear();

        if (userBean != null) {
            map.put("member_id", userBean.getMember_id());
        }
        map.put("goods_id", good_id);
        getPresenter().getGoodDetail(map);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public GoodDetailPresenter createPresenter() {
        return new GoodDetailPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_service, R.id.tv_collection, R.id.tv_add_cart
            , R.id.tv_buy,R.id.tv_nums,R.id.view3,R.id.view2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_service:
                /***
                 * 联系客服
                 */
                if(EMClient.getInstance().isLoggedInBefore()){
                    //如果登陆过，判断是否连接
                    if (EMClient.getInstance().isConnected()){
                       //链接
                        try {
                            startActivity(new Intent(getActivity(), ChatActivity.class)
                                    .putExtra("userId",goodsBean.getMerchantsBean().getHx_account())
                                    .putExtra("APP_user_name",userBean.getHx_nick_name())
                                    .putExtra("health_record_id","")
                                    .putExtra("doctor_id","")
                                    .putExtra("consult_record_id","")
                                    .putExtra("to_head_image",userBean.getLegal_hand_img())
                                    .putExtra("consultation_type","1")
                                    .putExtra("to_username","客服"));

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else {
                        Log.d("dfc", "登录过，已短开！");
                        EMClient.getInstance().logout(true);
                        loginHx();
                    }
                }else{
                    //开始登陆
                    loginHx();
                }


                break;
            case R.id.tv_collection:

                if (userBean!=null){

                    map.clear();
                    map.put("member_id",userBean.getMember_id());
                    map.put("member_token",userBean.getMember_token());
                    map.put("collection_type","goods");
                    map.put("goods_id",good_id);
                    getPresenter().getinsertCollection(map);

                }else {
                    startLogin();
                }
                break;
            case R.id.tv_add_cart:

                break;
            case R.id.tv_buy:
                if (goodsBean!=null){
                    startConfirmServiceGoodOrderFragment(goodsBean);
                }

                break;

            case R.id.tv_nums:
                break;
            case R.id.view3://评价
                startMoreAssessmentListFragment(good_id);
                break;
            case  R.id.view2://商家详情
                startMerchantsDetailFragment(goodsBean.getMerchants_id());
                break;
        }
    }


    //登陆环信
    private void loginHx() {

        EMClient.getInstance().login(userBean.getHx_account(), userBean.getHx_password(), new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                startActivity(new Intent(getActivity(), ChatActivity.class)
                        .putExtra("userId",goodsBean.getMerchantsBean().getHx_account())
                        .putExtra("APP_user_name",userBean.getHx_nick_name())
                        .putExtra("health_record_id","")
                        .putExtra("doctor_id","")
                        .putExtra("consult_record_id","")
                        .putExtra("to_head_image",userBean.getLegal_hand_img())
                        .putExtra("to_username","客服"));


            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                Log.d("dfc", "登录聊天服务器失败！"+code+message);
            }
        });
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
    public void onGoodDetail(GoodsBean data) {

        if (data != null) {
            goodsBean=data;
            Log.e("lalla",goodsBean.getMerchantsBean().getHx_account());
            if (data.getGoodsImgBeans() != null && data.getGoodsImgBeans().size() > 0) {
                if (convenietnBanner != null) {
                    toSetList(listBanner, data.getGoodsImgBeans(), false);
                    convenietnBanner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Holder<GoodsImgBean> createHolder() {
                            return new ImageHolder();
                        }
                    }, listBanner)
                            .setPageIndicator(new int[]{R.drawable.icon_dot_nor, R.drawable.icon_dot_pre})
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

                    if (!convenietnBanner.isTurning()) {
                        convenietnBanner.startTurning(4000);
                    }
                    convenietnBanner.notifyDataSetChanged();
                }

            }
            if (!goodsBean.getGoods_url().equals("")) {
                WVH5.loadUrl(Constants.BASE_URL + goodsBean.getGoods_url());
                Log.i("dfc", "onGoodsDetail: " + Constants.BASE_URL + goodsBean.getGoods_url());
            }


            tvGoodName.setText(data.getGoods_name());
            tvGoodPrice.setText("¥" + data.getGoods_now_price());
            tvHaoping.setText(data.getRate());
            tvYuexiao.setText(data.getMonth_sales());
            tvKucun.setText(data.getGoods_stock());

            MerchantsBean merchantsBean = data.getMerchantsBean();
            Glide.with(context).load(Constants.BASE_URL + merchantsBean.getMerchants_img())
                    .error(R.drawable.pic_defaults)
                    .placeholder(R.drawable.pic_defaults).into(imgMerchants);

            tvMerchantsName.setText(merchantsBean.getMerchants_name());
            tvMerchantsAddress.setText(merchantsBean.getMerchants_province() + merchantsBean.getMerchants_city() + merchantsBean.getMerchants_country());
            tvMerchantsDesc.setText(merchantsBean.getMerchants_introduction());



            if (data.getIs_collection().equals("1")) {
                Drawable drawable = getActivity().getResources().getDrawable(R.drawable.doctor_consult_followed);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvCollection.setCompoundDrawables(null, drawable, null, null);
            } else {

                Drawable drawable = getActivity().getResources().getDrawable(R.drawable.doctor_consult_follow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvCollection.setCompoundDrawables(null, drawable, null, null);
            }

        }
    }

    @Override
    public void oninsertCollection(String data) {
        ToastUtils.showToast(context.getApplicationContext(),"收藏成功");
        Drawable drawable = getActivity().getResources().getDrawable(R.drawable.doctor_consult_followed);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvCollection.setCompoundDrawables(null, drawable, null, null);
    }

    @Override
    public void onInsertShopCar(String data) {
        ToastUtils.showToast(context.getApplicationContext(),"添加购物成功");
    }

    @Override
    public void onGetGoodsSpecificationDetail(GoodsSpecificationBeans data) {
        EventBus.getDefault().post(data);
    }

    @Override
    public void onAssessmentGoods(List<AssessmentBean> data) {

    }


    //轮播图
    public class ImageHolder implements Holder<GoodsImgBean> {
        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, GoodsImgBean data) {
            Glide.with(context).load(Constants.BASE_URL + data.getGoods_img()).error(R.drawable.banner_default).placeholder(R.drawable.banner_default).into(iv);
        }
    }


    ///加载网页 初始化

    private void AddWebView() {

        WVH5.getSettings().setJavaScriptEnabled(true); //与js交互
        WVH5.requestFocus();// 如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件?
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型? 1、LayoutAlgorithm.NARROW_COLUMNS
         * ：?应内容大? 2、LayoutAlgorithm.SINGLE_COLUMN : 适应屏幕，内容将自动缩放
         */
        WVH5.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        // 启动缓存
        WVH5.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        WVH5.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        WVH5.getSettings().setAppCacheEnabled(false);
        WVH5.clearCache(true);
        if (Build.VERSION.SDK_INT >= 19) {// 页面finish后再发起图片加载
            WVH5.getSettings().setLoadsImagesAutomatically(true);
        } else {
            WVH5.getSettings().setLoadsImagesAutomatically(false);
        }

        WVH5.setOnLongClickListener(new View.OnLongClickListener() {// 屏蔽系统长按复制 粘贴事件
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        Map<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put("Referer", "http://www.google.com");// 防止盗链
        WVH5.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });


    }
}
