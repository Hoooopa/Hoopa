package com.hoooopa.hoopa.hoopa.views.main.gank.more;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.GankModel;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankCallback;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

import java.util.List;

public class GankMorePresenter extends BasePresenter<IGankView.IGankMoreView> {

    private IGankView.IGankMoreView view;

    public GankMorePresenter(IGankView.IGankMoreView view){
        this.view = view;
    }



    public void getMoreData(int count , int page){
        new GankModel().getMoreData(count, page, new IGankCallback.IGankMoreCallback() {
            @Override
            public void onGankMoreData_Sucess(List<AndroidBean> moreData) {
                view.onMoreData_Success(moreData);
            }

            @Override
            public void onGankMoreData_Error(String error) {
                view.onMoreData_Error(error);
            }
        });
    }

}
