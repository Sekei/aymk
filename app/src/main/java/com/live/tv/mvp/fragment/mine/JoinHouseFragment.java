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
import com.live.tv.bean.DoctorTitleBean;
import com.live.tv.bean.HouseKeepTypeBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.ChooseHouseKeepTypeDialog;
import com.live.tv.mvp.presenter.mine.JoinHousePresenter;
import com.live.tv.mvp.view.mine.IJoinHouseView;
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
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * 申请家政
 *
 * @author Created by stone
 * @since 2018/2/1
 */

public class JoinHouseFragment extends BaseFragment<IJoinHouseView, JoinHousePresenter> implements IJoinHouseView {

    Unbinder unbinder;

    Map<String, String> map;
    UserBean userBean;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etNmae)
    EditText etNmae;
    @BindView(R.id.etSex)
    TextView etSex;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etJob)
    EditText etJob;
    @BindView(R.id.etType)
    TextView etType;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.imgOne)
    ImageView imgOne;
    @BindView(R.id.imgTwo)
    ImageView imgTwo;
    @BindView(R.id.imgFour)
    ImageView imgFour;
    @BindView(R.id.imgFive)
    ImageView imgFive;
    @BindView(R.id.imgSix)
    ImageView imgSix;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.textView21)
    TextView textView21;

    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.showchoseaddress)
    TextView showchoseaddress;
    @BindView(R.id.etAddress)
    EditText etAddress;
    private String province = "";
    private String city = "";
    private String country = "";
    private String imgStr1 = "";
    private String imgStr2 = "";
    private String imgStr4 = "";
    private String imgStr5 = "";
    private String imgStr6 = "";
    private static final int IMAGE = 0X02;
    public static final int READ_CONTACTS_REQUEST_CODE = 0;
    private int img_type = 0;
    private List<String> imgList;
    private DepartmentPopwindowBottom departmentPopwindow;
    private List<DoctorTitleBean> doctorTitleBeanList;
    private String htmlurl;

    public static JoinHouseFragment newInstance(String htmlurl) {

        Bundle args = new Bundle();

        JoinHouseFragment fragment = new JoinHouseFragment();
        fragment.htmlurl=htmlurl;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_join_house;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.txt_join_house);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map = new HashMap<>();
        getPresenter().getServiceTypes();
    }


    @OnClick({R.id.ivLeft, R.id.commit, R.id.imgOne, R.id.imgTwo, R.id.imgFour, R.id.imgFive, R.id.imgSix
            , R.id.tv_service, R.id.etSex, R.id.etType,R.id.tvchoseAddress
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.commit:
                if (checkInputKey()) {
                    imgNum = 0;
                    LoadingUtil.showLoading(context, "上传图片中");
                    imgList = new ArrayList<>();
                    imgList.add(imgStr1);
                    imgList.add(imgStr2);
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

                startWeb("爱医美康", Constants.BASE_URL+htmlurl, "2");
                break;

            case R.id.etSex:

                chooseSex();
                break;

            case R.id.etType:

                if (houseKeepTypeBeanList != null && houseKeepTypeBeanList.size() > 0) {
                    startChosoeDoctortitle(houseKeepTypeBeanList, context);
                }
                break;
            case R.id.tvchoseAddress:

                cityPicker();
                break;


        }
    }

    private void chooseSex() {

        SinglePicker<String> singlePicker = new SinglePicker<>(getActivity(), new String[]{"男", "女"});
        singlePicker.setCanLoop(true);//不禁用循环
        singlePicker.setAnimationStyle(R.style.AnimBottom);
        singlePicker.setTopBackgroundColor(0xFFEEEEEE);
        singlePicker.setTopHeight(50);
        singlePicker.setTopLineHeight(1);
        singlePicker.setTitleText("请选择");
        singlePicker.setTitleTextColor(0xFF999999);
        singlePicker.setTitleTextSize(12);
        singlePicker.setCancelTextColor(0xFF999999);
        singlePicker.setCancelTextSize(13);
        singlePicker.setSubmitTextColor(0xFF6EB92B);
        singlePicker.setSubmitTextSize(13);
        singlePicker.setSelectedTextColor(0xFF6EB92B);
        singlePicker.setUnSelectedTextColor(0xFF999999);
        LineConfig config = new LineConfig();
        config.setColor(0xFF6EB92B);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        singlePicker.setLineConfig(config);
        singlePicker.setItemWidth(200);
        singlePicker.setBackgroundColor(0xFFE1E1E1);
        singlePicker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                etSex.setText(item);
            }
        });
        singlePicker.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public JoinHousePresenter createPresenter() {
        return new JoinHousePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String img1 = "";
    private String img2 = "";
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


        if (imgNum == 5) {

            LoadingUtil.hideLoading();
            ToastUtils.showToast(context, "图片上传完成");

            map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("prove_img1", img1);
            map.put("prove_img2", img2);
            map.put("legal_face_img", img4);//身份证正面照
            map.put("legal_opposite_img", img5);//身份证反面照
            map.put("legal_hand_img", img6);//身份证反面照
            map.put("age", etAge.getText().toString());//年龄
            map.put("contact_name", etNmae.getText().toString());
            map.put("sex", etSex.getText().toString().toString());
            map.put("experience", etJob.getText().toString());
            map.put("service_type", service_type);
            map.put("service_type_id", service_type_id);
            map.put("contact_phone", textView21.getText().toString());
            map.put("email_address", edEmail.getText().toString());
            map.put("house_province",province);
            map.put("house_city",city);
            map.put("house_country",country);
            map.put("house_address",etAddress.getText().toString());
            getPresenter().applyHouse(map);

            Log.i("dfc", "onUploadImgs: " + map.toString());

        }


    }

    public String service_type_id = "";
    public String service_type = "";
    public List<HouseKeepTypeBean> houseKeepTypeBeanList;

    public void startChosoeDoctortitle(List<HouseKeepTypeBean> doctorTitleBeanList, Context context) {
        final ChooseHouseKeepTypeDialog fragment = ChooseHouseKeepTypeDialog.newInstance(doctorTitleBeanList, context);
        fragment.setOkClickListener(new ChooseHouseKeepTypeDialog.OKOnclickListener() {
            @Override
            public void onOk(String state, String id) {
                service_type = state;
                service_type_id = id;
                etType.setText(state);
            }


        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    }


    @Override
    public void applyHouse(String data) {

        ToastUtils.showToast(context.getApplicationContext(), "申请成功");
        finish();
    }

    @Override
    public void getServiceTypes(List<HouseKeepTypeBean> data) {
        houseKeepTypeBeanList = data;
    }


    private ArrayList<DepartmentLevelOneBean> listOne = new ArrayList<>();


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

        if (StringUtils.isBlank(etAge.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请填写你的年龄");
            return false;
        }

        if (StringUtils.isBlank(etSex.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择你的性别");
            return false;
        }
        if (StringUtils.isBlank(etJob.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请填写你的从业经验");
            return false;
        }

        if (StringUtils.isBlank(service_type)) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择你的服务类型");
            return false;
        }
        if (StringUtils.isBlank(textView21.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请填写你的联系电话");
            return false;
        }
        if (StringUtils.isBlank(edEmail.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请填写你的邮箱");
            return false;
        }
        if (StringUtils.isBlank(imgStr1)) {
            ToastUtils.showToast(context.getApplicationContext(), "请上传相关资质证明");
            return false;
        }
        if (StringUtils.isBlank(imgStr2)) {
            ToastUtils.showToast(context.getApplicationContext(), "请上传相关资质证明");
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
            ToastUtils.showToast(context.getApplicationContext(), "请阅读爱医美康家政申请协议");
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
                        case R.id.imgOne:
                            imgStr1 = path;
                            Glide.with(getContext()).load(path).into(imgOne);
                            break;
                        case R.id.imgTwo:
                            imgStr2 = path;
                            Glide.with(getContext()).load(path).into(imgTwo);
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
                showchoseaddress.setText(province + " " + city + " " + country);


            }

            @Override
            public void onCancel() {


            }
        });
    }

}
