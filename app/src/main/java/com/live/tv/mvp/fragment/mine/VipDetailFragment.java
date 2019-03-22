package com.live.tv.mvp.fragment.mine;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;
import com.king.base.util.SystemUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssociatorBean;
import com.live.tv.mvp.base.SimpleFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class VipDetailFragment extends SimpleFragment {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.vError)
    LinearLayout vError;
    @BindView(R.id.fc)
    FrameLayout fc;

    private AssociatorBean associatorBean;

    protected boolean isError;

    private boolean isShowError;
    private String url="";
    private String title="";

    public static VipDetailFragment newInstance(AssociatorBean associatorBean) {

        Bundle args = new Bundle();
        VipDetailFragment fragment = new VipDetailFragment();
        fragment.associatorBean = associatorBean;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
        }
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragement_vip_detail;
    }

    @Override
    public void initUI() {

        if (associatorBean!=null){

            url=associatorBean.getAssociator_url();
            title=associatorBean.getAssociator_title();
        }


        if (!StringUtils.isBlank(title)) {
            tvTitle.setText(title);
        }
        progressBar.setMax(100);

        isShowError = addErrorView(vError);

        WebSettings ws = webView.getSettings();
        //是否允许脚本支持
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);

        ws.setJavaScriptCanOpenWindowsAutomatically(true);

//        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);

//        webView.setHorizontalScrollBarEnabled(false);

//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        String str = getIntent().getStringExtra(Constants.KEY_URL);
        if (!TextUtils.isEmpty(str)) {
            this.url = str;
        }


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                updateProgressBar(newProgress);
            }
        });
        webView.setWebViewClient(getWebViewClient());

        load(webView, url);
    }

    @Override
    public void initData() {


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

    @OnClick({R.id.ivLeft, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_submit:
                startBuyVipFragment(associatorBean.getAssociator_id(), associatorBean.getAssociator_price(), associatorBean.getAssociator_title());
                break;
        }
    }

    public WebViewClient getWebViewClient() {
        return new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.d("startUrl:" + url);
                isError = false;
                if (view != null) {
                    webView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("url:" + url);

                return super.shouldOverrideUrlLoading(view, url);

            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                updateProgressBar(100);
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                LogUtils.w("errorCode:" + errorCode + "|failingUrl:" + failingUrl);
                showReceiveError();
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.cancel();
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view != null) {
                    hideReceiveError();
                }

            }
        };
    }

    /**
     * @param group
     * @return true表示已添加ErrorView并显示ErrorView/false表示不处理
     */
    public boolean addErrorView(ViewGroup group) {
        group.addView(LayoutInflater.from(context).inflate(R.layout.layout_error, null));
        return true;
    }

    private void showReceiveError() {
        isError = true;
        if (SystemUtils.isNetWorkActive(context)) {
            LogUtils.w("Page loading failed.");
        } else {
            LogUtils.w("Network unavailable.");
        }

        if (isShowError) {
            webView.setVisibility(View.GONE);
            vError.setVisibility(View.VISIBLE);

        }


    }

    private void hideReceiveError() {
        try {
            if (isError) {
                showReceiveError();
            } else {
                webView.setVisibility(View.VISIBLE);
                vError.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载url
     *
     * @param webView
     * @param url
     */
    private void load(WebView webView, String url) {
        LogUtils.d("url:" + url);
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }

    }

    private boolean isGoBack() {
        return webView != null && webView.canGoBack();
    }


    private void updateProgressBar(int progress) {
        updateProgressBar(true, progress);
    }


    private void updateProgressBar(boolean isVisibility, int progress) {

        try {
            progressBar.setVisibility((isVisibility && progress < 100) ? View.VISIBLE : View.GONE);
            progressBar.setProgress(progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
