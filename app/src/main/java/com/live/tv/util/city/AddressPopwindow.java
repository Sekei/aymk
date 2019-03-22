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

import java.util.ArrayList;
import java.util.List;

import static com.ysjk.health.iemk.R.id.levelone;


/**
 * Created by mac1010 on 2017/11/9.
 */

public class AddressPopwindow {


    private Context context;

    private PopupWindow pop;

    private List<ProvinceBean> dataOne;
    private List<CityBean> dataTwo;
    private List<Integer> pois;

    private TwoAddressAdapter adaptertwo;
    private OneAddressAdapter adapterOne;

    private LinearLayoutManager mLinearLayoutManager;

    public AddressPopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        pois = new ArrayList<>();
        dataOne = new ArrayList<>();
        dataTwo = new ArrayList<>();

        pop.setOutsideTouchable(true);
    }

    public AddressPopwindow setData(ArrayList<ProvinceBean> data) {
        this.dataOne = data;
        return this;
    }

    public AddressPopwindow setOutsideTouchable(boolean b) {
        //pop.setOutsideTouchable(b);
        //pop.setFocusable(b);
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public AddressPopwindow cerate() {

        View view = View.inflate(context, R.layout.popwindow, null);

//        View view = LayoutInflater.from(context).inflate(R.layout.pop1,null);
        final RecyclerView recyclerViewOne = (RecyclerView) view.findViewById(levelone);
        final RecyclerView leveltwo = (RecyclerView) view.findViewById(R.id.leveltwo);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);

        adapterOne = new OneAddressAdapter(context, dataOne);
        adaptertwo = new TwoAddressAdapter(context, dataTwo);

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
                adaptertwo.addAll(adapterOne.getItem(position).getCityList());
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
        adaptertwo.addAll(adapterOne.getItem(0).getCityList());
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

    public AddressPopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public AddressPopwindow setOnItemClickListener(AddressPopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(CityBean data);
    }
}
