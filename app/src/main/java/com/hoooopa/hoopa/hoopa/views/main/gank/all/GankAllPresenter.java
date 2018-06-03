package com.hoooopa.hoopa.hoopa.views.main.gank.all;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.GankModel;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankCallback;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

public class GankAllPresenter extends BasePresenter<IGankView.IGankAllView> {

    private IGankView.IGankAllView view;

    public GankAllPresenter(IGankView.IGankAllView view){
        this.view = view;
    }


    public void getIosData(int count , int page){
        new GankModel().getIosData(count, page, new IGankCallback.IGankIOSCallback() {
            @Override
            public void onGankIOSData_Sucess(GankIoDataBean iosData) {
                view.onIosData_Success(iosData.getResults());
            }

            @Override
            public void onGankIOSData_Error(String error) {
                view.onIosData_Error(error);
            }
        });
    }

    public void getWebData(int count , int page){
        new GankModel().getWebData(count, page, new IGankCallback.IGankWebCallback() {
            @Override
            public void onGankWebData_Sucess(GankIoDataBean webData) {
                view.onWebData_Success(webData.getResults());
            }

            @Override
            public void onGankWebData_Error(String error) {
                view.onWebData_Error(error);
            }
        });
    }

    public void getMoreData(int count , int page){
        new GankModel().getMoreData(count, page, new IGankCallback.IGankMoreCallback() {
            @Override
            public void onGankMoreData_Sucess(GankIoDataBean moreData) {
                view.onWebData_Success(moreData.getResults());
            }

            @Override
            public void onGankMoreData_Error(String error) {
                view.onWebData_Error(error);
            }
        });
    }

}
