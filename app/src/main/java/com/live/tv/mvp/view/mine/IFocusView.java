package com.live.tv.mvp.view.mine;


import com.live.tv.bean.CollectionBean;
import com.live.tv.mvp.base.BaseView;

import java.util.List;

/**
 * Created by taoh on 2017/7/20.
 */

public interface IFocusView extends BaseView {


    void  onFocusList(List<CollectionBean> data);

    void  onCancelCollection(String data);//
}
