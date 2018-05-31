package com.hoooopa.hoopa.hoopa.views.main.cook.save;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;

/**
 * Created by Pray on 2018/4/29.
 */

public class CookSaveActivity extends BaseActivity<ICookSaveView,CookSavePresenter> implements ICookSaveView {

    private CookSavePresenter presenter;

    @Override
    protected CookSavePresenter createPresenter() {
        return presenter;
    }
}
