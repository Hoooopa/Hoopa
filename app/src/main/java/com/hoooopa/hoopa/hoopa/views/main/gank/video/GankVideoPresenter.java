package com.hoooopa.hoopa.hoopa.views.main.gank.video;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.gankbean.GankIoDataBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.GankModel;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankCallback;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

/**
 * Created by Pray on 2018/6/1.
 */

public class GankVideoPresenter extends BasePresenter<IGankView.IGankVideoView> {

    private IGankView.IGankVideoView view;

    public GankVideoPresenter(IGankView.IGankVideoView view){
        this.view = view;
    }

    public void getVideoData(int count , int page){

        new GankModel().getVideoData(count,page, new IGankCallback.IGankVideoCallback() {
            @Override
            public void onGankVideoData_Sucess(GankIoDataBean videoData) {
                view.onVideoData_Success(videoData.getResults());
            }

            @Override
            public void onGankVideoData_Error(String error) {
                view.onVideoData_error(error);
            }
        });

    }

}
