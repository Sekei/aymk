package com.live.tv.mvp.adapter.mine;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FetureDateBeans;

import java.util.List;


/**
 *七天时间
 */

public class SevendaysAdapter extends BaseQuickAdapter<FetureDateBeans, BaseViewHolder> {
    int position=0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SevendaysAdapter(List<FetureDateBeans> data) {
        super(R.layout.item_week, data);
    }




    @Override
    protected void convert(BaseViewHolder helper, FetureDateBeans item) {


            String week_new="";
            String day_new="";

        String week= item.getWeekday();
        String day=item.getConsult_set_day();



        day_new=day.substring(day.length()-2,day.length());
//
//        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date=sDateFormat.parse(day);
//            day_new=date.getDay()+"";
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }



        if (helper.getLayoutPosition()==0){
            switch (week) {
                case "星期日":
                    week_new="日\n今";
                    break;
                case "星期一":
                    week_new="一\n今";
                    break;
                case "星期二":
                    week_new="二\n今";
                    break;
                case "星期三":
                    week_new="三\n今";
                    break;
                case "星期四":
                    week_new="四\n今";
                    break;
                case "星期五":
                    week_new="五\n今";
                    break;
                case "星期六":
                    week_new="六\n今";
                    break;
            }


        }else {


            switch (week) {
                case "星期日":
                    week_new="日\n"+day_new;
                    break;
                case "星期一":
                    week_new="一\n"+day_new;
                    break;
                case "星期二":
                    week_new="二\n"+day_new;
                    break;
                case "星期三":
                    week_new="三\n"+day_new;
                    break;
                case "星期四":
                    week_new="四\n"+day_new;
                    break;
                case "星期五":
                    week_new="五\n"+day_new;
                    break;
                case "星期六":
                    week_new="六\n"+day_new;
                    break;
            }
        }

        TextView one=helper.getView(R.id.one);


        one.setText(week_new);

        if (position==helper.getLayoutPosition()){
            one.setTextColor(mContext.getResources().getColor(R.color.pure_white));
            one.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else {
            one.setTextColor(mContext.getResources().getColor(R.color.colorTextG3));
            one.setBackgroundColor(mContext.getResources().getColor(R.color.pure_white));
        }
    }
}
