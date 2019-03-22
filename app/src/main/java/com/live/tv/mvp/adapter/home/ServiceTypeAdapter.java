package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.bean.MemberMsgsBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by mac1010 on 2018/9/12.
 * 医生服务类型的适配器
 *
 */

public class ServiceTypeAdapter extends RecyclerArrayAdapter<HealthManagerBeans> {

   private OnEditChangelisenter monEditChangelisenter;
    public ServiceTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<HealthManagerBeans> {

        private TextView content,time,price,tv_edit;



        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_service_type);
            content = $(R.id.tv_servicecontet);
            time = $(R.id.tv_time);
            price=$(R.id.tv_price);
            tv_edit=$(R.id.tv_edit_2);

        }

        @Override
        public void setData(final HealthManagerBeans person) {
            Log.i("ViewHolder", "position" + getDataPosition());
            content.setText(person.getService_desc());
            time.setText(person.getService_title());
            price.setText(person.getService_price()+" 元");
            tv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monEditChangelisenter!=null){
                        monEditChangelisenter.setData(person);
                    }
                }
            });

        }
    }

public void SetOnClickLisenter(OnEditChangelisenter onEditChangelisenter){
    monEditChangelisenter=onEditChangelisenter;
}

 public interface OnEditChangelisenter{

     void setData(HealthManagerBeans beans);
 }
}