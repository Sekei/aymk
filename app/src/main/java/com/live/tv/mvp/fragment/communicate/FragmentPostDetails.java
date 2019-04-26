package com.live.tv.mvp.fragment.communicate;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.live.bean.TCInputTextMsgDialog;
import com.live.tv.mvp.adapter.communicate.PoastDeatilsAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.communicate.PostDetailsPresenter;
import com.live.tv.mvp.view.communicate.IPostDetailsView;
import com.live.tv.util.SpSingleInstance;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/21.
 * 健康详情界面
 */

public class FragmentPostDetails extends BaseFragment<IPostDetailsView, PostDetailsPresenter> implements IPostDetailsView, TCInputTextMsgDialog.OnTextSendListener {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.tv_good)
    TextView tvGood;
    @BindView(R.id.tv_ommenct)
    TextView tvOmmenct;
    @BindView(R.id.write_message)
    TextView writeTv;
    @BindView(R.id.iv_good)
    ImageView ivgood;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    private SHARE_MEDIA mShare_meidia = SHARE_MEDIA.WEIXIN;
    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;
    private PoastDeatilsAdapter adapter;
    private UserBean userBean;
    public Map<String, String> map = new HashMap<>();
    Unbinder unbinder;
    private PlateListBean mlistBean;
    private String postId;
    private List<PlateListBean.CommentPostBeansBeanX> list1 = new ArrayList<>();
    private List<PlateListBean.PostBeanBeanX.CommentPostBeansBean> list2 = new ArrayList<>();
    private UMImage mImage;
    private String mShareUrl;
    private String mTitle;
    private TCInputTextMsgDialog mInputTextMsgDialog;
    private String number;

    public static FragmentPostDetails newInstance(String postId) {

        FragmentPostDetails fragment = new FragmentPostDetails();
        fragment.postId = postId;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_post_details;
    }

    @Override
    public void initUI() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvTitle.setText("健康详情");

        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.doctor_share);
        WebSettings webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setBuiltInZoomControls(true); // 显示放大缩小
        webSettings.setSupportZoom(true); // 可以缩放
        webView.setWebViewClient(new MyWebViewClient());
        adapter = new PoastDeatilsAdapter(getActivity());
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        easyRecyclerView.setAdapter(adapter);
        mInputTextMsgDialog = new TCInputTextMsgDialog(getActivity(), R.style.InputDialog);
        mInputTextMsgDialog.setmOnTextSendListener(this);
    }

    @Override
    public void initData() {
        setdata();
        //修改帖子阅读次数
        Map<String, String> map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("post_id", postId);
        getPresenter().getUpdatePost(map);
    }

    private void setdata() {
        try {
            map.put("post_id", postId);
            map.put("member_id", userBean.getMember_id());
            getPresenter().getPoastDetial(map);
        } catch (Exception e) {
            e.printStackTrace();
            startLogin();
        }

    }

    @Override
    public PostDetailsPresenter createPresenter() {
        return new PostDetailsPresenter(getApp());
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

    /***
     * 返回数据
     * @param listBean
     */
    @Override
    public void onPoastDetial(final PlateListBean listBean) {

        if (listBean != null) {
            mlistBean = listBean;
            if (listBean.getPostBean() != null && listBean.getPostBean().getForward_num() != null) {
                /**
                 * 逻辑判断(转发的)
                 */
                // webView.loadUrl("http://meikang.zhongfeigou.com/html1/post/20180821192640758783.html");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText(listBean.getPostBean().getPost_title());
                        mShareUrl = Constants.BASE_URL + listBean.getPostBean().getPost_html_desc();
                        webView.loadUrl(Constants.BASE_URL + listBean.getPostBean().getPost_html_desc());
                        tvGood.setText(listBean.getPraise_num());
                        tvRead.setText(listBean.getRead_num()+"人阅读");
                        number = listBean.getPraise_num();
                        if (listBean.getIs_praise().equals("1")) {
                            ivgood.setBackgroundResource(R.drawable.heart_icom);
                        } else {
                            ivgood.setBackgroundResource(R.drawable.unheart_icon);

                        }

                        list1 = listBean.getCommentPostBeans();
                        if (list1 != null && list1.size() > 0) {
                            tvOmmenct.setVisibility(View.VISIBLE);
                            tvOmmenct.setText("留言(" + list1.size() + ")");
                            adapter.clear();
                            adapter.addAll(list1);
                            adapter.notifyDataSetChanged();
                        } else {
                            tvOmmenct.setVisibility(View.VISIBLE);
                            tvOmmenct.setText("留言(0)");

                        }
                    }
                });


            } else {
                /***
                 * 自己写的
                 */
                tvTitle.setText(listBean.getPost_title());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mShareUrl = Constants.BASE_URL + listBean.getPost_html_desc();
                        webView.loadUrl(Constants.BASE_URL + listBean.getPost_html_desc());
                        tvGood.setText(listBean.getPraise_num());
                        tvRead.setText(listBean.getRead_num()+"人阅读");
                        number = listBean.getPraise_num();
                        if (listBean.getIs_praise().equals("1")) {
                            ivgood.setBackgroundResource(R.drawable.heart_icom);
                        } else {
                            ivgood.setBackgroundResource(R.drawable.unheart_icon);

                        }
                        list1 = listBean.getCommentPostBeans();
                        if (list1 != null && list1.size() > 0) {
                            tvOmmenct.setVisibility(View.VISIBLE);
                            tvOmmenct.setText("留言(" + list1.size() + ")");
                            adapter.clear();
                            adapter.addAll(list1);
                            adapter.notifyDataSetChanged();

                        } else {
                            tvOmmenct.setVisibility(View.VISIBLE);
                            tvOmmenct.setText("留言(0)");
                        }
                    }
                });


            }
        }


    }

    /***
     * 点赞
     * @param listBean
     */
    @Override
    public void onpraisePost(String listBean) {
        ToastUtils.showToast(context.getApplicationContext(), "点赞成功");
        ivgood.setBackgroundResource(R.drawable.heart_icom);
        try {
            tvGood.setText(Integer.parseInt(number) + 1 + "");
        } catch (Exception e) {
            e.printStackTrace();
            tvGood.setText("1");
        }

    }

    @Override
    public void onpraisePosterror(Throwable msg) {
        ToastUtils.showToast(getActivity(), msg.getMessage());
    }

    /***
     * 写留言
     * @param msg
     */
    @Override
    public void onpcommentPost(String msg) {

        ToastUtils.showToast(getActivity(), "留言成功");
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        setdata();
    }

    /***
     * 发送评论
     * @param msg
     * @param tanmuOpen
     */
    @Override
    public void onTextSend(String msg, boolean tanmuOpen) {
        if (userBean != null) {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("comment_desc", msg);
            map.put("member_nick_name", userBean.getMember_nick_name());
            map.put("member_head_image", userBean.getMember_head_image());
            map.put("post_id", postId);
            getPresenter().getpcommentPost(map);
        } else {
            startLogin();
        }


    }

    //设置webview代理加载图片
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void imgReset() {
        if (getActivity() != null) {
            webView.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName('img'); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "var img = objs[i];   " +
                    " img.style.maxWidth = '100%';img.style.height='auto';" +
                    "}" +
                    "})()");
        }
    }

    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.iv_good, R.id.write_message})

    public void onclik(View view) {

        switch (view.getId()) {
            case R.id.ivLeft:

                finish();
                break;
            case R.id.ivRight:
                if (mlistBean != null) {
                    if (mlistBean.getPostBean() != null && mlistBean.getPostBean().getForward_num() != null) {

                        mImage = new UMImage(getContext(), Constants.BASE_URL + mlistBean.getPostBean().getMember_head_image());
                        mTitle = mlistBean.getPostBean().getPost_title();

                    } else {

                        mImage = new UMImage(getContext(), Constants.BASE_URL + mlistBean.getMember_head_image());
                        mTitle = mlistBean.getPost_title();
                    }


                } else {
                    mImage = new UMImage(getContext(), R.drawable.avatar);
                    mTitle = "爱医美康分享标题";
                }

                showShareDialog();

                break;
            case R.id.iv_good:
                if (userBean != null) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("post_id", postId);
                    getPresenter().getpraisePost(map);

                } else {
                    startLogin();
                }

                break;
            case R.id.write_message:

                showInputMsgDialog();
                break;


        }


    }


    /**
     * 发消息弹出框
     */
    private void showInputMsgDialog() {
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mInputTextMsgDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        mInputTextMsgDialog.getWindow().setAttributes(lp);
        mInputTextMsgDialog.setCancelable(true);
        mInputTextMsgDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mInputTextMsgDialog.show();
    }

    private void showShareDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.share_dialog, null);
        final AlertDialog mDialog = new AlertDialog.Builder(context).create();
        mDialog.show();// 显示创建的AlertDialog，并显示，必须放在Window设置属性之前
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(view);//这一步必须指定，否则不出现弹窗
            WindowManager.LayoutParams mParams = window.getAttributes();
            mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
            window.setBackgroundDrawableResource(android.R.color.white);
            window.setWindowAnimations(R.style.AnimBottom);
            window.setAttributes(mParams);
        }

        TextView btn_wx = (TextView) view.findViewById(R.id.btn_share_wx);
        TextView btn_circle = (TextView) view.findViewById(R.id.btn_share_circle);
        TextView btn_qq = (TextView) view.findViewById(R.id.btn_share_qq);
        TextView btn_qzone = (TextView) view.findViewById(R.id.btn_share_qqzone);
        TextView btn_cancle = (TextView) view.findViewById(R.id.tv_cancel);


        btn_wx.setOnClickListener(mShareBtnClickListen);
        btn_circle.setOnClickListener(mShareBtnClickListen);
        btn_qq.setOnClickListener(mShareBtnClickListen);
        btn_qzone.setOnClickListener(mShareBtnClickListen);


        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
    }

    private View.OnClickListener mShareBtnClickListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_share_wx:
                    mShare_meidia = SHARE_MEDIA.WEIXIN;
                    break;
                case R.id.btn_share_circle:
                    mShare_meidia = SHARE_MEDIA.WEIXIN_CIRCLE;
                    break;
                case R.id.btn_share_qq:
                    mShare_meidia = SHARE_MEDIA.QQ;
                    break;
                case R.id.btn_share_qqzone:
                    mShare_meidia = SHARE_MEDIA.QZONE;
                    break;

                default:
                    break;
            }

            ShareAction shareAction = new ShareAction(getActivity());

            UMWeb web = new UMWeb(mShareUrl);
            web.setThumb(mImage);
            web.setTitle(mTitle);
            web.setDescription("来自《爱医美康》APP的分享！");//描述

            shareAction.withMedia(web);
            shareAction.setCallback(umShareListener);
            shareAction.setPlatform(mShare_meidia).share();
        }
    };


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(getActivity(), "分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

}
