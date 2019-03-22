package com.live.tv.mvp.fragment.shop.popwindow;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ysjk.health.iemk.R;

/**
 * Created by mac1010 on 2017/11/9.
 */

public class LiveStatePopwindow {


    private Context context;

    private PopupWindow pop;


    public LiveStatePopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        pop.setOutsideTouchable(true);
    }


    public LiveStatePopwindow setOutsideTouchable(boolean b) {
        pop.setOutsideTouchable(b);
        pop.setFocusable(b);
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public LiveStatePopwindow cerate() {

        View view = View.inflate(context, R.layout.live_state_popwindow, null);

        final TextView tv_1 = (TextView) view.findViewById(R.id.tv_1);
        final TextView tv_2 = (TextView) view.findViewById(R.id.tv_2);
        final TextView tv_3 = (TextView) view.findViewById(R.id.tv_3);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);
        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onclick("1", "全部");
                dismiss();
            }
        });
        tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onclick("2", "仅看直播");
                dismiss();

            }
        });
        tv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onclick("3", "仅看回放");
                dismiss();

            }
        });


        return this;
    }

    public void show(View view) {
        if (Build.VERSION.SDK_INT > 24) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            int height = view.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            pop.setHeight(height);
            pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        } else {
            pop.showAsDropDown(view);
        }
    }

    public LiveStatePopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public LiveStatePopwindow setOnItemClickListener(LiveStatePopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(String type, String name);
    }
}
