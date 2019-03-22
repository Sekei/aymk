package com.live.tv.mvp.presenter.home;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.home.IHousekeepDetailView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/22
 *
 * 家政详情的presenter
 */

public class HouseKeepDetailPresenter extends BasePresenter<IHousekeepDetailView>{

    public HouseKeepDetailPresenter(App app) {
        super(app);
    }



    //家政详情
    public void getHousekeepDetail(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getHouseKeepsDetail(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<HousekeepBean>>() {


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
                    public void onNext(HttpResult<HousekeepBean> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onGetHousekeepDetail(userBeanHttpResult.getData());
                        }
                    }
                });
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
//
    /***
     * 评论
     * @param map
     */
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
                            getView().OnserviceComment(plateListBeanHttpResult.getData());
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

    /**
     *热门推荐
     * @param parmer
     */
    public void getHouseKeeps(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getHouseKeeps(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<HousekeepBean>>>() {
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
                    public void onNext(HttpResult<List<HousekeepBean>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onHouseKeeps(userBeanHttpResult.getData());
                        }
                    }
                });
    }

    /***
     * 添加收藏
     * @param parmer
     */

    public void getinsertCollection(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .insertCollection(parmer)
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
                                getView().onCollectiohouse(userBeanHttpResult.getData());
                        }
                    }
                });
    }
}
