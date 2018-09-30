package com.hoooopa.hoopa.hoopa.views.main.gank;

import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;
import com.hoooopa.hoopa.hoopa.constants.Constants;
import com.hoooopa.hoopa.hoopa.http.HttpClient;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankModel {

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.GANK_IO_BASE_URL)
            .build();

    /**
     * 获取安卓的数据
     * @param count
     * @param page
     * @param callback
     */
    public void getAndroidData(int count, int page, final IGankCallback.IGankAndroidCallback callback){
        retrofit.create(HttpClient.class)
                .getGankAndroidData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean androidData) {
                        callback.onGankAndroidData_Success(androidData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankAndroidData_Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });





    }

    /**
     * 获取图片
     * @param count
     * @param page
     * @param callback
     */
    public void getGirlsData(int count, int page , final IGankCallback.IGankGirlsCallback callback){
        retrofit.create(HttpClient.class)
                .getGankGirlsData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean androidData) {
                        callback.onGankGirlsData_Success(androidData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankGirlsData_Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 获取IOS、Web、More的数据
     * @param count
     * @param page
     * @param callback
     */
    public void getMoreData(int count, int page , final IGankCallback.IGankMoreCallback callback){

//        retrofit.create(HttpClient.class)
//                .getGankIOSdData(count, page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GankIoDataBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GankIoDataBean data) {
//                        callback.onGankMoreData_Sucess(data.getResults());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


//        retrofit.create(HttpClient.class)
//                .getGankWebData(count, page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GankIoDataBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GankIoDataBean data) {
//                        callback.onGankMoreData_Sucess(data.getResults());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        retrofit.create(HttpClient.class)
                .getGankMoreData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean data) {
                        callback.onGankMoreData_Sucess(data.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankMoreData_Error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



}
