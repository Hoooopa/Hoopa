package com.hoooopa.hoopa.hoopa.views.main.cook;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;
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


/**
 * Created by Pray on 2018/4/28.
 */

public class CookModel {

    public static void getBannerData( final ICookCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                baseUrl(Constants.JD_CLOUD_COOK_BASE_URL).build();

                retrofit.create(HttpClient.class)
                        .getCookByID(1, Constants.JD_CLOUD_COOK_APPKEY)
                        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookFromIDBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookFromIDBean cookFromIDBean) {
                        callback.onBannerDataSucess(cookFromIDBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
