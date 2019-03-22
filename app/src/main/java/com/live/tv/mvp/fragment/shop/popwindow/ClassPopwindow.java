package com.live.tv.mvp.fragment.shop.popwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ClassBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac1010 on 2017/11/9.
 */

public class ClassPopwindow {


    private Context context;

    private PopupWindow pop;

    private List<ClassBean> dataOne;
    private List<ClassBean> dataTwo;
    private List<Integer> pois;

    private ClassOnesAdapter mOneAdapter;
    private ClassTwoAdapter mTwoAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    public ClassPopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        pois = new ArrayList<>();
        dataOne = new ArrayList<>();
        dataTwo = new ArrayList<>();
    }

    public ClassPopwindow setData(List<ClassBean> data) {
        this.dataOne = data;
        dataTwo=data.get(0).getGoodsClassBeans();
        return this;
    }

    public ClassPopwindow setOutsideTouchable(boolean b) {
        pop.setOutsideTouchable(b);
        pop.setFocusable(b);
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public ClassPopwindow cerate() {

        View view = View.inflate(context, R.layout.class_popwindow, null);

//        View view = LayoutInflater.from(context).inflate(R.layout.pop1,null);
        final RecyclerView recyclerViewOne = (RecyclerView) view.findViewById(R.id.levelone);
        final RecyclerView leveltwo = (RecyclerView) view.findViewById(R.id.leveltwo);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);

        mOneAdapter = new ClassOnesAdapter(context, dataOne);
        mTwoAdapter = new ClassTwoAdapter(context, dataTwo);

        recyclerViewOne.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewOne.setAdapter(mOneAdapter);
        mOneAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                mOneAdapter.setCheckPosition(position);
                mOneAdapter.notifyDataSetChanged();
                mTwoAdapter.clear();
                mTwoAdapter.addAll(mOneAdapter.getItem(position).getGoodsClassBeans());
                mTwoAdapter.notifyDataSetChanged();
            }
        });



        leveltwo.setLayoutManager(new GridLayoutManager(context,2));
        leveltwo.setAdapter(mTwoAdapter);
        mTwoAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(mTwoAdapter.getItem(position));
                }
            }
        });

//        mTwoAdapter.setCheckPosition(0);
//        mTwoAdapter.notifyDataSetChanged();
//        mTwoAdapter.clear();
//        mTwoAdapter.addAll(mTwoAdapter.getItem(0).getCityList());
//        mTwoAdapter.notifyDataSetChanged();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });


        return this;
    }

    public void show(View view) {
        if(pop!=null){
            pop.showAsDropDown(view);

        }
    }

    public ClassPopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public ClassPopwindow setOnItemClickListener(ClassPopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(ClassBean data);
    }
}
