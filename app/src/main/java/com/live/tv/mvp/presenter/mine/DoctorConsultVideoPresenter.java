package com.live.tv.mvp.presenter.mine;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.FetureDateBeans;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.mine.IDoctorConsultVideoView;

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

public class DoctorConsultVideoPresenter extends BasePresenter<IDoctorConsultVideoView> {

    public DoctorConsultVideoPresenter(App app) {
        super(app);
    }


    //获取七天时间
    public void getFetureDay() {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getFetureDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<FetureDateBeans>>>() {


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
                    public void onNext(HttpResult<List<FetureDateBeans>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onGetFetureDay(userBeanHttpResult.getData());
                        }
                    }
                });
    }


    //获取咨询列表
    public void getDoctorConsultList(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getDoctorConsultList(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ConsultBean>>>() {


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
                    public void onNext(HttpResult<List<ConsultBean>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().ongetDoctorConsultList(userBeanHttpResult.getData());
                        }
                    }
                });
    }

    //获取咨询列表
    public void getMoreDoctorConsultList(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getDoctorConsultList(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ConsultBean>>>() {

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
                    public void onNext(HttpResult<List<ConsultBean>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().ongetMoreDoctorConsultList(userBeanHttpResult.getData());
                        }
                    }
                });
    }

}
