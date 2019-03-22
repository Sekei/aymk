package com.live.tv.util.city;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DepartmentLevelTwoBean;

import java.util.ArrayList;
import java.util.List;

import static com.ysjk.health.iemk.R.id.levelone;


/**
 * Created by mac1010 on 2017/11/9.
 */

public class DepartmentPopwindow {


    private Context context;

    private PopupWindow pop;

    private List<DepartmentLevelOneBean> dataOne;
    private List<DepartmentLevelTwoBean> dataTwo;
    private List<Integer> pois;

    private OneDepartmentAdapter adapterOne;
    private TwoDepartmentAdapter adaptertwo;

    private LinearLayoutManager mLinearLayoutManager;

    public DepartmentPopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        pois = new ArrayList<>();
        dataOne = new ArrayList<>();
        dataTwo = new ArrayList<>();
        pop.setOutsideTouchable(true);
    }

    public DepartmentPopwindow setData(ArrayList<DepartmentLevelOneBean> data) {
        this.dataOne = data;
//        int poi = 0;
//        pois.add(poi);
//        for (CorporTypeListBean bean:dataone){
//            poi = poi + bean.getTypes().size();
//            pois.add(poi);
//            datatow.addAll(bean.getTypes());
//        }

        return this;
    }

    public DepartmentPopwindow setOutsideTouchable(boolean b) {
        //pop.setOutsideTouchable(b);
        //pop.setFocusable(b);
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public DepartmentPopwindow cerate() {

        View view = View.inflate(context, R.layout.popwindow_two, null);
//        View view = LayoutInflater.from(context).inflate(R.layout.pop1,null);
        final RecyclerView recyclerViewOne = (RecyclerView) view.findViewById(levelone);
        final RecyclerView leveltwo = (RecyclerView) view.findViewById(R.id.leveltwo);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);
        adapterOne = new OneDepartmentAdapter(context, dataOne);
        adaptertwo = new TwoDepartmentAdapter(context, dataTwo);

        recyclerViewOne.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewOne.setAdapter(adapterOne);
        adapterOne.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                leveltwo.scrollToPosition(pois.get(position));
//                mLinearLayoutManager.scrollToPositionWithOffset(pois.get(position), 0);
//                mLinearLayoutManager.setStackFromEnd(true);
//                adapterone.setCheckPosition(position);
                adapterOne.setCheckPosition(position);
                adapterOne.notifyDataSetChanged();
                adaptertwo.clear();
                adaptertwo.addAll(adapterOne.getItem(position).getDepartmentBeans());
                adaptertwo.notifyDataSetChanged();
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        leveltwo.setLayoutManager(mLinearLayoutManager);
        leveltwo.setAdapter(adaptertwo);
        adaptertwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(adaptertwo.getItem(position));
                }
            }
        });

        adapterOne.setCheckPosition(0);
        adapterOne.notifyDataSetChanged();
        adaptertwo.clear();
        adaptertwo.addAll(adapterOne.getItem(0).getDepartmentBeans());
        adaptertwo.notifyDataSetChanged();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });

//        leveltwo.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                for (int i=0;i<pois.size();i++){
//                    if(mLinearLayoutManager.findFirstVisibleItemPosition()>= pois.get(i)){
//                        adapterone.setCheckPosition(i);
//                        levelone.scrollToPosition(i);
//                    }
//                }
//            }
//        });
        return this;
    }

    public void show(View view) {
//        int popupHeight = view.getMeasuredHeight();

    /*    popAdapter.addAll(popList);
        popAdapter.notifyDataSetChanged();*/
        //获取需要在其下方显示的控件的位置信息
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
        //在控件上方显示
//        pw.showAtLocation(view, Gravity.NO_GRAVITY, (location[0]), location[1] + popupHeight);
        pop.showAsDropDown(view);
    }

    public DepartmentPopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public DepartmentPopwindow setOnItemClickListener(DepartmentPopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(DepartmentLevelTwoBean data);
    }
}
