package com.hoooopa.hoopa.hoopa.views.main.gank;

import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;

public interface IGankCallback {


    interface IGankAndroidCallback {

        void onGankAndroidData_Success(GankIoDataBean androidData);

        void onGankAndroidData_Error(String error);

    }

    interface IGankGirlsCallback{
        void onGankGirlsData_Success(GankIoDataBean girlsData);
        void onGankGirlsData_Error(String error);
    }

    interface IGankVideoCallback{
        void onGankVideoData_Sucess(GankIoDataBean videoData);
        void onGankVideoData_Error(String error);
    }

    interface IGankIOSCallback{
        void onGankIOSData_Sucess(GankIoDataBean iosData);
        void onGankIOSData_Error(String error);
    }

    interface IGankWebCallback{
        void onGankWebData_Sucess(GankIoDataBean webData);
        void onGankWebData_Error(String error);
    }

    interface IGankMoreCallback{
        void onGankMoreData_Sucess(GankIoDataBean moreData);
        void onGankMoreData_Error(String error);
    }

}
