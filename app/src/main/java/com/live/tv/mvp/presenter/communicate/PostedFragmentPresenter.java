package com.live.tv.mvp.presenter.communicate;

import com.live.tv.App;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.communicate.IPostedFragmentView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Created by stone
 * @since 2018/7/31
 */

public class PostedFragmentPresenter extends BasePresenter<IPostedFragmentView> {
    public PostedFragmentPresenter(App app) {
        super(app);
    }



    //多张图片上传
    public void uploadImgs(RequestBody body) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .uploadImgs(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String[]>>() {
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
                    public void onNext(HttpResult<String[]> userBeanHttpResult) {

                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onUploadImgs(userBeanHttpResult.getData());

                            }
                        }
                    }
                });
    }

//plateInterfaces.api?publishPost
public void getpost(Map<String,String> body) {
    if (isViewAttached()) {
        getView().showProgress();
    }
    getAppComponent().getAPIService()
            .getpost(body)
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

                    if (userBeanHttpResult != null) {
                        if (isViewAttached()) {
                            getView().onpostsuccess(userBeanHttpResult.getData());

                        }
                    }
                }
            });
}
}
