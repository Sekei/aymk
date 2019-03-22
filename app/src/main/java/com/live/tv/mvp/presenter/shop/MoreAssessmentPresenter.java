package com.live.tv.mvp.presenter.shop;

import com.live.tv.App;
import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.shop.IMoreAssessmentView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class MoreAssessmentPresenter extends BasePresenter<IMoreAssessmentView>{
    public MoreAssessmentPresenter(App app) {
        super(app);
    }





 public void getAssessmentGoodsList(Map<String, String> params) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .assessmentGoods(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<AssessmentBean>>>() {


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
                    public void onNext(HttpResult<List<AssessmentBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onAssessmentGoods(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

    public void getAssessmentList(Map<String, String> map) {

        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .getAssessmentList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ServiceCommentBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<ServiceCommentBean>> plateListBeanHttpResult) {
                        if (isViewAttached()) {
                            getView().onAssessment(plateListBeanHttpResult.getData());
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
