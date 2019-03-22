package com.live.tv.mvp.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DepartmentLevelTwoBean;
import com.live.tv.bean.DoctorTitleBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.ChooseDoctorTitleDialog;
import com.live.tv.mvp.presenter.mine.JoinDoctorPresenter;
import com.live.tv.mvp.view.mine.IJoinDoctorView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SpSingleInstance;
import com.live.tv.util.city.DepartmentPopwindowBottom;
import com.lljjcoder.citypickerview.widget.CityPicker;

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

public class JoinDoctorFragment extends BaseFragment<IJoinDoctorView, JoinDoctorPresenter> implements IJoinDoctorView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

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
    @BindView(R.id.tvEmail)
    TextView tvType;
    @BindView(R.id.etNmae)
    EditText etNmae;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etJob)
    TextView etJob;
    @BindView(R.id.etHostipal)
    EditText etHostipal;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.textView22)
    EditText textView22;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
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
    @BindView(R.id.imgOne)
    ImageView imgOne;
    @BindView(R.id.imgTwo)
    ImageView imgTwo;
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
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.etAddress)
    TextView etAddress;

    private String imgStr1 = "";
    private String imgStr2 = "";
    private String imgStr3 = "";
    private String imgStr4 = "";
    private String imgStr5 = "";
    private String imgStr6 = "";
    private static final int IMAGE = 0X02;
    public static final int READ_CONTACTS_REQUEST_CODE = 0;
    private int img_type = 0;
    private List<String> imgList;
    private DepartmentPopwindowBottom departmentPopwindow;
    private List<DoctorTitleBean> doctorTitleBeanList;
    private String htmlutl;

    public static JoinDoctorFragment newInstance(String htmlutl) {

        Bundle args = new Bundle();

        JoinDoctorFragment fragment = new JoinDoctorFragment();
        fragment.htmlutl=htmlutl;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_join_doctor;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.doctor_join);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map = new HashMap<>();
        getPresenter().getDepartments(map);


        getPresenter().getDoctorTitle();
    }


    @OnClick({R.id.ivLeft, R.id.commit, R.id.imgOne, R.id.imgTwo,
            R.id.imgThree, R.id.imgFour, R.id.imgFive, R.id.imgSix
            , R.id.textView21, R.id.etJob, R.id.tv_service,R.id.etAddress
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.commit:
                if (checkInputKey()) {
                    imgNum=0;
                    LoadingUtil.showLoading(context, "上传图片中");
                    imgList = new ArrayList<>();
                    imgList.add(imgStr1);
                    imgList.add(imgStr2);
                    imgList.add(imgStr3);
                    imgList.add(imgStr4);
                    imgList.add(imgStr5);
                    imgList.add(imgStr6);
                    for (int i = 0; i < imgList.size(); i++) {
//                        imgNum = i;
                        upImage(imgList.get(i), i);
                    }


                }


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

            case R.id.textView21:
                showDepartmentPop();
                break;


            case R.id.etJob:

                if (doctorTitleBeanList != null) {

                    startChosoeDoctortitle(doctorTitleBeanList, context);
                }

                break;


            case R.id.tv_service://阅读协议
                startWeb("爱医美康", Constants.BASE_URL+htmlutl, "2");
                break;


            case R.id.etAddress:
                //选择地区
                cityPicker();
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

    public void startChosoeDoctortitle(List<DoctorTitleBean> doctorTitleBeanList, Context context) {
        final ChooseDoctorTitleDialog fragment = ChooseDoctorTitleDialog.newInstance(doctorTitleBeanList, context);
        fragment.setOkClickListener(new ChooseDoctorTitleDialog.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                etJob.setText(state);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    }

    @Override
    public JoinDoctorPresenter createPresenter() {
        return new JoinDoctorPresenter(getApp());
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
    private String img5 = "";
    private String img6 = "";

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
            case 3:
                img4 = data[0];
                break;
            case 4:
                img5 = data[0];
                break;
            case 5:
                img6 = data[0];
                break;
        }


        if (imgNum == 6) {

            LoadingUtil.hideLoading();
            ToastUtils.showToast(context, "图片上传完成");

            map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("legal_face_img", img4);//身份证正面照
            map.put("legal_opposite_img", img5);//身份证反面照
            map.put("legal_hand_img", img6);//身份证反面照
            map.put("doctor_name", etNmae.getText().toString().trim());
            map.put("doctor_email", etEmail.getText().toString().trim());
            map.put("doctor_title", etJob.getText().toString().trim());
            map.put("doctor_hospital", etHostipal.getText().toString().trim());
            map.put("doctor_attendingtype", textView22.getText().toString().toString());
            map.put("medical_certification_img", img1);
            map.put("physician_certificate_img", img2);
            map.put("doctor_province", province);
            map.put("doctor_city", city);
            map.put("doctor_country", country);
//            map.put("doctor_address", "");
            map.put("doctor_phone", etPhone.getText().toString().trim());
            map.put("license_img", img3);
            map.put("department_level1", department_level1);
            map.put("department_level2", department_level2);

            getPresenter().applyDoctor(map);


        }


    }

    @Override
    public void applyDoctor(String data) {

        if (data != null) {
            ToastUtils.showToast(context.getApplicationContext(), data);
            startJoindoctorSuccessFragment("2");
            finish();

        }

    }

    private ArrayList<DepartmentLevelOneBean> listOne = new ArrayList<>();

    @Override
    public void onGetDepartments(List<DepartmentLevelOneBean> data) {
        listOne.clear();
        listOne.addAll(data);
    }

    @Override
    public void onDoctorTitleList(List<DoctorTitleBean> data) {

        if (data != null) {

            doctorTitleBeanList = data;
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
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_four));
            return false;
        }
        if (StringUtils.isBlank(etHostipal.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_five));
            return false;
        }
        if (StringUtils.isBlank(department_level2)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_six));
            return false;
        }
        if (StringUtils.isBlank(textView22.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.hint_seven));
            return false;
        }
        if (StringUtils.isBlank(imgStr1)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_one));
            return false;
        }
        if (StringUtils.isBlank(imgStr2)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_two));
            return false;
        }
        if (StringUtils.isBlank(imgStr3)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_three));
            return false;
        }
        if (StringUtils.isBlank(imgStr4)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_four));
            return false;
        }
        if (StringUtils.isBlank(imgStr5)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_five));
            return false;
        }
        if (StringUtils.isBlank(imgStr6)) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_six));
            return false;
        }

        if (!checkbox.isChecked()) {
            ToastUtils.showToast(context.getApplicationContext(), "请阅读爱医美康医生入驻协议");
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).needCamera(R.drawable.ic_boxing_camera_white).withCropOption(new BoxingCropOption(destUri))
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
                        case R.id.imgFour:
                            imgStr4 = path;
                            Glide.with(getContext()).load(path).into(imgFour);
                            break;
                        case R.id.imgFive:
                            imgStr5 = path;
                            Glide.with(getContext()).load(path).into(imgFive);
                            break;
                        case R.id.imgSix:
                            imgStr6 = path;
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
                            getPresenter().uploadImgs(files, i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.e("-------------------", "当压缩过程出现问题时调用");
                    }
                }).launch();    //启动压缩

    }

    private String department_level1 = "";
    private String department_level2 = "";

    //科室
    private void showDepartmentPop() {
        if (departmentPopwindow == null) {
            departmentPopwindow = new DepartmentPopwindowBottom(context).setOutsideTouchable(false);
        }
        if (!departmentPopwindow.isShowing()) {
            departmentPopwindow.setData(listOne).cerate().setOnItemClickListener(new DepartmentPopwindowBottom.onItemClickListener() {
                @Override
                public void onclick(DepartmentLevelOneBean departmentLevelOneBean, DepartmentLevelTwoBean data) {
                    if (data != null) {

                        department_level1 = departmentLevelOneBean.getDepartment_name();
                        department_level2 = data.getDepartment_name();
                        textView21.setText(department_level2);
                    }
                    departmentPopwindow.dismiss();
                }
            }).show(textView21);
        }
    }


    public static String province = "";
    public static String city = "";
    public static String country = "";


    /**
     * 显示城市列表
     */
    private void cityPicker() {
        CityPicker cityPicker = new CityPicker.Builder(context)
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#666666")
                .confirTextColor("#6EB92B")
                .cancelTextColor("#999999")
                .province("上海")
                .city("上海市")
                .district("虹口区")
                .textColor(Color.parseColor("#6EB92B"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(10)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                province = citySelected[0];
                //城市
                city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                country = citySelected[2];

                //邮编
                String code = citySelected[3];
                etAddress.setText(province+" "+city+" "+country);


            }

            @Override
            public void onCancel() {


            }
        });
    }



}
