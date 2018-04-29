package com.hoooopa.hoopa.hoopa.views.main.cook.save;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;

/**
 * Created by Pray on 2018/4/29.
 */

public class SaveActivity extends BaseActivity<ISaveView,SavePresenter> implements ISaveView {

    private SavePresenter presenter;

    @Override
    protected SavePresenter createPresenter() {
        return presenter;
    }
}
