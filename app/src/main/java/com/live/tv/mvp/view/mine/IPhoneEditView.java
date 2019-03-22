package com.live.tv.mvp.view.mine;

import com.live.tv.mvp.base.BaseView;
import com.live.tv.mvp.fragment.home.ConsultWeek;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public interface IPhoneEditView extends BaseView {
    void onConsultPrice(String data);
    void onConsultTimePage(List<ConsultWeek> data);
}
