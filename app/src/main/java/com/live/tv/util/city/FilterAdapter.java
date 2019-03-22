package com.live.tv.util.city;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;

import java.util.List;

/**
 * Created by mac1010 on 2017/10/9.
 */

public class FilterAdapter extends RecyclerArrayAdapter<OfficeBean> {

    public String select_id = "";

    private SelectListener selectListener;

    public interface SelectListener {
        void OnSelectListener(String id, int position);
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public FilterAdapter(Context context, List<OfficeBean> officeBean) {
        super(context, officeBean);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<OfficeBean> {


        private FlexboxLayout mFlexboxLayout_hot;
        private TextView FlexboxLayout_t;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_filter);
            mFlexboxLayout_hot = $(R.id.mFlexboxLayout_hot);
            FlexboxLayout_t = $(R.id.FlexboxLayout_t);

        }

        @Override
        public void setData(OfficeBean data) {
            FlexboxLayout_t.setText(data.getOfficeName());
            addHotView(data.getList(), mFlexboxLayout_hot, getDataPosition());
        }


    }

    public void addHotView(List<HospitalLevelBean> data,
                           FlexboxLayout mFlexboxLayout_hot, final int position) {
        if (mFlexboxLayout_hot == null) return;
        mFlexboxLayout_hot.removeAllViews();
        for (final HospitalLevelBean bean : data) {
            getContext();
            final TextView word = new TextView(getContext());
            word.setTextColor(getContext().getResources().getColor(R.color.colorTextG9));
            word.setText(bean.getHospitalLevel());
            word.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.y26));
            word.setBackgroundResource(R.drawable.setbar_gray);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(12, 5, 12, 5);
            word.setLayoutParams(lp);
            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    word.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    word.setBackgroundResource(R.drawable.setbar_seach);
                   // selectListener.OnSelectListener(bean.getSpecification_id(), position);
                }
            });
       /*     if (select_id.contains(bean.getSpecification_id())) {
                word.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                word.setBackgroundResource(R.drawable.setbar_gg2);
                //selectListener.OnSelectListener(bean.getSpecification_id(), position);
            }*/

            mFlexboxLayout_hot.addView(word);
        }
    }

}