package com.live.tv.mvp.fragment.shop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.AssessmentImgBeans;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.OrderGoodsBeans;
import com.live.tv.mvp.adapter.shop.CommentsAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.shop.AssessmentOrderPresenter;
import com.live.tv.mvp.view.shop.IAssessmentOrderView;
import com.live.tv.util.ImageFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class AssessmentOrderFragment extends BaseFragment<IAssessmentOrderView, AssessmentOrderPresenter> implements IAssessmentOrderView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.recyclerView)
    RecyclerView rvOrderGoods;
    Unbinder unbinder;
    private List<OrderGoodsBeans> orderGoodsBeansList;
    private String order_id = "";
    private CommentsAdapter commentsAdapter;
    private int clickImg;
    private int position;
    private List<AssessmentBean> GoodsCommentslist;

    public static AssessmentOrderFragment newInstance(List<OrderGoodsBeans> orderGoodsBeansList, String order_id) {

        Bundle args = new Bundle();
        AssessmentOrderFragment fragment = new AssessmentOrderFragment();
        fragment.orderGoodsBeansList = orderGoodsBeansList;
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_assessment_order;
    }

    @Override
    public void initUI() {
        tvTitle.setText("评价");
        GoodsCommentslist = new ArrayList<>();
        rvOrderGoods.setLayoutManager(new LinearLayoutManager(getContext()));
        for (OrderGoodsBeans bean : orderGoodsBeansList) {
            List<String> img = new ArrayList<>();
            img.add("");
            bean.setCommentsImgs(img);
        }
        commentsAdapter = new CommentsAdapter(context, orderGoodsBeansList);
        rvOrderGoods.setAdapter(commentsAdapter);

        commentsAdapter.setOnClickListener(new CommentsAdapter.onClickListener() {
            @Override
            public void ClickImage(int prente, int img) {
                prenterPosition = prente;
                List<String> images = commentsAdapter.getItem(prente).getCommentsImgs();
                String imagepath = images.get(img);
                if (TextUtils.isEmpty(imagepath)) {
                    int max = MAX_COUNT - images.size() + 1;
                    show_img(max);
                } else {
                    if (TextUtils.isEmpty(images.get(images.size() - 1))) {
                        images.remove(images.size() - 1);
                    }
                    images.remove(img);
                    if (images.size() < 3) {
                        images.add("");
                    }
                    commentsAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void initData() {

    }


    private void upimg(final List<String> medias) {
        new Thread() {
            @Override
            public void run() {
                try {
                    List<MultipartBody.Part> files = new ArrayList<>();
                    for (int i = 0; i < medias.size(); i++) {
                        String path = medias.get(i);
                        if (path.equals("")){
                            continue;
                        }
                        String out_path = getActivity().getExternalCacheDir().getPath() + System.currentTimeMillis() + ".jpg";
                        ImageFactory imageFactory = new ImageFactory();
//                Bitmap ratio = imageFactory.getBitmap(path);
                        Bitmap ratio = imageFactory.ratio(path, 480, 480);
                        try {
                            imageFactory.storeImage(ratio, out_path);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            if (ratio != null) {
                                ratio.recycle();
                                ratio = null;
                                imageFactory = null;
                            }
                        }
                        File _file = new File(out_path);
                        MultipartBody.Part b_cover = MultipartBody.Part.createFormData("img" + i, _file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), _file));
                        files.add(b_cover);
                    }
                    //上传图片
                    getPresenter().uploadImgs(files);

                } catch (Exception e) {
                }
            }
        }.start();


    }


    private final int MAX_COUNT = 3;
    private int prenterPosition, imgPosition;

    protected void show_img(int maxcount) {
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(maxcount).needCamera(R.drawable.ic_boxing_camera_white).needGif();

        Boxing.of(config).withIntent(getContext(), BoxingActivity.class).start(this, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OrderGoodsBeans bean = null;
        final ArrayList<BaseMedia> medias = Boxing.getResult(data);
        if (requestCode == REQUEST_CODE) {
            if (medias == null) {
                return;
            }
            List<String> imgs = new ArrayList<>();
            for (BaseMedia media : medias) {
                if (media instanceof ImageMedia) {
                    imgs.add(((ImageMedia) media).getThumbnailPath());
                }
            }

            commentsAdapter.getItem(prenterPosition).getCommentsImgs().remove(commentsAdapter.getItem(prenterPosition).getCommentsImgs().size() - 1);
            commentsAdapter.getItem(prenterPosition).getCommentsImgs().addAll(imgs);
            if (commentsAdapter.getItem(prenterPosition).getCommentsImgs().size() < 3) {
                commentsAdapter.getItem(prenterPosition).getCommentsImgs().add("");
            }
            commentsAdapter.notifyDataSetChanged();
        }
    }


    @OnClick({R.id.ivLeft, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tv_submit:
                if (GoodsCommentslist == null) {
                    GoodsCommentslist = new ArrayList<>();
                } else {
                    GoodsCommentslist.clear();
                }

                for (OrderGoodsBeans bean : orderGoodsBeansList) {
                    if (TextUtils.isEmpty(bean.getContent())) {
                        ToastUtils.showToast(context.getApplicationContext(), "请填写所有商品评价内容");
//                        progress.setVisibility(View.GONE);
//                        yes.setEnabled(true);
                        return;
                    }
                    if (bean.getCommentsImgs() == null || bean.getCommentsImgs().size() <= 0) {
                        ToastUtils.showToast(context.getApplicationContext(), "请填写所有商品评价图片");
//                        progress.setVisibility(View.GONE);
//                        yes.setEnabled(true);
                        return;
                    }
                }

                for (int i = 0; orderGoodsBeansList.size() > i; i++) {
                    position = i;
                    if (orderGoodsBeansList.get(i).getCommentsImgs() != null && orderGoodsBeansList.get(i).getCommentsImgs().size() > 0) {
                        upimg(orderGoodsBeansList.get(position).getCommentsImgs());
                        return;
                    } else {
                        OrderGoodsBeans obean = orderGoodsBeansList.get(position);
                        AssessmentBean bean = new AssessmentBean();
                        bean.setGoods_id(obean.getGoods_id());
                        bean.setOrder_id(obean.getOrder_id());
                        bean.setMember_id(userBean.getMember_id());
                        bean.setAssessment_type("goods");
                        bean.setAssessment_star1(obean.getGoods_mark() == 0 ? "5" : obean.getGoods_mark() + "");
                        bean.setAssessment_desc(obean.getContent());
                        GoodsCommentslist.add(bean);
                    }
                }

                Gson gson = new Gson();
                String content = gson.toJson(GoodsCommentslist);

                map.put("json", content);
                getPresenter().assessmentOrder(map);


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
    public AssessmentOrderPresenter createPresenter() {
        return new AssessmentOrderPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onUploadImgs(List<String> data) {

        OrderGoodsBeans obean = orderGoodsBeansList.get(position);
        AssessmentBean bean = new AssessmentBean();
        bean.setGoods_id(obean.getGoods_id());
        bean.setOrder_id(obean.getOrder_id());
        bean.setMember_id(userBean.getMember_id());
        bean.setAssessment_type("goods");
        bean.setAssessment_star1(obean.getGoods_mark() == 0 ? "3" : obean.getGoods_mark() + "");
        List<AssessmentImgBeans> assessmentImgBeansList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            AssessmentImgBeans assessmentImgBeans = new AssessmentImgBeans();
            assessmentImgBeans.setAssessment_img(data.get(i));
            assessmentImgBeansList.add(assessmentImgBeans);
        }

        bean.setAssessmentImgBeans(assessmentImgBeansList);
        bean.setAssessment_desc(obean.getContent());
        GoodsCommentslist.add(bean);

        position++;

        if (orderGoodsBeansList.size() == position) {

            Gson gson = new Gson();
            String content = gson.toJson(GoodsCommentslist);
            map.put("member_id",userBean.getMember_id());
            map.put("member_token",userBean.getMember_token());
            map.put("json", content);
            getPresenter().assessmentOrder(map);

        } else {
            for (int i = position; orderGoodsBeansList.size() > i; i++) {
                OrderGoodsBeans obean1 = orderGoodsBeansList.get(i);
                position = i;
                if (obean1.getCommentsImgs() != null && obean1.getCommentsImgs().size() > 0) {
                    upimg(orderGoodsBeansList.get(position).getCommentsImgs());
                    return;
                } else {
                    AssessmentBean bean1 = new AssessmentBean();
                    bean1.setGoods_id(obean1.getGoods_id());
                    bean.setOrder_id(obean.getOrder_id());
                    bean.setMember_id(userBean.getMember_id());
                    bean.setAssessment_type("goods");
                    bean.setAssessment_star1(obean.getGoods_mark() == 0 ? "3" : obean.getGoods_mark() + "");
                    bean1.setAssessment_desc(obean1.getContent());
                    GoodsCommentslist.add(bean1);
                }
            }

            Gson gson = new Gson();
            String content = gson.toJson(GoodsCommentslist);
            map.put("json", content);
            Log.i("dfc", "onUploadImgs: ");
             getPresenter().assessmentOrder(map);
        }


    }

    @Override
    public void onAssessmentOrder(String data) {

        ToastUtils.showToast(context.getApplicationContext(),"评价成功");
        EventBus.getDefault().post(new FirstEvent("order_success"));
        finish();

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
}
