package com.live.tv.mvp.presenter.home;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.home.IComplaintView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mac1010 on 2018/8/28.
 */

public class ComplaintPresenter extends BasePresenter<IComplaintView> {
    public ComplaintPresenter(App app) {
        super(app);
    }

    /**
     *
     * @param parmer
     */
    public void getinsertComplains(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getinsertComplains(parmer)
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
                                getView().oninsertComplains(userBeanHttpResult.getData());
                        }
                    }
                });
    }
}
