package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;


public class GankAndroidPresenter extends BasePresenter<IGankAndroidView> {

    private IGankAndroidView view;

    public GankAndroidPresenter(IGankAndroidView view){
        this.view = view;
    }
}
