package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HouseKeepTypeBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.ChooseHouseKeepTypeDialog;
import com.live.tv.mvp.fragment.mine.JoinDoctorFragment;
import com.live.tv.mvp.presenter.home.ReleaseHouseKeepPresenter;
import com.live.tv.mvp.view.home.IReleaseHousekeepView;
import com.live.tv.util.ImageFactory;
import com.live.tv.util.LoadingUtil;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
 * 发布家政信息
 * Created by sh-lx on 2017/7/12.
 */

public class ReleaseHouseKeepFragment extends BaseFragment<IReleaseHousekeepView, ReleaseHouseKeepPresenter> implements IReleaseHousekeepView {
    @BindView(R.id.ivLeft)
    TextView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_quyu_bg)
    TextView tvQuyuBg;
    @BindView(R.id.tv_quyu)
    TextView tv_quyu;
    @BindView(R.id.tv_type_bg)
    TextView tvTypeBg;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_Contacts_bg)
    TextView tvContactsBg;
    @BindView(R.id.tv_Contacts)
    EditText tvContacts;
    @BindView(R.id.tv_phone_bg)
    TextView tvPhoneBg;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    Unbinder unbinder;
    private String path = "";
    private String image_url = "";
    @BindView(R.id.tv_content)
    EditText tvcontent;
    @BindView(R.id.title)
    EditText title;

    public String province = "";
    public String city = "";
    public String country = "";
    public String service_type_id = "";
    public String service_type = "";
    private List<HouseKeepTypeBean> houseKeepTypeBeanList;

    public static ReleaseHouseKeepFragment newInstance() {
        Bundle args = new Bundle();
        ReleaseHouseKeepFragment fragment = new ReleaseHouseKeepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_release_housekeep;
    }

    @Override
    public void initUI() {
        tvTitle.setText("发布");
    }

    @Override
    public void initData() {

        houseKeepTypeBeanList = new ArrayList<>();

        getPresenter().getServiceTypes();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                //发布家政信息
                if (checkInputKey()) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("service_type_id", service_type_id);
                    map.put("service_type", service_type);
                    map.put("service_province", province);
                    map.put("service_city", city);
                    map.put("service_country", country);
                    map.put("contact_member", tvContacts.getText().toString());
                    map.put("contact_phone", tvPhone.getText().toString());
                    map.put("service_desc", tvcontent.getText().toString());
                    map.put("service_title", title.getText().toString());
                    map.put("image_url", image_url);
                    getPresenter().releaseHouse(map);
                }


                break;
        }
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

                    BoxingMediaLoader.getInstance().displayThumbnail(img, path, 480, 480);
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
            }
        }
        File _file = new File(outFilePath);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
        LoadingUtil.showLoading(context, getResources().getString(R.string.img_put));

    }

    @Override
    public ReleaseHouseKeepPresenter createPresenter() {
        return new ReleaseHouseKeepPresenter(getApp());
    }

    @Override
    public void onReleaseHousekeep(String data) {

        ToastUtils.showToast(context.getApplicationContext(), "发布成功");
        finish();
    }

    @Override
    public void uploadImg(String data) {
        LoadingUtil.hideLoading();
        image_url = data;
    }

    @Override
    public void getServiceTypes(List<HouseKeepTypeBean> data) {

        houseKeepTypeBeanList = data;
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

    @OnClick({R.id.img, R.id.tv_quyu_bg, R.id.tv_type_bg, R.id.tv_Contacts_bg, R.id.tv_phone_bg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                show_img();
                break;
            case R.id.tv_quyu_bg:
                cityPicker();
                break;
            case R.id.tv_type_bg:
                if (houseKeepTypeBeanList != null && houseKeepTypeBeanList.size() > 0) {
                    startChosoeDoctortitle(houseKeepTypeBeanList, context);
                }
                break;
            case R.id.tv_Contacts_bg:
                break;
            case R.id.tv_phone_bg:
                break;
        }
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
                tv_quyu.setText(province + " " + city + " " + country);


            }

            @Override
            public void onCancel() {


            }
        });
    }

    public void startChosoeDoctortitle(List<HouseKeepTypeBean> doctorTitleBeanList, Context context) {
        final ChooseHouseKeepTypeDialog fragment = ChooseHouseKeepTypeDialog.newInstance(doctorTitleBeanList, context);
        fragment.setOkClickListener(new ChooseHouseKeepTypeDialog.OKOnclickListener() {
            @Override
            public void onOk(String state, String id) {
                service_type = state;
                service_type_id = id;
                tvType.setText(state);
            }


        });
        fragment.show(getFragmentManager(), JoinDoctorFragment.class.getSimpleName());

    }

    private boolean checkInputKey() {

        if (StringUtils.isBlank(title.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请编辑标题");
            return false;
        }
        if (StringUtils.isBlank(image_url)) {
            ToastUtils.showToast(context.getApplicationContext(), "请上传图片");
            return false;
        }
        if (StringUtils.isBlank(province)) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择服务区域");
            return false;
        }
        if (StringUtils.isBlank(service_type)) {
            ToastUtils.showToast(context.getApplicationContext(), "请选择服务类型");
            return false;
        }


        if (StringUtils.isBlank(tvContacts.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入联系人");
            return false;
        }
        if (StringUtils.isBlank(tvPhone.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入联系电话");
            return false;
        }
        if (StringUtils.isBlank(tvcontent.getText().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), "请编辑服务信息");
            return false;
        }

        return true;
    }
}
