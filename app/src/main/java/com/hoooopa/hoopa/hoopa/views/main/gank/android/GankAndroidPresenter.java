package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.gankbean.android.Androidbean;


public class GankAndroidPresenter extends BasePresenter<IGankAndroidView> {

    private IGankAndroidView view;

    public GankAndroidPresenter(IGankAndroidView view){
        this.view = view;
    }


    public void getAndroidDate(){

        new GankAndroidModel().getAndroidData(10, 1, new IGankAndroidCallback() {
            @Override
            public void onGankAndroidData_Success(Androidbean androidData) {
                int a = androidData.getResults().get(0).getImages().length;
            }

            @Override
            public void onGankAndroidData_Error() {

            }
        });


    }

}
