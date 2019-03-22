package com.live.tv.mvp.presenter.mine;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.mine.ITimeSettingView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class TimeSettingPresenter extends BasePresenter<ITimeSettingView>{

    public TimeSettingPresenter(App app) {
        super(app);
    }


    //
    public void setConsultTime(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getConsultTimes(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ConsultTimesBean>>>() {


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
                    public void onNext(HttpResult<List<ConsultTimesBean>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onConsultTimeList(userBeanHttpResult.getData());
                        }
                    }
                });
    }
 public void setOpenTime(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .setOpenTime(parmer)
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
                                getView().onsetOpenTime(userBeanHttpResult.getData());
                        }
                    }
                });
    }

}
