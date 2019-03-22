package com.live.tv.mvp.adapter.shop;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ShopCarBean;
import com.live.tv.mvp.fragment.shop.CartFragment;

import java.util.List;


public class CartGoodAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {
    private CartFragment cartFragment;
    private int shop_position;
    int count_good=0;
    TextView tv_good_num;
    private UpdateCartListener  mUpdateCartListener;

    public UpdateCartListener getmUpdateCartListener() {
        return mUpdateCartListener;
    }

    public void setmUpdateCartListener(UpdateCartListener mUpdateCartListener) {
        this.mUpdateCartListener = mUpdateCartListener;
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }

    public void setCartFragment(CartFragment cartFragment) {
        this.cartFragment = cartFragment;
    }


    public interface  UpdateCartListener{

        void  updateCartNum(String goods_id,String num);
    }

    public CartGoodAdapter(List<ShopCarBean> data, CartFragment cartFra, int index) {
        super(R.layout.item_edit_cart_goods, data);
        cartFragment=cartFra;
        shop_position=index;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCarBean item) {

        Glide.with(mContext).load(Constants.BASE_URL+item.getGoods_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.img_merchants));
        tv_good_num = helper.getView(R.id.tv_cancel);

        helper.setText(R.id.tv_good_name,item.getGoods_name());
        helper.setText(R.id.tv_nums,"规格："+item.getSpecification_names());
        helper.setText(R.id.tv_price,"¥"+item.getSpecification_price());
        tv_good_num.setText(item.getGoods_num());


        helper.addOnClickListener(R.id.cb_merchants);
        helper.addOnClickListener(R.id.tv_jia);
        helper.addOnClickListener(R.id.tv_jian);


        final CheckBox cb_Selecte=helper.getView(R.id.cb_merchants);
        cb_Selecte.setChecked(item.isSelected());
        cb_Selecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.setSelected(cb_Selecte.isChecked());

                cartFragment.updatePrice(shop_position);

            }
        });


        TextView img_jia=helper.getView(R.id.tv_jia);
        TextView img_jian=helper.getView(R.id.tv_jian);

        img_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_good=Integer.parseInt(item.getGoods_num());
                count_good++;
                item.setGoods_num(String.valueOf(count_good));
                Log.i("dfc", "onClick: "+count_good);
                tv_good_num.setText(String.valueOf(count_good));

               mUpdateCartListener.updateCartNum(item.getCar_id(),count_good+"");
                notifyDataSetChanged();
                cartFragment.updatePrice(shop_position);

            }
        });

        img_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                count_good=Integer.parseInt(item.getGoods_num());

                if (count_good==1){

                    Toast.makeText(mContext,"数量不能为0",Toast.LENGTH_SHORT).show();

                }else {
                    count_good--;
                    item.setGoods_num(String.valueOf(count_good));
                    Log.i("dfc", "onClick: "+count_good);
                    tv_good_num.setText(String.valueOf(count_good));
                    mUpdateCartListener.updateCartNum(item.getCar_id(),count_good+"");
                    notifyDataSetChanged();
                    cartFragment.updatePrice(shop_position);
                }


            }
        });
    }





}
