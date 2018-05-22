package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import com.hoooopa.hoopa.hoopa.base.IBaseView;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;

/**
 * Created by Pray on 2018/4/27.
 */

public interface IDoubanView extends IBaseView {

    void onBannerData_Start();
    void onBannerData_Failed(String error);
    void onBannerData_Success(MovieListBean movieListBean);

    void onComing_Start();
    void onComing_Failed(String error);
    void onComing_Success(MovieListBean movieListBean);


}
