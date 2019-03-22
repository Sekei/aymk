package com.live.tv.util.city;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.ysjk.health.iemk.R;

import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by mac1010 on 2017/11/9.
 */

public class FilterPopwindow {

    private Context context;
    private PopupWindow pop;
    private List<OfficeBean> list;
    private FilterAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    public FilterPopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        list = new ArrayList<>();
        pop.setOutsideTouchable(true);
    }

    public FilterPopwindow setData(ArrayList<OfficeBean> data) {
        this.list = data;
        return this;
    }

    public FilterPopwindow setOutsideTouchable(boolean b) {
        /*pop.setOutsideTouchable(b);
        pop.setFocusable(b);*/
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public FilterPopwindow cerate() {
        View view = View.inflate(context, R.layout.popwindow_three, null);
        final RecyclerView    recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new FilterAdapter(context,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);

        return this;
    }

    public void show(View view) {
        pop.showAsDropDown(view);
    }

    public FilterPopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public FilterPopwindow setOnItemClickListener(FilterPopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(HospitalLevelBean data);
    }
}
