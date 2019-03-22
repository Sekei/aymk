package com.live.tv.mvp.activity.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GiftBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/4/11
 */

public class HomeClaseeGridViewAdapter extends BaseAdapter {
    private List<GiftBean> mDatas;
    private LayoutInflater mLayoutInflater;
    /**
     * 页数下标,从0开始(通俗讲第几页)
     */
    private int mIndex;
    /**
     * 每页显示最大条目个数 ,默认是dimes.xml里 HomePageHeaderColumn 属性值的两倍(每页多少个图标)
     */
    private int mPageSize;

    public HomeClaseeGridViewAdapter(Context context, List<GiftBean> mDatas, int mIndex) {
        this.mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(context);
        this.mIndex = mIndex;
        mPageSize = 10;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (mIndex+1)*mPageSize,
     * 如果够，则直接返回每一页显示的最大条目个数mPageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - mIndex * mPageSize);(说白了 最后一页就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > (mIndex + 1) * mPageSize ? mPageSize : (mDatas.size() - mIndex * mPageSize);

    }

    @Override
    public GiftBean getItem(int position) {
        return mDatas.get(position + mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
//        Log.i("TAG", "position:" + position + "   :" + this);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_gift_gridview, parent, false);
            vh = new ViewHolder();
            vh.xuanzhong = (ImageView) convertView.findViewById(R.id.xuanzhong);
            vh.is_running = (ImageView) convertView.findViewById(R.id.is_running);
            vh.img = (ImageView) convertView.findViewById(R.id.img);
            vh.liwu_name = (TextView) convertView.findViewById(R.id.liwu_name);
            vh.rl_parentview= (RelativeLayout)convertView.findViewById(R.id.rl_parentview);
            vh.count_price= (TextView)convertView.findViewById(R.id.count_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        /**
         * 在给View绑定显示的数据时，计算正确的position = position + mIndex * mPageSize，
         */
        final int pos = position + mIndex * mPageSize;
//        if (mDatas.get(pos).getIs_running().equals("1")){
//            vh.is_running.setVisibility(View.GONE);
//        }else if (mDatas.get(pos).getIs_running().equals("2")){
//            vh.is_running.setVisibility(View.VISIBLE);
//        }


        if (mDatas.get(pos).ischecked()){
            vh.xuanzhong.setVisibility(View.VISIBLE);
            vh.is_running.setVisibility(View.GONE);
        }else{
            vh.xuanzhong.setVisibility(View.GONE);
        }
        vh.liwu_name.setText(mDatas.get(pos).getGift_name());
        vh.count_price.setText("¥"+mDatas.get(pos).getGift_price());
        vh.rl_parentview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<mDatas.size();i++){
                    GiftBean model = mDatas.get(i);
                    if (i==pos){
                        if (model.ischecked()) {
                            model.setIschecked(false);
                        } else {
                            model.setIschecked(true);
                        }
                    }else{
                        model.setIschecked(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
        Glide.with(parent.getContext()).load(Constants.BASE_URL+mDatas.get(pos).getGift_img()).diskCacheStrategy(DiskCacheStrategy.ALL).into(vh.img);
        return convertView;
    }


    class ViewHolder {
        public ImageView xuanzhong;
        public ImageView img;
        public ImageView is_running;
        public RelativeLayout rl_parentview;
        public TextView count_price;
        public TextView liwu_name;
    }
}
