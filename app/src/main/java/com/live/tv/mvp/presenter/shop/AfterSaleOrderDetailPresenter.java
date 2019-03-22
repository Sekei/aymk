package com.live.tv.mvp.presenter.shop;


import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.shop.IAfterSaleOrderDetailView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by taoh on 2017/7/20.
 */

public class AfterSaleOrderDetailPresenter extends BasePresenter<IAfterSaleOrderDetailView> {
    public AfterSaleOrderDetailPresenter(App app) {
        super(app);
    }

//商品订单列表

    public void getAfterSaleOrderDetail(Map<String, String> parme) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getRefundOrderDetail(parme)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<AfterSaleOrderBean>>() {


                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<AfterSaleOrderBean> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onAfterSaleOrderDetail(userBeanHttpResult.getData());
                        }
                    }
                });
    }





 public void getCancelRefundOrder(Map<String, String> parme) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .cancelRefundOrder(parme)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {


                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onCancelRefundOrder(userBeanHttpResult.getData());
                        }
                    }
                });
    }






}
