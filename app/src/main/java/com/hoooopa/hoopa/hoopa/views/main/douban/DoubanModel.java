package com.hoooopa.hoopa.hoopa.views.main.douban;

import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;
import com.hoooopa.hoopa.hoopa.constants.Constants;
import com.hoooopa.hoopa.hoopa.http.HttpClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoubanModel {

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.DOUBAN_API_BASE_URL)
            .build();

    public void getBannerData(int count, final IDoubanCallback.IDoubanMainCallback.BannerCallback callback){
        retrofit.create(HttpClient.class)
                .getMovieInTheaters(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieListBean movieListBean) {
                        callback.onBannerData_Success(movieListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onBannerData_Failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getComingData(int count,final IDoubanCallback.IDoubanMainCallback.ComingCallback callback){
        retrofit.create(HttpClient.class)
                .getMovieComingSoon(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieListBean movieListBean) {
                        callback.onComingData_Success(movieListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onComingData_Failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTop250Data(int start, int count ,final IDoubanCallback.IDoubanMainCallback.Top250Callback callback){
        retrofit.create(HttpClient.class)
                .getMovieDoubanTop250(start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieListBean movieListBean) {
                        callback.onDoubanTop250_Success(movieListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDoubanTop250_Failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUsBoxData(final IDoubanCallback.IDoubanMainCallback.UsBoxCallback callback){
        retrofit.create(HttpClient.class)
                .getMovieUsBox()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UsMovieListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UsMovieListBean usMovieListBean) {
                        callback.onUsBoxData_Success(usMovieListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onUsBoxData_Failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
