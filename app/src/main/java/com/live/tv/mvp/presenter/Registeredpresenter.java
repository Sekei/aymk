package com.live.tv.mvp.presenter;

import com.live.tv.App;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.UserBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.IRegisteredView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/1/19
 */

public class Registeredpresenter extends BasePresenter<IRegisteredView> {
    public Registeredpresenter(App app) {
        super(app);
    }

    //注册
    public void registerMember(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .registerMember(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserBean>>() {
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
                    public void onNext(HttpResult<UserBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onRegisterMember(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

    //验证码
    public void sendCode(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .sendCode(parmer)
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
                                getView().onSendCode(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

    /**
     * 绑定手机
     * @param parmer
     */
    public void getbuildnumber(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .getBundleNumber(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserBean>>() {
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
                    public void onNext(HttpResult<UserBean> userBeanHttpResult) {

                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onbuildnumber(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
