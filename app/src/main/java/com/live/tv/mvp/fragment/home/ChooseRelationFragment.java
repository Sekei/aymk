package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.RelationsBean;
import com.live.tv.mvp.adapter.home.SelectRelationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public class ChooseRelationFragment extends BottomSheetDialogFragment {

    public interface SelectReasonOnclickListener {
        void ok(String orderContent);

    }

    private SelectReasonOnclickListener onclickListener;

    public void setSelectReasonOnclickListener(SelectReasonOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    @BindView(R.id.recyclerView)
    RecyclerView easyRecyclerView;
    Unbinder unbinder;
    private SelectRelationAdapter adapter;
    List<RelationsBean> relationList = new ArrayList<>();
    private Context context;

    public static ChooseRelationFragment newInstance(List<RelationsBean> list, Context context) {
        Bundle args = new Bundle();
        ChooseRelationFragment fragment = new ChooseRelationFragment();
        fragment.context = context;
        fragment.relationList = list;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_relation, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        adapter = new SelectRelationAdapter(context, relationList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onclickListener.ok(adapter.getItem(position).getRelation_name());
                dismiss();
            }
        });
    }

    @OnClick({R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;


        }
    }


}
