package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.flexbox.FlexboxLayout;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.PublicEditFragment;
import com.live.tv.mvp.fragment.mine.PersonalInfoFragment;
import com.live.tv.mvp.presenter.home.MerchantMessagePresenter;
import com.live.tv.mvp.view.home.IMerchantMessageView;
import com.live.tv.util.ImageFactory;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.editutils.StringUtils;
import com.lljjcoder.citypickerview.widget.CityPicker;

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
import cn.addapp.pickers.picker.SinglePicker;
import de.hdodenhof.circleimageview.CircleImageView;
import freemarker.ext.beans.BeansWrapper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by mac1010 on 2018/8/28.
 */

public class MerchantMessageFragment extends BaseFragment<IMerchantMessageView, MerchantMessagePresenter> implements IMerchantMessageView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.tv_heard)
    TextView tvHeard;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.v4)
    View v4;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.v5)
    View v5;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.v6)
    View v6;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.v7)
    View v7;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.FlexboxLayout)
    FlexboxLayout FlexboxLayout;
    @BindView(R.id.v8)
    View v8;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.v9)
    View v9;
    @BindView(R.id.tv_detailed)
    TextView tvDetailed;
    @BindView(R.id.v10)
    View v10;
    Unbinder unbinder;
    @BindView(R.id.iv_heard)
    CircleImageView ivHeard;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.eara)
    TextView eara;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.detailed)
    TextView detailed;
    private String path;
    private Map<String,String> map=new HashMap<>();

    public static MerchantMessageFragment newInstance() {

        MerchantMessageFragment fragment = new MerchantMessageFragment();


        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchantmessage;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的消息");
        ivLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {


    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("house_service_id",userBean.getHouse_service_id());
        getPresenter().getHousekeepDetail(map);
    }

    @Override
    public MerchantMessagePresenter createPresenter() {
        return new MerchantMessagePresenter(getApp());
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

    @OnClick({R.id.ivLeft,R.id.tv_heard,R.id.tv_type,R.id.tv_area,R.id.tv_name,R.id.tv_phone,R.id.tv_address,R.id.tv_account,
    R.id.tv_content,R.id.tv_detailed})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:

                finish();
                break;
            case R.id.tv_heard:
                show_img();
                break;
            case R.id.tv_type:
                final String mIssex = type.getText().toString();
                SinglePicker<String> singlePickersex = new SinglePicker<>(getActivity(), new String[]{"保洁", "月嫂"});
                singlePickersex.setCanLoop(true);//不禁用循环
                singlePickersex.setAnimationStyle(R.style.AnimBottom);
                singlePickersex.setTopBackgroundColor(0xFFEEEEEE);
                singlePickersex.setTopHeight(50);
                singlePickersex.setTopLineHeight(1);
                singlePickersex.setTitleText("请选择");
                singlePickersex.setTitleTextColor(0xFF999999);
                singlePickersex.setTitleTextSize(12);
                singlePickersex.setCancelTextColor(0xFF999999);
                singlePickersex.setCancelTextSize(13);
                singlePickersex.setSubmitTextColor(0xFF6EB92B);
                singlePickersex.setSubmitTextSize(13);
                singlePickersex.setSelectedTextColor(0xFF6EB92B);
                singlePickersex.setUnSelectedTextColor(0xFF999999);
                LineConfig configsex = new LineConfig();
                configsex.setColor(0xFF6EB92B);//线颜色
                configsex.setAlpha(140);//线透明度
                configsex.setRatio((float) (1.0 / 8.0));//线比率
                singlePickersex.setLineConfig(configsex);
                singlePickersex.setItemWidth(200);
                singlePickersex.setBackgroundColor(0xFFE1E1E1);
                singlePickersex.setSelectedItem(mIssex);
                singlePickersex.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        type.setText(item);

                    }
                });
                singlePickersex.show();
                break;
            case R.id.tv_area:
                startFragment("", "String","请输入...","默认",getActivity(),1);

                break;
            case R.id.tv_name:
                startFragment("", "String","请输入...","默认",getActivity(),2);

                break;
            case R.id.tv_phone:
                startFragment("", "phone","请输入...","默认",getActivity(),3);

                break;
            case R.id.tv_address:
                cityPicker();
                break;
            case R.id.tv_account:
                startermerchantaboutFragment();
                break;
            case R.id.tv_content:

                startservicecopeFragment();
                break;
            case R.id.tv_detailed:
                startFragment("", "String","请输入...","默认",getActivity(),4);

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
                    BoxingMediaLoader.getInstance().displayThumbnail(ivHeard, path, 480, 480);
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


    public void startFragment(String s, String type, String hint, String title, Context context, final int a) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s,type,hint,title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {


               switch (a){
                   case 1:
                       //phone.setText(state);
                       map.clear();
                       map.put("member_id", userBean.getMember_id());
                       map.put("member_token", userBean.getMember_token());
                       map.put("member_phone", state);
                       map.put("house_service_id",userBean.getHouse_service_id());

                       getPresenter().getCHangemessage(map);
                       break;
                   case 2:
                       name.setText(state);
                       map.clear();
                       map.put("member_id", userBean.getMember_id());
                       map.put("member_token", userBean.getMember_token());
                       map.put("house_service_name", state);
                       map.put("house_service_id",userBean.getHouse_service_id());

                       getPresenter().getCHangemessage(map);
                       break;
                   case 3:
                       phone.setText(state);
                       map.clear();
                       map.put("member_id", userBean.getMember_id());
                       map.put("member_token", userBean.getMember_token());
                       map.put("contact_phone", state);
                       map.put("house_service_id",userBean.getHouse_service_id());

                       getPresenter().getCHangemessage(map);

                       break;
                   case 4:
                       detailed.setText(state);
                       map.clear();
                       map.put("member_id", userBean.getMember_id());
                       map.put("member_token", userBean.getMember_token());
                       map.put("house_address", state);
                       map.put("house_service_id",userBean.getHouse_service_id());

                       getPresenter().getCHangemessage(map);

                       break;







               }


                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }

    public  String province = "";
    public String city = "";
    public  String country = "";
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
                address.setText(province+" "+city+" "+country);

                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("house_province", province);
                map.put("house_city", city);
                map.put("house_country", country);
                map.put("house_service_id",userBean.getHouse_service_id());

                getPresenter().getCHangemessage(map);
            }

            @Override
            public void onCancel() {


            }
        });
    }


    @Override
    public void onGetHousekeepDetail(HousekeepBean housekeepBean) {
        //获取数据
        Glide.with(getContext()).load(Constants.BASE_URL + housekeepBean.getHouse_service_image()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivHeard);
        type.setText(housekeepBean.getService_type());
        eara.setText("");
        name.setText(housekeepBean.getHouse_service_name());
        phone.setText(housekeepBean.getContact_phone());
        content.setText(housekeepBean.getHouse_service_desc());
        String[] servicetag=housekeepBean.getService_range().split(",");
        if (servicetag!=null&&servicetag.length>0){
            FlexboxLayout.removeAllViews();
            for (int i=0;i<servicetag.length;i++){

                final TextView word = new TextView(getContext());
                word.setTextColor(getContext().getResources().getColor(R.color.tx_l));
                word.setText(servicetag[i]);
                word.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.y26));
                word.setBackgroundResource(R.drawable.newshape_line);
                FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(12, 5, 12, 5);
                word.setLayoutParams(lp);
                FlexboxLayout.addView(word);

            }


        }
        address.setText(housekeepBean.getHouse_province()+"-"+housekeepBean.getHouse_city()+"-"+housekeepBean.getHouse_country());
        detailed.setText(housekeepBean.getHouse_address());
    }

    @Override
    public void onChangeSuccess(String str) {
        ToastUtils.showToast(getActivity(),str);
    }

    @Override
    public void uploadImg(String data) {
        LoadingUtil.hideLoading();
        Glide.with(getContext()).load(Constants.BASE_URL + data).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivHeard);
        map.clear();
        map.put("member_id",userBean.getMember_id());
        map.put("member_token",userBean.getMember_token());
        map.put("house_service_image",data);
        map.put("house_service_id",userBean.getHouse_service_id());
        getPresenter().getCHangemessage(map);
    }


}
