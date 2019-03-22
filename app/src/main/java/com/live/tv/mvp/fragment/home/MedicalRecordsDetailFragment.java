package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.content.DialogInterface;
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
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.MedicalRecordsDetailPresenter;
import com.live.tv.mvp.view.home.IMedicalRecordsDetailView;
import com.live.tv.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ysjk.health.iemk.R.id.ivLeft;


/**
 * 电子病历详情
 *
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalRecordsDetailFragment extends BaseFragment<IMedicalRecordsDetailView, MedicalRecordsDetailPresenter> implements IMedicalRecordsDetailView {
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

    public static MedicalRecordsDetailFragment newInstance(MedicalListBean medicalList, int position) {

        Bundle args = new Bundle();

        MedicalRecordsDetailFragment fragment = new MedicalRecordsDetailFragment();
        fragment.medicalList = medicalList;
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
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("删除");
        medicalImgs = new ArrayList<>();
    }

    @Override
    public void initData() {
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

            convenientBanner.setcurrentitem(position);
            convenientBanner.notifyDataSetChanged();
        }

        content.setText(medicalList.getMedical_desc());


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

        ToastUtils.showToast(context.getApplicationContext(), data);
        if (medicalList.getMedicalImgBeans()!=null&&medicalList.getMedicalImgBeans().size()>1){
            Map<String, String> mMap = new HashMap<>();
            mMap.put("medical_id", medicalList.getMedical_id() + "");
            getPresenter().MedicalDetail(mMap);
        }else {
            EventBus.getDefault().post(new FirstEvent("bingli_success"));//刷新病历列表
            finish();
        }

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
        }else {
            EventBus.getDefault().post(new FirstEvent("bingli_success"));//刷新病历列表
            finish();
        }
        EventBus.getDefault().post(new FirstEvent("bingli_success"));//刷新病历列表
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

    @OnClick({ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case ivLeft:
                finish();
                break;
            case R.id.tvRight:
                //删除 图片

              Delete();

                break;
        }
    }


    //删除提示窗
    private void Delete() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否确认删除？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (convenientBanner != null) {

                    int currentItem = convenientBanner.getCurrentItem();

                    if (medicalList.getMedicalImgBeans().size() > 0) {

                        MedicalListBean.MedicalImgBeansBean medicalImgBeansBean = medicalList.getMedicalImgBeans().get(currentItem);


                        Map<String, String> mMap = new HashMap<>();
                        mMap.put("member_id", userBean.getMember_id());
                        mMap.put("member_token", userBean.getMember_token());
                        mMap.put("medical_id", medicalImgBeansBean.getMedical_id() + "");
                        mMap.put("medical_img_ids", medicalImgBeansBean.getMedical_img_id() + "");
                        getPresenter().DeleteMedicalImg(mMap);

                    }


                }


                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
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
