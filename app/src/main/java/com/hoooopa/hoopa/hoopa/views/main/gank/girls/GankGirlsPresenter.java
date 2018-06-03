package com.hoooopa.hoopa.hoopa.views.main.gank.girls;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.GankModel;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankCallback;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

public class GankGirlsPresenter extends BasePresenter<IGankView.IGankGirlsView> {

    private IGankView.IGankGirlsView view;

    public GankGirlsPresenter(IGankView.IGankGirlsView view){
        this.view = view;
    }


    public void getGrilsData(int count , int page ){

        new GankModel().getGirlsData(count, page, new IGankCallback.IGankGirlsCallback() {
            @Override
            public void onGankGirlsData_Success(GankIoDataBean girlsData) {
                view.onGirlsData_Success(girlsData.getResults());
            }

            @Override
            public void onGankGirlsData_Error(String error) {
                view.onGirlsData_error(error);
            }
        });

    }

}
