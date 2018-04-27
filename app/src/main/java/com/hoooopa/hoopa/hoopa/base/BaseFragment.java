package com.hoooopa.hoopa.hoopa.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Pray on 2018/4/25.
 */

public abstract class BaseFragment< V extends IBaseView , P extends BasePresenter<V> > extends Fragment{

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        try{
            mPresenter.attachView((V) this);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            mPresenter.detachView();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected abstract P createPresenter();



}
