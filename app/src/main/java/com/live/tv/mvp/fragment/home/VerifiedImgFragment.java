package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.VerfiedImgPresenter;
import com.live.tv.mvp.view.home.IVerfiedImgView;
import com.live.tv.util.LoadingUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * @author Created by stone
 * @since 2018/2/1
 */

public class VerifiedImgFragment extends BaseFragment<IVerfiedImgView, VerfiedImgPresenter> implements IVerfiedImgView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ed_real_name)
    EditText edRealName;
    @BindView(R.id.tv_alipay)
    TextView tvAlipay;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.imgOne)
    ImageView imgOne;
    @BindView(R.id.imgTwo)
    ImageView imgTwo;
    @BindView(R.id.imgThree)
    ImageView imgThree;


    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private String health_record_id = "";
    private String imgStr1 = "";
    private String imgStr2 = "";
    private String imgStr3 = "";
    private static final int IMAGE = 0X02;
    public static final int READ_CONTACTS_REQUEST_CODE = 0;
    private int img_type = 0;
    private List<String> imgList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//通知栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);
        }

    }

    public static VerifiedImgFragment newInstance(String health_record_id) {

        Bundle args = new Bundle();
        VerifiedImgFragment fragment = new VerifiedImgFragment();
        fragment.health_record_id = health_record_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_verified_img;
    }

    @Override
    public VerfiedImgPresenter createPresenter() {
        return new VerfiedImgPresenter(getApp());
    }

    @Override
    public void initUI() {
        tvTitle.setText("实名认证");
    }

    @Override
    public void initData() {

    }


    @Override
    public void onCertificationRecord(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }


    private String img1 = "";
    private String img2 = "";
    private String img3 = "";

    int imgNum = 0;

    @Override
    public void onUploadImgs(String[] data, int i) {
        imgNum++;
        Log.i("dfc", "onUploadImgs: " + data[0]);
        switch (i) {
            case 0:
                img1 = data[0];
                break;
            case 1:
                img2 = data[0];
                break;
            case 2:
                img3 = data[0];
                break;
        }


        if (i == 2) {

            LoadingUtil.hideLoading();
            ToastUtils.showToast(context, "图片上传完成");

            map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("legal_face_img", img1);//身份证正面照
            map.put("legal_opposite_img", img2);//身份证反面照
            map.put("legal_hand_img", img3);//身份证反面照
            map.put("health_record_id", health_record_id);
            map.put("idcard", edNum.getText().toString().trim());
            map.put("real_name", edRealName.getText().toString().trim());
            getPresenter().certificationRecord(map);


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

    protected void show_img(int requestcode) {
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
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }

    private String path = "";

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {

                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    if (medias.get(0) instanceof ImageMedia) {
                        path = ((ImageMedia) medias.get(0)).getThumbnailPath();
                    } else {
                        path = medias.get(0).getPath();
                    }

                    switch (img_type) {
                        case R.id.imgOne:
                            imgStr1 = path;
                            Glide.with(getContext()).load(path).into(imgOne);
                            break;
                        case R.id.imgTwo:
                            imgStr2 = path;
                            Glide.with(getContext()).load(path).into(imgTwo);
                            break;
                        case R.id.imgThree:
                            imgStr3 = path;
                            Glide.with(getContext()).load(path).into(imgThree);
                            break;


                    }

                    Log.i("dfc", "onActivityResult: " + path);
                }
                break;


        }
    }

    //上传图片
    private void upImage(String imgStr, final int i) {
        final List<MultipartBody.Part> files = new ArrayList<>();

        Luban.with(getActivity())
                .load(imgStr)                                   // 传人要压缩的图片列表
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // Log.e("-------------------", "压缩开始前调用");
                    }

                    @Override
                    public void onSuccess(File file) {
                        //Log.e("-------------------", "压缩成功后调用");
                        MultipartBody.Part b_cover = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                        files.add(b_cover);
                        if (files.size() > 0) {
                            getPresenter().uploadImgs(files, i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.e("-------------------", "当压缩过程出现问题时调用");
                    }
                }).launch();    //启动压缩

    }

    @OnClick({R.id.ivLeft, R.id.tv_alipay, R.id.imgOne, R.id.imgTwo, R.id.imgThree, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_alipay:
                startVerifiedFragment(health_record_id);
                finish();
                break;
            case R.id.imgOne:
                img_type = R.id.imgOne;
                show_img(IMAGE);
                break;
            case R.id.imgTwo:
                img_type = R.id.imgTwo;
                show_img(IMAGE);
                break;
            case R.id.imgThree:
                img_type = R.id.imgThree;
                show_img(IMAGE);
                break;
            case R.id.tv_submit:
//
//                if (verForm("11111111")){
//                    ToastUtils.showToast(context.getApplicationContext(),"身份证格式不正确");
//                    return;
//                }

                if (checkInputKey()) {


                    LoadingUtil.showLoading(context, "上传图片中");
                    imgList = new ArrayList<>();
                    imgList.add(imgStr1);
                    imgList.add(imgStr2);
                    imgList.add(imgStr3);
                    for (int i = 0; i < imgList.size(); i++) {
//                        imgNum = i;
                        upImage(imgList.get(i), i);
                    }


                }
                break;
        }
    }

    public static boolean verForm(String num) {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        if (!num.matches(reg)) {
            return false;
        }
        return true;
    }



    private boolean checkInputKey() {
        if (StringUtils.isBlank(edRealName.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入您的真实姓名");
            return false;
        }

        if (StringUtils.isBlank(edNum.getText().toString().trim())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入您的身份证号码");
            return false;
        }





        if (StringUtils.isBlank(imgStr1)) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入上传身份证正面");
            return false;
        }        if (StringUtils.isBlank(imgStr2)) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入上传身份证反面");
            return false;
        }        if (StringUtils.isBlank(imgStr3)) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入上传手持身份证");
            return false;
        }


        return true;
    }
}
