package com.live.tv.mvp.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MedicalClassBean;
import com.live.tv.bean.MerchantServiceListBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.mvp.adapter.UploadCaseImageAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.PublicEditFragment;
import com.live.tv.mvp.fragment.huanxin.Constant;
import com.live.tv.mvp.presenter.mine.EditServicePresenter;
import com.live.tv.mvp.view.mine.IEditServiceView;

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
 * Created by mac1010 on 2018/8/29.
 * 编辑服务
 */

public class EditServiceFragment extends BaseFragment<IEditServiceView, EditServicePresenter> implements IEditServiceView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tv_heard)
    TextView tvHeard;
    @BindView(R.id.iv_heard)
    TextView ivHeard;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.type)
    TextView mtype;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.service)
    TextView service;
    private UploadCaseImageAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private List<MedicalClassBean> medList = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();
    private ServiceCopeBean mServiceCopeBean;
    private String data;
    Unbinder unbinder;
    private static final int IMAGE = 0X02;

    public static EditServiceFragment newInstance() {


        EditServiceFragment fragment = new EditServiceFragment();


        return fragment;
    }
    public static EditServiceFragment newInstance(String serviceCopeBean) {


        EditServiceFragment fragment = new EditServiceFragment();

            fragment.data=serviceCopeBean;
        return fragment;
    }
    @Override
    public int getRootViewId() {
        return R.layout.fragment_editservice;
    }

    @Override
    public void initUI() {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");
        tvRight.setTextColor(getResources().getColor(R.color.textcolor));
        tvTitle.setText("编辑服务");
        ivLeft.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        mAdapter = new UploadCaseImageAdapter(list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (position == adapter.getData().size() - 1) {
                    show_img(IMAGE);
                }
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                adapter.getData().remove(position);
                list = adapter.getData();
                mAdapter.setNewData(list);


            }
        });
    }

    @Override
    public void initData() {

       mServiceCopeBean=new Gson().fromJson(data,ServiceCopeBean.class);
        if (mServiceCopeBean!=null){
            ivHeard.setText(mServiceCopeBean.getService_name());
            mtype.setText(mServiceCopeBean.getService_money());
            edContent.setText(mServiceCopeBean.getService_desc());
            service.setText(mServiceCopeBean.getService_type());
            if (mServiceCopeBean.getServiceRangeImageBeans()!=null&&mServiceCopeBean.getServiceRangeImageBeans().size()>0){
                for (int i=0;i<mServiceCopeBean.getServiceRangeImageBeans().size();i++){

                  list.add(Constants.BASE_URL+mServiceCopeBean.getServiceRangeImageBeans().get(i).getService_image_url());
                }
                list.add("");
                mAdapter.setNewData(list);


            }else {
                list.add("");
                mAdapter.setNewData(list);
            }


        }else {
            list.add("");
            mAdapter.setNewData(list);
        }

    }

    @Override
    public EditServicePresenter createPresenter() {
        return new EditServicePresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
ToastUtils.showToast(getActivity(),e.getMessage());
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(9 - (list.size() - 1)).needCamera(R.drawable.ic_boxing_camera_white).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }


    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    list = mAdapter.getData();
                    list.remove(list.size() - 1);


                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {

                            list.add(((ImageMedia) media).getThumbnailPath());
//                            mAdapter.addData();
                        } else {
                            //mAdapter.addData(media.getPath());
                            list.add(media.getPath());
                        }
                    }
                    list.add("");
                    mAdapter.setNewData(list);
                }

        }
    }

    //上传图片
    private void upImage() {
        final List<MultipartBody.Part> files = new ArrayList<>();


        final List<String> allData = mAdapter.getData();
        Luban.with(getActivity())
                .load(allData)                                   // 传人要压缩的图片列表
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
                        if (files.size() == allData.size() - 1) {
                            getPresenter().uploadImgs(files);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.e("-------------------", "当压缩过程出现问题时调用");
                    }
                }).launch();    //启动压缩

    }

    @Override
    public void onUploadImgs(String[] data) {
        if (data != null) {
            MerchantServiceListBean listBean = new MerchantServiceListBean();
            listBean.setHouse_service_id(userBean.getHouse_service_id());
            listBean.setService_desc(edContent.getText().toString());
            listBean.setService_money(mtype.getText().toString());
            listBean.setService_name(ivHeard.getText().toString());
            listBean.setService_type(service.getText().toString());
            if (mServiceCopeBean!=null){
                listBean.setService_id(mServiceCopeBean.getService_id()+"");
            }

            List<MerchantServiceListBean.ServiceRangeImageBeansBean> imgs = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {

                MerchantServiceListBean.ServiceRangeImageBeansBean beansBean = new MerchantServiceListBean.ServiceRangeImageBeansBean();
                beansBean.setService_image_url(data[i]);
                imgs.add(beansBean);

            }
            listBean.setServiceRangeImageBeans(imgs);

            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("json", new Gson().toJson(listBean));
            if (mServiceCopeBean!=null){
                getPresenter().getupdateServiceRange(map);

            }else {
                getPresenter().geteditService(map);

            }
        }


    }

    @Override
    public void onUploadImgserror(String data) {
        ToastUtils.showToast(getActivity(),"图片上传异常,请重新上传图片");
    }

    @Override
    public void OnaddService(String str) {
        ToastUtils.showToast(getActivity(), str);
        finish();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tv_heard, R.id.tv_type,R.id.tv_service})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:

                finish();
                break;
            case R.id.tvRight:
                if (userBean != null) {
                   if (TextUtils.isEmpty(service.getText().toString())){

                       ToastUtils.showToast(context.getApplicationContext(), "请输入类型");

                       return;
                   }
                    if (TextUtils.isEmpty(ivHeard.getText().toString())){

                        ToastUtils.showToast(context.getApplicationContext(), "请输入名称");

                        return;
                    }
                    if (TextUtils.isEmpty(mtype.getText().toString())){

                        ToastUtils.showToast(context.getApplicationContext(), "请输入价格");

                        return;
                    }

                    if (list.size() > 1) {
                        upImage();
                    } else {
                        ToastUtils.showToast(context.getApplicationContext(), "请上传图片");
                    }
                } else {
                    startLogin();
                }

                break;
            case R.id.tv_heard:

                SeviceNameFragment("", "string", "请输入服务名称", "服务名称", getActivity());
                break;
            case R.id.tv_type:

                SevicepriceFragment("", "num", "请输入服务价格", "服务价格", getActivity());
                break;
            case R.id.tv_service:

                SevicetypeFragment("", "string", "例如:次,月,年", "服务的类型", getActivity());

                break;
        }
    }


    public void SeviceNameFragment(String s, String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                ivHeard.setText(state);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }

    public void SevicepriceFragment(String s, final String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                mtype.setText(state);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }
    public void SevicetypeFragment(String s, String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                service.setText(state);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }
}
