package com.live.tv.mvp.adapter.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultTimesBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 */

public class ConsultTimeAdapter extends BaseQuickAdapter<ConsultTimesBean, BaseViewHolder> {
    public ConsultTimeAdapter(List<ConsultTimesBean> data) {
        super(R.layout.item_is_reservation_new, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultTimesBean item) {


        helper.setText(R.id.can, item.getMember_num() + "人");

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sDateFormat.parse(item.getStart_time());
            Date enddate = sDateFormat.parse(item.getEnd_time());

            String hs = "";
            String ms = "";
            String endhs = "";
            String endms = "";


            if (date.getHours() < 10) {
                hs = "0" + date.getHours();
            } else {
                hs = date.getHours() + "";
            }
            if (date.getMinutes() < 10) {
                ms = "0" + date.getMinutes();
            } else {
                ms = date.getMinutes() + "";
            }

            if (enddate.getHours() < 10) {
                endhs = "0" + enddate.getHours();
            } else {
                endhs = enddate.getHours() + "";
            }
            if (enddate.getMinutes() < 10) {
                endms = "0" + enddate.getMinutes();
            } else {
                endms = enddate.getMinutes() + "";
            }




            helper.setText(R.id.time, hs + ":" + ms+"~"+endhs+":"+endms);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
