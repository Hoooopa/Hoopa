package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.GankModel;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankCallback;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

class GankAndroidPresenter extends BasePresenter<IGankView.IGankAndroidView> {

    private IGankView.IGankAndroidView view;

    public GankAndroidPresenter(IGankView.IGankAndroidView view){
        this.view = view;
    }

    public void getAndroidDate(int count, int page) {

        new GankModel().getAndroidData(count, page, new IGankCallback.IGankAndroidCallback() {
            @Override
            public void onGankAndroidData_Success(GankIoDataBean androidData) {
                view.onAndroidData_Success(androidData.getResults());
            }

            @Override
            public void onGankAndroidData_Error(String error) {
                view.onAndroidData_error(error);
            }
        });
    }

}
