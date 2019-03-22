package com.live.tv.mvp.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.JoinShopPresenter;
import com.live.tv.mvp.view.mine.IJoinShopView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.city.DepartmentPopwindow;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

public class JoinShopFragment extends BaseFragment<IJoinShopView, JoinShopPresenter> implements IJoinShopView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.imgThree)
    ImageView imgThree;
    @BindView(R.id.imgFour)
    ImageView imgFour;
    @BindView(R.id.imgFive)
    ImageView imgFive;
    @BindView(R.id.imgSix)
    ImageView imgSix;
    @BindView(R.id.commit)
    TextView commit;
    Unbinder unbinder;

    Map<String, String> map;
    UserBean userBean;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvPhone;
    @BindView(R.id.tvAge)
    TextView tvEmail;
    @BindView(R.id.tvJob)
    TextView tvJob;
    @BindView(R.id.tvType)
    TextView tvHospital;
    @BindView(R.id.tvDepaterment)
    TextView tvDepaterment;
    @BindView(R.id.etNmae)
    EditText etNmae;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etJob)
    EditText etJob;
    @BindView(R.id.etHostipal)
    EditText etHostipal;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.tvIDCard)
    TextView tvIDCard;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_service)
    TextView tvService;
    private String imgStr1 = "";
    private String imgStr2 = "";
    private String imgStr3 = "";
    private String imgStr4 = "";
    private static final int IMAGE = 0X02;
    public static final int READ_CONTACTS_REQUEST_CODE = 0;
    private int img_type = 0;
    private List<String> imgList;
    private String htmlurl;
    private DepartmentPopwindow departmentPopwindow;

    public static JoinShopFragment newInstance(String htmlurl) {

        Bundle args = new Bundle();

        JoinShopFragment fragment = new JoinShopFragment();
        fragment.htmlurl=htmlurl;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_join_shop;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.shop_join);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map = new HashMap<>();


    }



    @OnClick({R.id.ivLeft, R.id.commit,
            R.id.imgThree, R.id.imgFour, R.id.imgFive, R.id.imgSix,R.id.tv_service
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.commit:
                if (checkInputKey()) {
                    imgNum=0;
                    imgList = new ArrayList<>();
                    imgList.add(imgStr1);
                    imgList.add(imgStr2);
                    imgList.add(imgStr3);
                    imgList.add(imgStr4);
                    LoadingUtil.showLoadingNew(context,"提交中...");
                    for (int i = 0; i < imgList.size(); i++) {
                        upImage(imgList.get(i), i);
                    }


                }


                break;

            case R.id.imgThree:
                img_type = R.id.imgThree;
                show_img(IMAGE);
                break;
            case R.id.imgFour:
                img_type = R.id.imgFour;
                show_img(IMAGE);
                break;
            case R.id.imgFive:
                img_type = R.id.imgFive;
                show_img(IMAGE);
                break;
            case R.id.imgSix:
                img_type = R.id.imgSix;
                show_img(IMAGE);
                break;


            case R.id.tv_service://阅读协议


                startWeb("爱医美康", Constants.BASE_URL+htmlurl,"2");
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
    public JoinShopPresenter createPresenter() {
        return new JoinShopPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String img1 = "";
    private String img2 = "";
    private String img3 = "";
    private String img4 = "";
    int imgNum=0;
    @Override
    public void onUploadImgs(String[] data, int i) {

        Log.i("dfc", "onUploadImgs: " + data[0]);
        imgNum++;
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
            case 3:
                img4 = data[0];
                break;
        }


        if (imgNum == 4) {

            ToastUtils.showToast(context, "图片上传完成");
            map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("business_img", img1);//商家三证何以
            map.put("legal_face_img", img2);//身份证正面照
            map.put("legal_opposite_img", img3);//身份证反面照
            map.put("legal_hand_img", img4);//身份证反面照
            map.put("merchants_name", etJob.getText().toString().trim());//商家名称
            map.put("contact_name", etNmae.getText().toString().trim());//联系人姓名
            map.put("contact_mobile", etPhone.getText().toString().trim());//联系人手机方式
            map.put("merchants_address", etHostipal.getText().toString().trim());//商家地址
            map.put("merchants_email", etEmail.getText().toString().trim());//邮箱地址
            map.put("merchants_img", "");//商家头像
             getPresenter().applyShop(map);

        }


    }

    @Override
    public void applyShop(String data) {
        if (data != null) {
            LoadingUtil.hideLoading();
            ToastUtils.showToast(context.getApplicationContext(), data);
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
        LoadingUtil.hideLoading();
        errorHandle(e);
    }


    private boolean checkInputKey() {
        if (StringUtils.isBlank(etNmae.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_one));
            return false;
        }
        if (StringUtils.isBlank(etPhone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_two));
            return false;
        }
        if (StringUtils.isBlank(etEmail.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_three));
            return false;
        }
        if (StringUtils.isBlank(etJob.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_shop_name));
            return false;
        }
        if (StringUtils.isBlank(etHostipal.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_shop_address));
            return false;
        }

        if (StringUtils.isBlank(imgStr1)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_sanzheng));
            return false;
        }
        if (StringUtils.isBlank(imgStr2)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_four));
            return false;
        }
        if (StringUtils.isBlank(imgStr3)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_five));
            return false;
        }
        if (StringUtils.isBlank(imgStr4)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_six));
            return false;
        }
        if (!checkbox.isChecked()){
            ToastUtils.showToast(context.getApplicationContext(), "请阅读爱医美康商家入驻协议");
            return false;
        }



        return true;
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(1).needCamera(R.drawable.ic_boxing_default_image).withCropOption(new BoxingCropOption(destUri))
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
                        case R.id.imgThree:
                            imgStr1 = path;
                            Glide.with(getContext()).load(path).into(imgThree);
                            break;
                        case R.id.imgFour:
                            imgStr2 = path;
                            Glide.with(getContext()).load(path).into(imgFour);
                            break;
                        case R.id.imgFive:
                            imgStr3 = path;
                            Glide.with(getContext()).load(path).into(imgFive);
                            break;
                        case R.id.imgSix:
                            imgStr4 = path;
                            Glide.with(getContext()).load(path).into(imgSix);
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
                            getPresenter().uploadImgs(files,i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.e("-------------------", "当压缩过程出现问题时调用");
                    }
                }).launch();    //启动压缩

    }


}
