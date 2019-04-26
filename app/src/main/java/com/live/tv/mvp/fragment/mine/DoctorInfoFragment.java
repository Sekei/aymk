package com.live.tv.mvp.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.EventBusMessage;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.DoctorInfoPresenter;
import com.live.tv.mvp.view.mine.IDoctorInfoView;
import com.live.tv.util.ImageFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * 医生档案个人信息
 * @author Created by stone
 * @since 2018/1/16
 */

public class DoctorInfoFragment extends BaseFragment<IDoctorInfoView, DoctorInfoPresenter> implements IDoctorInfoView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.all_bg)
    ConstraintLayout allBg;
    Unbinder unbinder;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;

    private String path = "";
    private String img_str = "";

    public static DoctorInfoFragment newInstance() {
        Bundle args = new Bundle();
        DoctorInfoFragment fragment = new DoctorInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_doctor_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人资料");
    }

    @Override
    public void initData() {

        if (userBean != null) {

            Map<String, String> map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
            getPresenter().getPersonInfo(map);
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
    public DoctorInfoPresenter createPresenter() {
        return new DoctorInfoPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.nameTittle, R.id.ivAvatar, R.id.sexTittle, R.id.birthdayTittle, R.id.phoneTittle, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivAvatar:
                show_img();
                break;

            case R.id.nameTittle:
                startEditNameFragment(name.getText().toString(), context);
                break;
            case R.id.sexTittle:
                chooseSex();
                break;
            case R.id.birthdayTittle:
                choosebirthday();

                break;
            case R.id.phoneTittle:
                editPhone();

                break;
            case R.id.ok:
                map.clear();
//                map.put("member_id", userBean.getMember_id());
//                map.put("member_token", userBean.getMember_token());
//                map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
//                map.put("doctor_name", name.getText().toString());
//                map.put("doctor_phone", phone.getText().toString());
//                map.put("doctor_birthday", birthday.getText().toString());
//                map.put("doctor_sex", sex.getText().toString());

                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_nick_name", name.getText().toString());
                map.put("member_phone", phone.getText().toString());
                map.put("member_birthday", birthday.getText().toString());
                map.put("member_sex", sex.getText().toString());


                if (!"".equals(img_str)) {
                    map.put("member_head_image", img_str);
                }


                getPresenter().updateDetail(map);

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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, REQUEST_CODE);

    }

    public void startEditNameFragment(String s, Context context) {
        final EditNameFragment fragment = EditNameFragment.newInstance(s, context);
        fragment.setOkClickListener(new EditNameFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                name.setText(state);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), DoctorInfoFragment.class.getSimpleName());

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

                    BoxingMediaLoader.getInstance().displayThumbnail(ivAvatar, path, 480, 480);
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
                .addFormDataPart("member_head_image", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
//        mProgressDialog.setMessage("正在上传图片，请稍候...");
//        mProgressDialog.show();
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
    public void PersonInfo(DoctorDetailBean data) {
        if (data != null) {

            Glide.with(context).load(Constants.BASE_URL + data.getDoctor_head_image())
                    .placeholder(R.drawable.pic_defaults)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatar);

            name.setText(data.getDoctor_name());
            phone.setText(data.getDoctor_phone());
            sex.setText("m".equals(data.getDoctor_sex()) ? "男" : "女");
            birthday.setText(data.getDoctor_birthday());


        }
    }

    @Override
    public void uploadImg(String data) {

        if (data != null) {

            img_str = data;
        }
    }

    @Override
    public void updateMemberDetail(String data) {

        EventBus.getDefault().post(new EventBusMessage("setting_success"));
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }


    private void editPhone() {



    }

    private void chooseSex() {

        final String mSex = sex.getText().toString();
        SinglePicker<String> singlePickerTwo = new SinglePicker<>(getActivity(), new String[]{"男", "女"});
        singlePickerTwo.setSelectedItem(mSex);
        singlePickerTwo.setCanLoop(true);//不禁用循环
        singlePickerTwo.setAnimationStyle(R.style.AnimBottom);
        singlePickerTwo.setTopBackgroundColor(0xFFEEEEEE);
        singlePickerTwo.setTopHeight(50);
        //singlePickerTwo.setTopLineColor(0xFF33B5E5);
        singlePickerTwo.setTopLineHeight(1);
        singlePickerTwo.setTitleText("请选择");
        singlePickerTwo.setTitleTextColor(0xFF999999);
        singlePickerTwo.setTitleTextSize(12);
        singlePickerTwo.setCancelTextColor(0xFF999999);
        singlePickerTwo.setCancelTextSize(13);
        singlePickerTwo.setSubmitTextColor(0xFF6EB92B);
        singlePickerTwo.setSubmitTextSize(13);
        singlePickerTwo.setSelectedTextColor(0xFFEE0000);
        singlePickerTwo.setUnSelectedTextColor(0xFF999999);
        LineConfig configTwo = new LineConfig();
        configTwo.setColor(0xFFEE0000);//线颜色
        configTwo.setAlpha(140);//线透明度
        configTwo.setRatio((float) (1.0 / 8.0));//线比率
        singlePickerTwo.setLineConfig(configTwo);
        singlePickerTwo.setItemWidth(200);
        singlePickerTwo.setBackgroundColor(0xFFE1E1E1);
        singlePickerTwo.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                sex.setText(item);
            }
        });
        singlePickerTwo.show();
    }

    private void EditName() {
    }

    private void choosebirthday() {

        final DatePicker picker = new DatePicker(getActivity());
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setLineVisible(false);
        picker.setAnimationStyle(R.style.AnimBottom);
        picker.setSubmitTextColor(getResources().getColor(R.color.colorPrimary));
        picker.setSelectedTextColor(getResources().getColor(R.color.colorPrimary));
        picker.setTopPadding(15);
        picker.setRangeStart(1800, 8, 29);
        picker.setRangeEnd(3017, 1, 11);
        picker.setSelectedItem(1993, 7, 7);
        picker.setWeightEnable(true);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                birthday.setText(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }
}
