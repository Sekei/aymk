package com.live.tv.mvp.fragment.home;

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
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.RelationsBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.AddFilePresenter;
import com.live.tv.mvp.view.home.IAddFileView;
import com.live.tv.util.ImageFactory;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SpSingleInstance;

import java.io.File;
import java.io.FileNotFoundException;
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
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * @author Created by stone
 * @since 2018/1/17
 */

public class AddFileFragment extends BaseFragment<IAddFileView, AddFilePresenter> implements IAddFileView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.relationshipTittle)
    TextView relationshipTittle;
    @BindView(R.id.relationship)
    TextView relationship;
    @BindView(R.id.nameTittle)
    TextView nameTittle;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sexTittle)
    TextView sexTittle;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthdayTittle)
    TextView birthdayTittle;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.phoneTittle)
    TextView phoneTittle;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.all_bg)
    ConstraintLayout allBg;
    Unbinder unbinder;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String path = "";

    private List<RelationsBean> relationList = new ArrayList<>();

    public static AddFileFragment newInstance() {

        Bundle args = new Bundle();

        AddFileFragment fragment = new AddFileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_file;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.insert_record);

    }

    @Override
    public void initData() {
        getPresenter().getRelations(map);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());


    }


    @Override
    public void onInsertRecord(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void uploadImg(String data) {
        map.put("head_image", data);
        LoadingUtil.hideLoading();
    }

    @Override
    public void onGetRelations(List<RelationsBean> data) {
        relationList = data;

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
                .addFormDataPart("file", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
        LoadingUtil.showLoading(context, getResources().getString(R.string.img_put));
    }


    //打开选择关系
    private void startChooseRelationShipFragment(List<RelationsBean> list) {
        ChooseRelationFragment fragment = ChooseRelationFragment.newInstance(list, context);
        fragment.setSelectReasonOnclickListener(new ChooseRelationFragment.SelectReasonOnclickListener() {
            @Override
            public void ok(String reasonName) {
                relationship.setText(reasonName);
                // map.put("reason_name", reasonName);
                // map.put("refund_reason_id", reasonId + "");
            }
        });

        fragment.show(getFragmentManager(), AddFileFragment.class.getSimpleName());
    }

    @OnClick({R.id.ivLeft, R.id.ivAvatar, R.id.relationshipTittle, R.id.sexTittle, R.id.birthdayTittle, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivAvatar:
                show_img();
                break;
            case R.id.ok:

                if (TextUtils.isEmpty(name.getText().toString()) || name.getText().toString().equals("")) {
                    ToastUtils.showToast(getActivity(), "姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(birthday.getText().toString()) || birthday.getText().toString().equals("")) {
                    ToastUtils.showToast(getActivity(), "出生日期不能为空");
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString()) || phone.getText().toString().equals("") || phone.getText().toString().length() < 11) {

                    ToastUtils.showToast(getActivity(), "请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(relationship.getText().toString()) || relationship.getText().toString().equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入与您的关系");
                    return;
                }

                map.put("record_name", name.getText().toString());
                map.put("relation", relationship.getText().toString());
                map.put("birthday", birthday.getText().toString());
                map.put("mobile_phone", phone.getText().toString());
                map.put("sex", sex.getText().toString());
                getPresenter().insertRecord(map);
                break;
            case R.id.relationshipTittle:

                startChooseRelationShipFragment(relationList);

                break;
            case R.id.sexTittle:
                final String mSex = sex.getText().toString();
                SinglePicker<String> singlePickerTwo = new SinglePicker<>(getActivity(), new String[]{"男", "女", "其他"});
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
                break;
            case R.id.birthdayTittle:
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
                try {
                    if (!birthday.getText().toString().equals("")) {
                        String[] strings = birthday.getText().toString().split("-");
                        picker.setSelectedItem(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));

                    } else {
                        picker.setSelectedItem(1993, 7, 7);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    picker.setSelectedItem(1993, 7, 7);

                }
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
    public AddFilePresenter createPresenter() {
        return new AddFilePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
