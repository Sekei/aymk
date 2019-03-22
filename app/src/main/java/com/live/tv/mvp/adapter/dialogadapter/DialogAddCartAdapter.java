package com.live.tv.mvp.adapter.dialogadapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.SpecificationBeans;

import java.util.List;

/**
 * Created by taoh on 2018/5/8.
 */

public class DialogAddCartAdapter extends RecyclerArrayAdapter<SpecificationBeans> {


    public String select_id = "";

    private SelectListener selectListener;

    public interface SelectListener {
        void OnSelectListener(String id, int position);
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public DialogAddCartAdapter(Context context, List<SpecificationBeans> ShopClassBean) {
        super(context, ShopClassBean);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<SpecificationBeans> {


        private FlexboxLayout mFlexboxLayout_hot;
        private TextView FlexboxLayout_t;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.dialog_good_cart_item);
            mFlexboxLayout_hot = $(R.id.mFlexboxLayout_hot);
            FlexboxLayout_t = $(R.id.FlexboxLayout_t);

        }

        @Override
        public void setData(SpecificationBeans data) {
            //name.setText(data.getClass_name());
            FlexboxLayout_t.setText(data.getSpecification_value());
            addHotView(data.getSpecificationBeans(), mFlexboxLayout_hot, getDataPosition());
        }
    }

    public void addHotView(List<SpecificationBeans> data,
                           FlexboxLayout mFlexboxLayout_hot, final int position) {
        if (mFlexboxLayout_hot == null) return;
        mFlexboxLayout_hot.removeAllViews();
        for (final SpecificationBeans bean : data) {
            final TextView word = new TextView(getContext());
            word.setTextColor(getContext().getResources().getColor(R.color.tx_l));
            word.setText(bean.getSpecification_value());
            word.setMinWidth(getContext().getResources().getDimensionPixelOffset(R.dimen.dp_40));
            word.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.y26));
            word.setBackgroundResource(R.drawable.shape_param_hui);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(12, 5, 12, 5);
            word.setLayoutParams(lp);
            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    word.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                    word.setBackgroundResource(R.drawable.setbar_gg2);
                    selectListener.OnSelectListener(bean.getSpecification_id(), position);
                }
            });
            if(select_id.contains(bean.getSpecification_id())){
                word.setTextColor(getContext().getResources().getColor(R.color.pure_white));
                word.setBackgroundResource(R.drawable.setbar_gg2);
                //selectListener.OnSelectListener(bean.getSpecification_id(), position);
            }

            mFlexboxLayout_hot.addView(word);
        }
    }
}
