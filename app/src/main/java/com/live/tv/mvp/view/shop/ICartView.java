package com.live.tv.mvp.view.shop;


import com.live.tv.bean.CartBean;
import com.live.tv.mvp.base.BaseView;
import java.util.List;


public interface ICartView extends BaseView {
    void CartList(List<CartBean> data);
    void DelCart(String data);
    void UpdateCart(String data);



}
