package com.live.tv.mvp.fragment.shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.OrderGoodsBeans;
import com.live.tv.bean.RefundsReasons;
import com.live.tv.mvp.adapter.UploadCaseImageAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.DialogRefTypeFragment;
import com.live.tv.mvp.fragment.dialog.DialogRefundReasonFragment;
import com.live.tv.mvp.presenter.shop.ApplyAfterSalePresenter;
import com.live.tv.mvp.view.shop.IApplyAfterSaleView;

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
import static com.ysjk.health.iemk.R.id.tv_price1;
import static com.ysjk.health.iemk.R.id.tv_reason;


/**
 * 申请售后
 * Created by sh-lx on 2017/7/12.
 */

public class ApplyAfterSaleFragment extends BaseFragment<IApplyAfterSaleView, ApplyAfterSalePresenter> implements IApplyAfterSaleView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img_merchants)
    ImageView imgMerchants;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_nums)
    TextView tvNums;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_cancel)
    TextView tvTime;
    @BindView(R.id.tv_after_sale)
    TextView tvAfterSale;
    @BindView(tv_reason)
    TextView tvReason;
    @BindView(tv_price1)
    TextView tvPrice1;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.ed_shuoming)
    EditText edShuoming;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    Unbinder unbinder;
    private OrderGoodsBeans orderGoodsBeans;
    private UploadCaseImageAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private static final int IMAGE = 0X02;
    private String refund_type = "1";
    private List<RefundsReasons> refundsReasonsList;
    private RefundsReasons refundsReasons;

    public static ApplyAfterSaleFragment newInstance(OrderGoodsBeans orderGoodsBeans) {
        Bundle args = new Bundle();
        ApplyAfterSaleFragment fragment = new ApplyAfterSaleFragment();
        fragment.orderGoodsBeans = orderGoodsBeans;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_apply_after_sale;
    }

    @Override
    public void initUI() {
        tvTitle.setText("申请售后");

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
        getPresenter().getRefundsReasons();

        if (orderGoodsBeans != null) {
            Glide.with(context).load(Constants.BASE_URL + orderGoodsBeans.getGoods_img())
                    .placeholder(R.drawable.pic_defaults)
                    .error(R.drawable.pic_defaults).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imgMerchants);

            tvGoodName.setText(orderGoodsBeans.getGoods_name());
            tvPrice.setText("¥" + orderGoodsBeans.getSpecification_price());
            tvTime.setText(orderGoodsBeans.getGoods_num());//数量
            tvNums.setText(orderGoodsBeans.getSpecification_names());//规格

            int num = Integer.parseInt(orderGoodsBeans.getGoods_num());

            double price_double = Double.parseDouble(orderGoodsBeans.getSpecification_price());

            double sum_price = price_double * num;

            tvPrice1.setText("¥" + sum_price + "");

        }

    }

    @OnClick({R.id.ivLeft, R.id.tv_after_sale, tv_reason, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tv_after_sale:

                DialogRefTypeFragment dialogRefTypeFragment = new DialogRefTypeFragment();

                dialogRefTypeFragment.setOnSelectListener(new DialogRefTypeFragment.SelectListener() {
                    @Override
                    public void OnSelectListener(String ids, String name) {
                        tvAfterSale.setText(name);
                        refund_type = ids;
                    }
                });
                dialogRefTypeFragment.show(getFragmentManager(), "dialogRefTypeFragment");

                break;
            case R.id.tv_submit:

                if (refundsReasons == null) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择退款原因");
                    return;
                }
                if (edShuoming.getText().toString().trim().length() == 0) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入退款说明");
                    return;
                }
                //凭证上传（选择性上传）
                if (list.size() - 1 >= 1) {
                    upImage();
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_id", orderGoodsBeans.getOrder_id());
                    map.put("order_goods_id", orderGoodsBeans.getOrder_goods_id());
                    map.put("refund_type", refund_type);
                    map.put("refund_reason_id", refundsReasons.getRefund_reason_id());
                    map.put("reason_name", refundsReasons.getReason_name());
                    map.put("refund_desc", edShuoming.getText().toString().trim());
                    map.put("refund_count", orderGoodsBeans.getGoods_num());
                    map.put("refund_imgs", "");
                    Log.i("", "onUploadImgs: ");
                    getPresenter().refundOrder(map);
                }

                break;
            case tv_reason:
                if (refundsReasonsList != null) {
                    startDialogRefundReasonFragment();
                }

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
    public ApplyAfterSalePresenter createPresenter() {
        return new ApplyAfterSalePresenter(getApp());
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

        errorHandle(e);
    }


    public void startDialogRefundReasonFragment() {
        final DialogRefundReasonFragment fragment = DialogRefundReasonFragment.newInstance(refundsReasonsList, context);

        fragment.setOnSelectListener(new DialogRefundReasonFragment.SelectListener() {
            @Override
            public void OnSelectListener(RefundsReasons Reason) {

                refundsReasons = Reason;
                tvReason.setText(Reason.getReason_name());
                fragment.dismiss();

            }
        });
        fragment.show(getFragmentManager(), "DialogRefundReasonFragment");

    }


    @Override
    public void onUploadImgs(String[] data) {
        if (data != null) {
            // List<MedicalListBean> meds = new ArrayList<>();
            String img_str = "";

            for (int i = 0; i < data.length; i++) {

                img_str += data[i] + ",";

            }


            if (!img_str.equals("")) {

                img_str = img_str.substring(0, img_str.length() - 1);
            }

            Map<String, String> map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("order_id", orderGoodsBeans.getOrder_id());
            map.put("order_goods_id", orderGoodsBeans.getOrder_goods_id());
            map.put("refund_type", refund_type);
            map.put("refund_reason_id", refundsReasons.getRefund_reason_id());
            map.put("reason_name", refundsReasons.getReason_name());
            map.put("refund_desc", edShuoming.getText().toString().trim());
            map.put("refund_count", orderGoodsBeans.getGoods_num());
            map.put("refund_imgs", img_str);
            Log.i("", "onUploadImgs: ");
            getPresenter().refundOrder(map);

        }
    }

    @Override
    public void onRefundsReasons(List<RefundsReasons> data) {

        if (data != null && data.size() > 0) {
            refundsReasonsList = data;

        }

    }

    @Override
    public void onRefundOrder(String data) {

        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6 - (list.size() - 1)).needCamera(R.drawable.ic_boxing_camera_white).withCropOption(new BoxingCropOption(destUri))
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

}
