package com.live.tv.mvp.presenter.mine;

import com.king.base.util.LogUtils;
import com.live.tv.App;
import com.live.tv.bean.MedicalClassBean;
import com.live.tv.http.HttpResult;
import com.live.tv.mvp.base.BasePresenter;
import com.live.tv.mvp.view.home.IUploadCaseView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @author Created by stone
 * @since 2018/1/10
 */

public class UploadCasePresenter extends BasePresenter<IUploadCaseView>{

    public UploadCasePresenter(App app) {
        super(app);
    }

    //多张图片上传
    public void uploadImgs(List<MultipartBody.Part> body) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .uploadImg(body)
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





    //上传电子病历
    public void insertMedical(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .InsertMedical(parmer)
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
                                getView().insertMedical(userBeanHttpResult.getData());
                        }
                    }
                });
    }

    //电子病历分类
    public void getMedicalClassBeans() {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getMedicalClass()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<MedicalClassBean>>>() {


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
                    public void onNext(HttpResult<List<MedicalClassBean>> userBeanHttpResult) {
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached())
                                getView().onGetMedicalClassBeans(userBeanHttpResult.getData());
                        }
                    }
                });
    }


}
