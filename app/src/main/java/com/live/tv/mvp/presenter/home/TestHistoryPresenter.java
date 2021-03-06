package com.live.tv.mvp.presenter.home;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.ReportHistoryBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.home.ITestHistoryView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/23
 */

public class TestHistoryPresenter extends BasePresenter<ITestHistoryView>{
    public TestHistoryPresenter(App app) {
        super(app);
    }

    //（添加档案）关系列表
    public void getReportList(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getReportList(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ReportHistoryBean>>>() {


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
                    public void onNext(HttpResult<List<ReportHistoryBean>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onGetReportList(userBeanHttpResult.getData());
                        }
                    }
                });
    }
}
