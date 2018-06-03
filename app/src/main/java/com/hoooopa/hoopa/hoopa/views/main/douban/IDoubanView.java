package com.hoooopa.hoopa.hoopa.views.main.douban;

import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.content.Subjects;

import java.util.List;

public interface IDoubanView {

    interface IDoubanMainView{
        void onBannerData_Start();
        void onBannerData_Failed(String error);
        void onBannerData_Success(MovieListBean movieListBean , List<String> bannerID , List<String> bannerTitle , List<String> bannerImg);

        void onComing_Start();
        void onComing_Failed(String error);
        void onComing_Success(List<Subjects> subjects);

        void onSaveData_null();
        void onSaveData_Got(List<Subjects> subjects);
    }

}
