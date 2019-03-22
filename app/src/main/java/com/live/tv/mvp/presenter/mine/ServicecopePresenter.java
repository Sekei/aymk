package com.live.tv.mvp.presenter.mine;

import com.live.tv.App;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.mine.IServicecopeView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac1010 on 2018/8/28.
 * 服务范围
 */

public class ServicecopePresenter extends BasePresenter<IServicecopeView> {
    public ServicecopePresenter(App app) {
        super(app);
    }

    /***
     * 服务范围
     * @param map
     */
    public void getserviceCope(Map<String, String> map) {

        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .getserviceCope(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ServiceCopeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<ServiceCopeBean>> plateListBeanHttpResult) {
                        if (isViewAttached()) {
                          getView().OnServiceCope(plateListBeanHttpResult.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
