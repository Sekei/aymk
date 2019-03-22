package com.live.tv.mvp.presenter.communicate;

import com.live.tv.App;
import com.live.tv.bean.PlateListBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.communicate.ICommunityListFragmentView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/7/18
 */

public class CommunityListFragmentPresenter extends BasePresenter<ICommunityListFragmentView> {
    public CommunityListFragmentPresenter(App app) {
        super(app);
    }

    /**
     * 首页养生查看更多
     */

    public void gethomeHealthMore(Map<String, String> map) {
        if (isViewAttached()) {
            getView().showProgress();
        }

        getAppComponent().getAPIService()
                .getHomeHealthMore(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<PlateListBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<PlateListBean>> listHttpResult) {
                        if (isViewAttached()) {
                            getView().onHomeHealthMore(listHttpResult.getData());
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
