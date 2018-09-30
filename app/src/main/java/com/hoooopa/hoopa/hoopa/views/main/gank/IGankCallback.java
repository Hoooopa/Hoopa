package com.hoooopa.hoopa.hoopa.views.main.gank;

import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;

import java.util.List;

public interface IGankCallback {


    interface IGankAndroidCallback {

        void onGankAndroidData_Success(GankIoDataBean androidData);

        void onGankAndroidData_Error(String error);
    }

    interface IGankGirlsCallback{

        void onGankGirlsData_Success(GankIoDataBean girlsData);

        void onGankGirlsData_Error(String error);
    }


    interface IGankMoreCallback{

        void onGankMoreData_Sucess(List<AndroidBean> moreData);

        void onGankMoreData_Error(String error);
    }

}
