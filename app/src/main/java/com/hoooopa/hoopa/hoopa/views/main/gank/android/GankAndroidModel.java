package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import com.hoooopa.hoopa.hoopa.bean.gankbean.android.Androidbean;
import com.hoooopa.hoopa.hoopa.constants.Constants;
import com.hoooopa.hoopa.hoopa.http.HttpClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankAndroidModel {

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.GANK_IO_BASE_URL)
            .build();

    public void getAndroidData(final int count, int page, final IGankAndroidCallback callback){
        retrofit.create(HttpClient.class)
                .getGankAndroidData(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Androidbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Androidbean androidData) {
                        callback.onGankAndroidData_Success(androidData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGankAndroidData_Error();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
