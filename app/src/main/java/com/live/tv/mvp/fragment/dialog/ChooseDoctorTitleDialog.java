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
import com.live.tv.bean.DoctorTitleBean;
import com.live.tv.mvp.adapter.mine.ChooseDoctorTitleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 选择医生职称
 *
 * @author Created by stone
 * @since 2017/11/29
 */

public class ChooseDoctorTitleDialog extends BottomSheetDialogFragment {


    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ChooseDoctorTitleAdapter mAdapter;

    private List<DoctorTitleBean> doctorTitleList;
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
        void onOk(String state);
    }

    private OKOnclickListener onclickListener;

    public void setOkClickListener(OKOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    private String name;
    Unbinder unbinder;
    Context context;

    public static ChooseDoctorTitleDialog newInstance(List<DoctorTitleBean> doctorTitleList, Context context) {
        Bundle args = new Bundle();
        ChooseDoctorTitleDialog fragment = new ChooseDoctorTitleDialog();
        fragment.doctorTitleList = doctorTitleList;
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_doctor_title, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter=new ChooseDoctorTitleAdapter(doctorTitleList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                DoctorTitleBean doctorTitleBean = (DoctorTitleBean) adapter.getData().get(position);
                onclickListener.onOk(doctorTitleBean.getDoctor_title());
                dismiss();
            }
        });


    }


}
