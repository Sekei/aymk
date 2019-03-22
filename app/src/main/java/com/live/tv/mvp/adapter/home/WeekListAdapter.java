package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultTimesBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class WeekListAdapter extends RecyclerArrayAdapter<ConsultTimesBean> {
    public WeekListAdapter(Context context, List<ConsultTimesBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public class ViewHolder extends BaseViewHolder<ConsultTimesBean> {
        private TextView time, can;
        //private EasyRecyclerView easyRecyclerView;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_is_reservation_new);
            time = $(R.id.time);
            can = $(R.id.can);
            //easyRecyclerView = $(R.id.easyRecyclerView);
            //easyRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        }

        @Override
        public void setData(ConsultTimesBean data) {
            super.setData(data);

            time.setText(data.getStart_time().substring(10,16)+"~"+data.getEnd_time().substring(10,16));


            if (data.getFlag().equals("2")){
                can.setText("可预约");
                can.setTextColor(getContext().getResources().getColor(R.color.colorAccent));

            }else {

                can.setText("不可预约");
                can.setTextColor(getContext().getResources().getColor(R.color.colorTextG6));
            }



/*            List<String> list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                list.add("可预约");
            }
            CommonAdapter adapter = new CommonAdapter<String>(getContext(), R.layout.item_is_reservation, list) {
                @Override
                protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, String s, int position) {
                    holder.setText(R.id.time, "8:00");
                    holder.setText(R.id.can, "可预约");
                }

            };
            easyRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    onClickListener.onOpen();
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });*/
        }


    }


    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private onClickListener onClickListener;

    public interface onClickListener {

        void onOpen();
    }

}
