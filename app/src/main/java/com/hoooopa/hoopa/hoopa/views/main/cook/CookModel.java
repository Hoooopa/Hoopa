package com.hoooopa.hoopa.hoopa.views.main.cook;

import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;
import com.hoooopa.hoopa.hoopa.constants.Constants;
import com.hoooopa.hoopa.hoopa.http.HttpClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CookModel {

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.JD_CLOUD_COOK_BASE_URL)
            .build();

    public void getCookDataByClassid(final int classid, int start, int num, final ICookCallback.ICookMainCallback.CookDataByClassidCallback callback){
        retrofit.create(HttpClient.class)
                .getCookDataByClassid(classid, start , num ,Constants.JD_CLOUD_COOK_APPKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookFromListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookFromListBean cookFromListBean) {
                        callback.onCookDataByClassid_Success(cookFromListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCookDataByClassid_Failure(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getCookDataByKeyword(String keyword, int num , final ICookCallback.ICookMainCallback.CookDataByKeywordCallback callback){
        retrofit.create(HttpClient.class)
                .getCookDataByKeyword(keyword, num ,Constants.JD_CLOUD_COOK_APPKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookFromListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookFromListBean cookFromListBean) {
                        callback.onCookDataByKeyword_Success(cookFromListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCookDataByKeyword_Failure("请求数据出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
