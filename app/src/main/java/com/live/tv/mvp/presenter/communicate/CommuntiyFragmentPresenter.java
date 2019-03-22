package com.live.tv.mvp.presenter.communicate;

import com.live.tv.App;
import com.live.tv.bean.CommAllBean;
import com.live.tv.bean.CommunityBannerBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.communicate.ICommuntiyFragmentView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/7/17
 */

public class CommuntiyFragmentPresenter extends BasePresenter<ICommuntiyFragmentView> {
    public CommuntiyFragmentPresenter(App app) {
        super(app);
    }

    /***
     * 获取banner
     * @param prams
     */
    public void getCommuntiyBanner(Map<String, String> prams) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getCommunityBanner(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CommunityBannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<CommunityBannerBean>> communityBannerBeanHttpResult) {
                        if (isViewAttached()) {

                            getView().OnBannerdata(communityBannerBeanHttpResult.getData());
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

    /***
     * 获取全部模块的data
     */
    public void getAllData(Map<String, String> params) {

        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .getAllData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CommAllBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(HttpResult<List<CommAllBean>> listHttpResult) {
                        if (isViewAttached()) {

                            getView().OnAllData(listHttpResult.getData());

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
