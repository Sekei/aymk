package com.live.tv.mvp.fragment.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.TextConsultBean;
import com.live.tv.bean.TextandImgBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.ImageAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.RequestOnePresenter;
import com.live.tv.mvp.view.home.IRequestOneView;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * 编辑图文咨询的内容
 * @author Created by stone
 * @since 2018/1/10
 */

public class RequestOneFragment extends BaseFragment<IRequestOneView, RequestOnePresenter> implements IRequestOneView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    Unbinder unbinder;
    @BindView(R.id.name)
    TextView name;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private ImageAdapter adapter;
    private List<String> list = new ArrayList<>();
    private static final int IMAGE = 0X02;
    private String health_record_id;
    private String doctor_id;
    private String doctor_name;
    private String price;

    private TextConsultBean textConsultBean = new TextConsultBean();
    private String  Hx_account;

    public static Fragment instance = null;
    List<String> uri_strs  = new ArrayList<>();
    public static RequestOneFragment newInstance(String doctor_id,String doctor_name,String price,String Hx_account) {

        Bundle args = new Bundle();
        RequestOneFragment fragment = new RequestOneFragment();
        fragment.doctor_id = doctor_id;
        fragment.doctor_name = doctor_name;
        fragment.price = price;
        fragment.Hx_account = Hx_account;
        instance=fragment;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_request_one;
    }

    @Override
    public void initUI() {
        tvTitle.setText("图文咨询");
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        list.add("");
        adapter = new ImageAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                show_img(IMAGE);
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("health_record_id", userBean.getHealthRecordBean().getHealth_record_id());
        getPresenter().healthRecordDetail(map);
    }

    @Override
    public void onUploadImgs(String[] data) {

        List<TextConsultBean.ConsultImagesBean> lsit = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            TextConsultBean.ConsultImagesBean imgBean = new TextConsultBean.ConsultImagesBean();
            imgBean.setConsult_image_url(data[i]);
            lsit.add(imgBean);
        }
        textConsultBean.setConsultImages(lsit);
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context.getApplicationContext(), "上传完成");
    }

    @Override
    public void onTextConsult(String data) {
        //startPayServerFragment("0","1");
        finish();

    }

    @Override
    public void onHealthRecordDetail(HealthRecordDetailBean data) {
        health_record_id = data.getHealth_record_id();
        name.setText(data.getRecord_name() + "(" + data.getRelation() + ")");
    }

    /***
     * 图文咨询生成订单
     * @param data
     */
    @Override
    public void onBuyConsult(TextandImgBean data) {
         startBuyConsultImageFragment(data.getOrder_id(),doctor_name,Hx_account,price,uri_strs,textConsultBean.getConsult_record_desc());

    }


    @OnClick({R.id.ivLeft, R.id.tittle, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tittle:
                startMoreConsultantFragment();
                break;
            case R.id.ok:


                if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请填写要咨询的信息");
                    return;
                }

          /* if (uri_strs==null||uri_strs.size()==0){
               ToastUtils.showToast(context.getApplicationContext(), "请上传图片");
                   return;
           }*/
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                textConsultBean.setDoctor_id(doctor_id);
                textConsultBean.setMember_id(userBean.getMember_id());
                textConsultBean.setConsult_record_desc(etContent.getText().toString());
              //  textConsultBean.setHealth_record_id(health_record_id);
                map.put("json",new Gson().toJson(textConsultBean));
                map.put("order_actual_price",price);
                map.put("doctor_id",doctor_id);
                map.put("health_record_id",health_record_id);
                getPresenter().buyConsult(map);



               // startBuyConsultImageFragment(price,doctor_name,textConsultBean,uri_strs,Hx_account);

                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUESTCODE:
                if (data != null) {
                    String stringExtra = data.getStringExtra(Constants.HEALTH_RECORD_ID);
                    health_record_id=stringExtra;
                    map.clear();
                    map.put("health_record_id", stringExtra);
                    getPresenter().healthRecordDetail(map);
                }
                break;
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    adapter.clear();
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());
                            uri_strs.add(((ImageMedia) media).getThumbnailPath());
                        } else {
                            adapter.add(media.getPath());
                        }
                    }
                    if (adapter.getAllData().size() > 0) {
                        upImage();
                        LoadingUtil.showLoading(getActivity(), "图片上传中...");
                    }

                    adapter.notifyDataSetChanged();
                }

                break;
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

    @Override
    public RequestOnePresenter createPresenter() {
        return new RequestOnePresenter(getApp());
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }

    List<File> files;

    //上传图片
    private void upImage() {
        new Thread() {
            @Override
            public void run() {
                files = new ArrayList<>();

                for (int i = 0; i < adapter.getAllData().size() - 1; i++) {
                    String path = adapter.getAllData().get(i);

                    if (TextUtils.isEmpty(path)) {
                        return;
                    }
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

                    files.add(_file);
                }

                //构建body
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("path", "/images/member");
                for (int i = 0;  i<files.size(); i++) {
                    builder.addFormDataPart("file", files.get(i).getName(), RequestBody.create(MediaType.parse("image/jpeg"), files.get(i)));
                }
                RequestBody requestBody = builder.build();
                getPresenter().uploadImgs(requestBody);


            }
        }.start();




    }








}