package com.live.tv.mvp.adapter.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.fragment.home.ConsultWeek;

import java.util.List;


/**
 *健康管理设置
 */

public class PhoneEditAdapter extends BaseQuickAdapter<ConsultWeek, BaseViewHolder> {
    public PhoneEditAdapter(List<ConsultWeek> data) {
        super(R.layout.item_weeks, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultWeek item) {

        helper.setText(R.id.tv_week,item.getWeekday());

        helper.addOnClickListener(R.id.tv_edit);
        RecyclerView  recyclerView =helper.getView(R.id.recyclerView);
        if (item.getConsultSetBeans()!=null&&item.getConsultSetBeans().size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            ConsultTimeAdapter consultTimeAdapter = new ConsultTimeAdapter(item.getConsultSetBeans());
            recyclerView.setAdapter(consultTimeAdapter);

        }else {
            recyclerView.setVisibility(View.GONE);
        }



    }
}
