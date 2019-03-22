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
import com.live.tv.bean.TypeServiceBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mac1010 on 2017/11/9.
 */

public class HotorNewPopwindow {

    private Context context;

    private PopupWindow pop;

    private List<Integer> pois;
    private NewOrHotAdapter adapterOne;
    private LinearLayoutManager mLinearLayoutManager;
    private List<String> list=new ArrayList<>();

    public HotorNewPopwindow(Context context) {
        this.context = context;
        pop = new PopupWindow(context);
        pois = new ArrayList<>();
        pop.setOutsideTouchable(true);
    }

    public HotorNewPopwindow setOutsideTouchable(boolean b) {
        /*pop.setOutsideTouchable(b);
        pop.setFocusable(b);*/
        return this;
    }

    public boolean isShowing() {
        return pop.isShowing();
    }

    public HotorNewPopwindow cerate() {

        View view = View.inflate(context, R.layout.popwindow_type, null);

//        View view = LayoutInflater.from(context).inflate(R.layout.pop1,null);
        final RecyclerView recyclerViewOne = (RecyclerView) view.findViewById(R.id.levelone);


        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setContentView(view);
        adapterOne = new NewOrHotAdapter(context);
        list.clear();
        list.add("最新");
        list.add("最热");
        adapterOne.addAll(list);
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewOne.setAdapter(adapterOne);
        adapterOne.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                adapterOne.setCheckPosition(position);
                adapterOne.notifyDataSetChanged();
                if (onItemClickListener!=null){
                    onItemClickListener.onclick(list.get(position));
                }

            }
        });

        adapterOne.setCheckPosition(0);
        adapterOne.notifyDataSetChanged();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });

        return this;
    }

    public void show(View view) {

        pop.showAsDropDown(view);
    }

    public HotorNewPopwindow dismiss() {
        pop.dismiss();
        return this;
    }

    public HotorNewPopwindow setOnItemClickListener(HotorNewPopwindow.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private HotorNewPopwindow.onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onclick(String data);
    }
}
