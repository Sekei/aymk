package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MedicalRecordsDetailPresenter;
import com.live.tv.mvp.view.home.IMedicalRecordsDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ysjk.health.iemk.R.id.ivLeft;


/**
 * 环信聊天
 * 电子病历详情
 *
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalRecordsDetailHxFragment extends BaseFragment<IMedicalRecordsDetailView, MedicalRecordsDetailPresenter> implements IMedicalRecordsDetailView {
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;
    @BindView(R.id.convenietnBanner)
    ConvenientBanner convenientBanner;
    private MedicalListBean medicalList;
    private List<MedicalListBean.MedicalImgBeansBean> medicalImgs;

    private int position;
    private String medical_id;

    public static MedicalRecordsDetailHxFragment newInstance(String medical_id, int position) {

        Bundle args = new Bundle();

        MedicalRecordsDetailHxFragment fragment = new MedicalRecordsDetailHxFragment();
        fragment.medical_id = medical_id;
        fragment.position = position;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_medical_records_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("详情");
        medicalImgs = new ArrayList<>();
    }

    @Override
    public void initData() {
        map.clear();
        map.put("medical_id", medical_id);
        getPresenter().MedicalDetail(map);

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
    public void onDeleteMedicalImg(String data) {


    }

    @Override
    public void onMedicalDetail(MedicalListBean data) {


        medicalList = data;
        if (medicalList.getMedicalImgBeans()!=null&&medicalList.getMedicalImgBeans().size()>0) {

            if (convenientBanner != null) {
                toSetList(medicalImgs, medicalList.getMedicalImgBeans(), false);
                convenientBanner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Holder<MedicalListBean.MedicalImgBeansBean> createHolder() {
                        return new ImageHolder();
                    }
                }, medicalImgs)
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .stopTurning();

                convenientBanner.notifyDataSetChanged();
            }
        }

        content.setText(medicalList.getMedical_desc());
    }

    public class ImageHolder implements Holder<MedicalListBean.MedicalImgBeansBean> {
        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, MedicalListBean.MedicalImgBeansBean data) {
            Glide.with(context).load(Constants.BASE_URL + data.getMedical_img()).error(R.drawable.banner_default).placeholder(R.drawable.banner_default).into(iv);
        }
    }

    @OnClick({ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case ivLeft:
                finish();
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
    public MedicalRecordsDetailPresenter createPresenter() {
        return new MedicalRecordsDetailPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
