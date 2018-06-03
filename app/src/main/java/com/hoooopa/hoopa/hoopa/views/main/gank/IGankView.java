package com.hoooopa.hoopa.hoopa.views.main.gank;

import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;

import java.util.List;

public interface IGankView {

    interface IGankAndroidView{

        void onAndroidData_Success(List<AndroidBean> data);

        void onAndroidData_error(String e);
    }

    interface IGankGirlsView{
        void onGirlsData_Success(List<AndroidBean> data);

        void onGirlsData_error(String e);
    }

    interface IGankVideoView{
        void onVideoData_Success(List<AndroidBean> data);

        void onVideoData_error(String e);
    }

    interface IGankAllView{

        void onIosData_Success(List<AndroidBean> iosData);
        void onIosData_Error(String e);

        void onWebData_Success(List<AndroidBean> webData);
        void onWebData_Error(String e);

        void onMoreData_Success(List<AndroidBean> moreData);
        void onMoreData_Error(String e);
    }

}
