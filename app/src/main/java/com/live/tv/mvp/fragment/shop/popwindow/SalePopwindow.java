package com.live.tv.mvp.fragment.shop.popwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ysjk.health.iemk.R;

/**
 * Created by mac1010 on 2017/11/9.
 */

public class SalePopwindow {


    private Context context;

    private PopupWindow pop;




    public SalePopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
    }


    public SalePopwindow setOutsideTouchable(boolean b) {
        pop.setOutsideTouchable(b);
        pop.setFocusable(b);
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public SalePopwindow cerate() {

        View view = View.inflate(context, R.layout.sale_popwindow, null);

        final TextView tv_1 = (TextView) view.findViewById(R.id.tv_1);
        final TextView tv_2 = (TextView) view.findViewById(R.id.tv_2);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);
        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onclick("desc");
                dismiss();
            }
        });
         tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onclick("asc");
                dismiss();

            }
        });




        return this;
    }

    public void show(View view) {
        if (pop != null) {
            pop.showAsDropDown(view);
        }
    }

    public SalePopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public SalePopwindow setOnItemClickListener(SalePopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(String type);
    }
}
