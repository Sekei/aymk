package com.live.tv.util.city;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ysjk.health.iemk.R;
import com.live.tv.bean.City;

import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;


public class CityAdapter extends BaseTurboAdapter<City, BaseViewHolder> {


    public interface onSearchCityLinstener {
        void onClick(String name);
    }

    private onSearchCityLinstener onSearchCityLinstener;

    public void setSearchCityLinstener(CityAdapter.onSearchCityLinstener onSearchCityLinstener) {
        this.onSearchCityLinstener = onSearchCityLinstener;
    }

    public CityAdapter(Context context) {
        super(context);
    }

    public CityAdapter(Context context, List<City> data) {
        super(context, data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        City city = getItem(position);
        return city.type;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new CityHolder(inflateItemView(R.layout.item_city, parent));
        else
            return new PinnedHolder(inflateItemView(R.layout.item_pinned_header, parent));
    }


    @Override
    protected void convert(BaseViewHolder holder, final City item) {
        if (holder instanceof CityHolder) {
            ((CityHolder) holder).city_name.setText(item.name);
            ((CityHolder) holder).constraint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSearchCityLinstener.onClick(item.name);
                }
            });

        } else {
            String letter = item.pys.substring(0, 1);
            ((PinnedHolder) holder).city_tip.setText(letter);
        }
    }

    public int getLetterPosition(String letter) {
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).type == 1 && getData().get(i).pys.equals(letter)) {
                return i;
            }
        }
        return -1;
    }

    class CityHolder extends BaseViewHolder {

        TextView city_name;
        ConstraintLayout constraint;

        public CityHolder(View view) {
            super(view);
            city_name = findViewById(R.id.city_name);
            constraint = findViewById(R.id.constraint);

        }
    }


    class PinnedHolder extends BaseViewHolder {

        TextView city_tip;

        public PinnedHolder(View view) {
            super(view);
            city_tip = findViewById(R.id.city_tip);
        }
    }
}
