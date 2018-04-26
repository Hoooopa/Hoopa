package com.hoooopa.hoopa.hoopa.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Pray on 2018/4/25.
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = createPresenter();
        //内存泄漏
        //关联View
        mPresenter.attachView((V)this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //接触关联
        mPresenter.detachView();
    }
    protected abstract T createPresenter();


}
