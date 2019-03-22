package com.live.tv.mvp.fragment.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ShaixuanBean;

import java.util.List;

/**
 * Created by mac1010 on 2017/11/9.
 */

public class ShaixuanPopwindow {

    private Context context;
    private PopupWindow pop;
    private ShaixuanBean  shaixuanBean;

    public ShaixuanPopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        pop.setOutsideTouchable(true);
    }

    public ShaixuanPopwindow setData(ShaixuanBean  data) {
        shaixuanBean=data;
        return this;
    }

    public ShaixuanPopwindow setOutsideTouchable(boolean b) {
        /*pop.setOutsideTouchable(b);
        pop.setFocusable(b);*/
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }
    FlexboxLayout  flexboxLayout_doctor;
    FlexboxLayout  flexboxLayout_hospital;
    TextView  clear;
    TextView  ok;
    public ShaixuanPopwindow cerate() {
        View view = View.inflate(context, R.layout.popwindow_shaixuan, null);
         flexboxLayout_doctor = (FlexboxLayout) view.findViewById(R.id.mFlexboxLayout_doctor);
        flexboxLayout_hospital = (FlexboxLayout) view.findViewById(R.id.mFlexboxLayout_hospital);
        clear = (TextView) view.findViewById(R.id.clear);
        ok = (TextView) view.findViewById(R.id.ok);
        bindData();
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);

        return this;
    }

    private void bindData() {
        if (shaixuanBean == null) return;
        addHotView(shaixuanBean.getDoctorJobBeans(),flexboxLayout_doctor);
        addHotView2(shaixuanBean.getHospitalBeans(),flexboxLayout_hospital);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String doc_str="";
                String hos_str="";

                for (ShaixuanBean.DoctorJobBeansBean docs:shaixuanBean.getDoctorJobBeans()){

                    if (docs.isCheck()){

                        doc_str+=docs.getJob_level()+",";
                    }

                }

                for (ShaixuanBean.HospitalBeansBean hoss:shaixuanBean.getHospitalBeans()){

                    if (hoss.isCheck()){

                        hos_str+=hoss.getHospital_level()+",";
                    }

                }

                if (!doc_str.equals("")){

                    doc_str=doc_str.substring(0,doc_str.length()-1);
                }

                if (!hos_str.equals("")){

                    hos_str=hos_str.substring(0,hos_str.length()-1);
                }
                onItemClickListener.onShaixuanStr(doc_str,hos_str);
                Log.i("dfc", "onClick: "+hos_str  +"========"+doc_str);

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chongzhi();
                bindData();
            }
        });

    }

    public void show(View view) {
        if (pop != null) {
            pop.showAsDropDown(view);
        }
    }

    public ShaixuanPopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public ShaixuanPopwindow setOnItemClickListener(ShaixuanPopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(ShaixuanBean data);
        void onShaixuanStr(String doc_str,String hos_str);
    }


    public void addHotView(List<ShaixuanBean.DoctorJobBeansBean> data,
                           FlexboxLayout mFlexboxLayout_hot) {
        if (mFlexboxLayout_hot == null) return;
        mFlexboxLayout_hot.removeAllViews();
        for (final ShaixuanBean.DoctorJobBeansBean bean : data) {

            final TextView word = new TextView(context);
            word.setTextColor(context.getResources().getColor(R.color.colorTextG9));
            word.setText(bean.getJob_level());
            word.setGravity(FlexboxLayout.LayoutParams.ALIGN_SELF_CENTER);
            word.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.y26));
            word.setBackgroundResource(R.drawable.setbar_gray);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(12, 5, 12, 5);
            word.setLayoutParams(lp);
            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.isCheck()){
                        bean.setCheck(false);
                        word.setBackgroundResource(R.drawable.setbar_gray);
                        word.setTextColor(context.getResources().getColor(R.color.colorTextG9));



                    }else {
                        bean.setCheck(true);
                        word.setTextColor(context.getResources().getColor(R.color.wheat));
                        word.setBackgroundResource(R.drawable.setbar_green);

                    }
                }
            });
            mFlexboxLayout_hot.addView(word);
        }
    }



    public void addHotView2(List<ShaixuanBean.HospitalBeansBean> data,
                           FlexboxLayout mFlexboxLayout_hot) {
        if (mFlexboxLayout_hot == null) return;
        mFlexboxLayout_hot.removeAllViews();
        for (final ShaixuanBean.HospitalBeansBean bean : data) {

            final TextView word = new TextView(context);
            word.setTextColor(context.getResources().getColor(R.color.colorTextG9));
            word.setText(bean.getHospital_level());
            word.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.y26));
            word.setBackgroundResource(R.drawable.setbar_gray);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(12, 5, 12, 5);
            word.setLayoutParams(lp);
            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (bean.isCheck()){
                        bean.setCheck(false);
                        word.setBackgroundResource(R.drawable.setbar_gray);
                        word.setTextColor(context.getResources().getColor(R.color.colorTextG9));



                    }else {
                        bean.setCheck(true);
                        word.setTextColor(context.getResources().getColor(R.color.wheat));
                        word.setBackgroundResource(R.drawable.setbar_green);

                    }


                    // selectListener.OnSelectListener(bean.getSpecification_id(), position);
                }
            });

            mFlexboxLayout_hot.addView(word);
        }

    }

    private void chongzhi() {
        for (ShaixuanBean.DoctorJobBeansBean docs:shaixuanBean.getDoctorJobBeans()){

         docs.setCheck(false);

        }

        for (ShaixuanBean.HospitalBeansBean hoss:shaixuanBean.getHospitalBeans()){

           hoss.setCheck(false);

        }

    }


}
