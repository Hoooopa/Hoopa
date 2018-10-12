package com.hoooopa.hoopa.hoopa.views.main.wan;

import io.github.hoooopa.hooopa_core.constants.Constants;

import java.util.List;

import io.github.hoooopa.hooopa_core.bean.wan.Datas;
import io.github.hoooopa.hooopa_core.bean.wan.WanBean;
import io.github.hoooopa.hooopa_core.net.HttpClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WanModel {


    public interface OnResultListener{
        void onSuccess(List<Datas> datas);
        void onFailed(String msg);
    }

    private static Retrofit  retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.WAN_ANDROID_BASE_URL)
            .build();

    /**
     * 获取安卓的数据
     * @param page
     */
    public static void getWanProjectData(final OnResultListener onResultListener ){
        retrofit.create(HttpClient.class)
                .getWanProjectData(1, 294)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WanBean data) {
                        onResultListener.onSuccess(data.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        onResultListener.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });





    }






}
