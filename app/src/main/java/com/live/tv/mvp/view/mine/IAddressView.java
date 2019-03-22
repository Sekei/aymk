package com.live.tv.mvp.view.mine;


import com.live.tv.bean.AddressBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;


public interface IAddressView extends BaseView {
    void AddressList(List<AddressBean> addressBeanList);
    void MoreAddressList(List<AddressBean> addressBeanList);
    void DelAddress(String data);
    void SetDefaultAddress(String data);





}
