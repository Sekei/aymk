package com.live.tv.mvp.presenter.shop;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.AddressBean;
import com.live.tv.bean.OrderBean;
import com.live.tv.bean.PriceBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.shop.IConfirmGoodOrderView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class ConfirmGoodOrderPresenter extends BasePresenter<IConfirmGoodOrderView>{
    public ConfirmGoodOrderPresenter(App app) {
        super(app);
    }

    public void getDefaultAddress(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .DefaultAddress(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<AddressBean>>() {


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
                    public void onNext(HttpResult<AddressBean> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onDefaultAddress(userBeanHttpResult.getData());
                        }
                    }
                });
    }


    public void getConfirmOrder(Map<String, String> params) {
        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .InsertOrder(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<PriceBean>>() {

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
                    public void onNext(HttpResult<PriceBean> HttpResult) {
                        LogUtils.d("Response:" + HttpResult);
                        if (HttpResult != null) {
                            if (isViewAttached()) {
                                getView().ConfirmOrder(HttpResult.getData());

                            }
                        }
                    }
                });

    }

    public void getOrderPrice(Map<String, String> params) {
        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .getOrderPrice(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<PriceBean>>() {


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
                    public void onNext(HttpResult<PriceBean> HttpResult) {
                        LogUtils.d("Response:" + HttpResult);
                        if (HttpResult != null) {
                            if (isViewAttached()) {
                                getView().getOrderPrice(HttpResult.getData());

                            }
                        }
                    }
                });

    }

}
