package com.live.tv.mvp.adapter.shop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CartBean;
import com.live.tv.bean.ShopCarBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.ContentActivity;
import com.live.tv.mvp.fragment.shop.CartFragment;
import com.live.tv.util.SpSingleInstance;

import java.util.List;



public class CartAdapter extends BaseQuickAdapter<CartBean, BaseViewHolder> {
    private CartFragment cartFragment;
    private UserBean userBean;

    private CartUpdateCartListener mCartUpdateCartListener;
    RecyclerView mRl_class;


    public CartUpdateCartListener getmCartUpdateCartListener() {
        return mCartUpdateCartListener;
    }

    public void setmCartUpdateCartListener(CartUpdateCartListener mCartUpdateCartListener) {
        this.mCartUpdateCartListener = mCartUpdateCartListener;
    }

    public interface CartUpdateCartListener {

        void updateCartNum(String goods_id, String num);
    }

    public CartAdapter(List<CartBean> data, CartFragment cartFra) {
        super(R.layout.item_cart_merchants, data);
        cartFragment = cartFra;


        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

    }


    @Override
    protected void convert(final BaseViewHolder helper, final CartBean item) {

        Glide.with(mContext).load(Constants.BASE_URL + item.getMerchantsBean().getMerchants_img())
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.img_merchants));
        helper.setText(R.id.merchants_name, item.getMerchantsBean().getMerchants_name());


       mRl_class = helper.getView(R.id.recyclerView);
        mRl_class.setLayoutManager(new LinearLayoutManager(mContext));
        final CartGoodAdapter cartGoodAdapter = new CartGoodAdapter(item.getShopCarBeans(), cartFragment, helper.getLayoutPosition());

        mRl_class.setAdapter(cartGoodAdapter);



        cartGoodAdapter.setmUpdateCartListener(new CartGoodAdapter.UpdateCartListener() {
            @Override
            public void updateCartNum(String goods_id, String num) {
                mCartUpdateCartListener.updateCartNum(goods_id, num);

            }
        });


        TextView merchants_name = helper.getView(R.id.merchants_name);

        merchants_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cartFragment.getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, Constants.Merchants_Detail_Fragment);
                intent.putExtra("merchants_id", item.getMerchants_id());
                cartFragment.startActivity(intent);

            }
        });


        cartGoodAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int p) {
                               ShopCarBean shopCarBean = (ShopCarBean) adapter.getData().get(p);

                Intent intent = new Intent(cartFragment.getActivity(), ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT,Constants.GOOD_DETAIL_FRAGMENT);
                intent.putExtra("goods_id",shopCarBean.getGoods_id());
                cartFragment.startActivity(intent);
            }
        });

        final CheckBox cb_Selecte_shop = helper.getView(R.id.cb_merchants);
        cb_Selecte_shop.setChecked(item.isSelected());
        cb_Selecte_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (ShopCarBean shopCarBean : item.getShopCarBeans()) {

                    shopCarBean.setSelected(cb_Selecte_shop.isChecked());
                    cartGoodAdapter.notifyDataSetChanged();
                    cartFragment.updatePrice(helper.getLayoutPosition());
                }

            }
        });


    }
}
