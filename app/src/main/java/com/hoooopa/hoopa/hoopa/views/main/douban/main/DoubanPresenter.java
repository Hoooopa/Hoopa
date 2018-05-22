package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;

/**
 * Created by Pray on 2018/4/27.
 */

public class DoubanPresenter extends BasePresenter<IDoubanView> {

    private IDoubanView view;

    public DoubanPresenter(IDoubanView view){
        this.view = view;
    }

    public void getBannerData(int count){

        view.onBannerData_Start();

        new DoubanModel().getBannerData(count, new IDoubanCallback.BannerCallback() {
            @Override
            public void onBannerData_Success(MovieListBean movieListBean) {
                view.onBannerData_Success(movieListBean);
            }

            @Override
            public void onBannerData_Failed(String error) {
                view.onBannerData_Failed(error);
            }
        });



    }

    public void getComingData(int count){

        view.onComing_Start();

        new DoubanModel().getComingData(count, new IDoubanCallback.ComingCallback() {
            @Override
            public void onComingData_Success(MovieListBean movieListBean) {
                view.onComing_Success(movieListBean);
            }

            @Override
            public void onComingData_Failed(String error) {
                view.onComing_Failed(error);
            }
        });

    }



}
