package com.live.tv.mvp.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.MedicalClassBean;
import com.live.tv.mvp.adapter.dialogadapter.MedicalClassAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *病历分类
 *
 * @author Created by stone
 * @since 2017/11/29
 */

public class MedicalClassDialog extends BottomSheetDialogFragment {


    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MedicalClassAdapter mAdapter;

    private List<MedicalClassBean> doctorTitleList;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {

        dismiss();
    }

    public interface OKOnclickListener {
        void onOk(String id,String title);
    }

    private OKOnclickListener onclickListener;

    public void setOkClickListener(OKOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    private String name;
    Unbinder unbinder;
    Context context;

    public static MedicalClassDialog newInstance(List<MedicalClassBean> doctorTitleList, Context context) {
        Bundle args = new Bundle();
        MedicalClassDialog fragment = new MedicalClassDialog();
        fragment.doctorTitleList = doctorTitleList;
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_med_class, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();

        return view;
    }

    private void initViews() {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter=new MedicalClassAdapter(doctorTitleList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                MedicalClassBean medicalClassBean = (MedicalClassBean) adapter.getData().get(position);
                onclickListener.onOk(medicalClassBean.getMedical_class_id(),medicalClassBean.getMedical_class_title());
                dismiss();
            }
        });


    }


}
