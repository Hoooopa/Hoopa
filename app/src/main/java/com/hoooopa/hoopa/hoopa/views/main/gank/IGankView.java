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


    interface IGankMoreView{

        void onMoreData_Success(List<AndroidBean> moreData);

        void onMoreData_Error(String e);
    }



}
