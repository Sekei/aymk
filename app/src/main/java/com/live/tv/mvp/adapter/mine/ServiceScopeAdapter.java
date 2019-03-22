package com.live.tv.mvp.adapter.mine;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ServiceCopeBean;



/**
 * Created by wql on 2017/3/29.
 * 服务范围适配器
 */

public class ServiceScopeAdapter extends RecyclerArrayAdapter<ServiceCopeBean> {
   private ServiceCopeItemAdapter adapter;
    private Context mContext;
    private SetEditView setEditView;
    public ServiceScopeAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    public class ViewHolder extends BaseViewHolder<ServiceCopeBean> {

        private TextView name, edtv, content, price;
        private RecyclerView recyclerView;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_servicescope);
            name = $(R.id.tv_name);
            edtv = $(R.id.tv_edit);
            price = $(R.id.tv_price);
            content = $(R.id.tv_content);
            recyclerView = $(R.id.recycler_view);

        }

        @Override
        public void setData(final ServiceCopeBean data) {
            super.setData(data);
            name.setText(data.getService_name());
            price.setText("￥"+data.getService_money()+"/"+data.getService_type());
            content.setText(data.getService_desc());
            adapter=new ServiceCopeItemAdapter(mContext,data.getServiceRangeImageBeans());
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);

            edtv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (setEditView!=null){
                       setEditView.setview(data);
                    }
                }
            });

        }
    }
    public void SetEditOnClickListener(SetEditView setEditView){
        this.setEditView=setEditView;
    }
    public  interface SetEditView{
        void setview(ServiceCopeBean data);


    }
}