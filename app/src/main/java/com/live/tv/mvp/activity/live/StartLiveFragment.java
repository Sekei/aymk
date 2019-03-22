package com.live.tv.mvp.activity.live;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.LiveBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.live.StartLivePresenter;
import com.live.tv.mvp.view.live.IStartLiveView;
import com.live.tv.util.GlideRoundTransform;
import com.live.tv.util.ImageFactory;
import com.live.tv.util.LoadingUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class StartLiveFragment extends BaseFragment<IStartLiveView,StartLivePresenter> implements IStartLiveView {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.img_update_pic)
    ImageView img_update_pic;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.tv_update_pic)
    TextView tvUpdatePic;
    @BindView(R.id.tv_start_live)
    TextView tvStartLive;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    Unbinder unbinder;

    public static StartLiveFragment newInstance() {
        Bundle args = new Bundle();
        StartLiveFragment fragment = new StartLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_start_live;
    }

    @Override
    public void initUI() {

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
    public StartLivePresenter createPresenter() {
        return new StartLivePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_close, R.id.tv_update_pic, R.id.tv_start_live, R.id.tv_xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.tv_update_pic:
                show_img();
                break;
            case R.id.tv_start_live:
                if (checkInputKey()){
                    if (userBean!=null){
                        //判断环信时候连接。已连接 开始直播，已断开，退出在登陆

                        LoadingUtil.showLoadingNew(context,"加载中...");
                     if (EMClient.getInstance().isConnected())  {
                         map.clear();
                         map.put("member_id", userBean.getMember_id());
                         map.put("member_token", userBean.getMember_token());
                         map.put("live_title", editText.getText().toString());
                         map.put("live_img", path_str);
                         getPresenter().openLive(map);

                     }else {
                         Log.d("dfc", "登录过，已短开！");
                         EMClient.getInstance().logout(true);
                         loginHx();

                     }




                    }
                }
                break;
            case R.id.tv_xieyi:
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
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("live_title", editText.getText().toString());
                map.put("live_img", path_str);
                getPresenter().openLive(map);
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
    protected void show_img() {
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                .needCamera(R.drawable.ic_boxing_camera_white)
                .withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, REQUEST_CODE);

    }


    private String path = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    if (medias.get(0) instanceof ImageMedia) {
                        path = ((ImageMedia) medias.get(0)).getThumbnailPath();
                    } else {
                        path = medias.get(0).getPath();
                    }
                    Glide.with(getActivity())
                            .load(path)
                            .centerCrop()
                            .transform(new CenterCrop(getActivity()), new GlideRoundTransform(getActivity(), 5))
                            .into(img_update_pic);

                    upimg();
                }
                break;


        }
    }
    private void upimg() {
        String outFilePath = context.getExternalCacheDir().getPath() + System.currentTimeMillis() + ".jpg";
        ImageFactory imageFactory = new ImageFactory();
        Bitmap ratio = imageFactory.ratio(path, 480, 480);
        try {
            imageFactory.storeImage(ratio, outFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ratio != null) {
                ratio.recycle();
                ratio = null;
                imageFactory = null;
            }
        }
        File _file = new File(outFilePath);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
        LoadingUtil.showLoading(context, getResources().getString(R.string.img_put));
    }



    private boolean checkInputKey() {
        if (StringUtils.isBlank(editText.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入直播标题");
            return false;
        }

        if ("".equals(path_str)){
            ToastUtils.showToast(context.getApplicationContext(), "请上传直播图片");
            return false;
        }

        return  true;
    }

    @Override
    public void openQiNiuLive(LiveBean data) {
        LoadingUtil.hideLoading();
        if (data!=null){
            Intent intent = new Intent(getActivity(), PushFlowActivity.class);
            intent.putExtra("live_push_url",data.getLive_push_url());
            intent.putExtra("roomId",data.getRoom_id());
            intent.putExtra("stream_id",data.getStream_id());
            intent.putExtra("liveId",data.getLive_id());
            startActivity(intent);
            finish();

        }
    }


    String path_str="";

    @Override
    public void uploadImg(String data) {
        LoadingUtil.hideLoading();
        path_str=data;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LoadingUtil.hideLoading();
        errorHandle(e);

    }
}
