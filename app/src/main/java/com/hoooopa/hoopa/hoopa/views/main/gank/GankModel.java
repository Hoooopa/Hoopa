package com.hoooopa.hoopa.hoopa.views.main.gank;

import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;
import com.hoooopa.hoopa.hoopa.constants.Constants;
import com.hoooopa.hoopa.hoopa.http.HttpClient;

import java.security.PublicKey;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankModel {

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.GANK_IO_BASE_URL)
            .build();

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

    public void getVideoData(int count, int page , final IGankCallback.IGankVideoCallback callback){
        retrofit.create(HttpClient.class)
                .getGankVideoData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean androidData) {
                        callback.onGankVideoData_Sucess(androidData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankVideoData_Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getIosData(int count, int page , final IGankCallback.IGankIOSCallback callback){
        retrofit.create(HttpClient.class)
                .getGankIOSdData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean iosData) {
                        callback.onGankIOSData_Sucess(iosData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankIOSData_Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWebData(int count, int page ,final IGankCallback.IGankWebCallback callback){
        retrofit.create(HttpClient.class)
                .getGankWebData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean webData) {
                        callback.onGankWebData_Sucess(webData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankWebData_Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMoreData(int count, int page , final IGankCallback.IGankMoreCallback callback){
        retrofit.create(HttpClient.class)
                .getGankMoreData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIoDataBean moreData) {
                        callback.onGankMoreData_Sucess(moreData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankMoreData_Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
