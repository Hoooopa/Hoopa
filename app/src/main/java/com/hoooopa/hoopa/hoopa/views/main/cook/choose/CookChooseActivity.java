package com.hoooopa.hoopa.hoopa.views.main.cook.choose;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;

/**
 * Created by Pray on 2018/4/29.
 */

public class CookChooseActivity extends BaseActivity<ICookChooseView, CookChoosePresenter> implements ICookChooseView {

    private CookChoosePresenter presenter;

    @Override
    protected CookChoosePresenter createPresenter() {
        return presenter;
    }



}
