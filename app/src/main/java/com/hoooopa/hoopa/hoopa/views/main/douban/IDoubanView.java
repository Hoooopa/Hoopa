package com.hoooopa.hoopa.hoopa.views.main.douban;

import io.github.hoooopa.hooopa_core.bean.douban.content.Subjects;

import java.util.List;

public interface IDoubanView {

    interface IDoubanMainView{

        void onBannerData_Failed(String error);

        void onBannerData_Success(List<String> bannerID , List<String> bannerTitle , List<String> bannerImg);


        void onComing_Failed(String error);

        void onComing_Success(List<Subjects> subjects);


        void onDoubanTop_Faild(String error);

        void onDoubanTop_Success(List<Subjects> subjects);


        void onUsa_Faild(String error);

        void onUsa_Success(List<Subjects> subjects);


        void onSaveData_Got(List<Subjects> subjects);
    }

}
