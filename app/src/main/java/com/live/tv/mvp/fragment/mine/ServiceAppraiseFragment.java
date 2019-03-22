package com.live.tv.mvp.fragment.mine;

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
import android.widget.RatingBar;
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
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.adapter.UploadCaseImageAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.ServiceAppraisePresenter;
import com.live.tv.mvp.view.mine.IServiceAppraiseView;

import java.io.File;
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
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mac1010 on 2018/9/3.
 * 服务评价
 */

public class ServiceAppraiseFragment extends BaseFragment<IServiceAppraiseView, ServiceAppraisePresenter> implements IServiceAppraiseView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private UploadCaseImageAdapter mAdapter;
    private static final int IMAGE = 0X02;
    private List<String> list = new ArrayList<>();
       @BindView(R.id.tv_commit)
        TextView tvCommit;
    Unbinder unbinder;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_serviceappraise;
    }

    @Override
    public void initUI() {
   tvTitle.setText("评价");

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
        list.add("");
        mAdapter.setNewData(list);
    }

    @Override
    public ServiceAppraisePresenter createPresenter() {
        return new ServiceAppraisePresenter(getApp());
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

    @OnClick({R.id.ivLeft,R.id.tv_commit})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.ivLeft:

                finish();
                break;
            case R.id.tv_commit:

                upImage();
                break;
        }
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
        /**
         * 图片上传成功的返回数据
         */

    }
}
