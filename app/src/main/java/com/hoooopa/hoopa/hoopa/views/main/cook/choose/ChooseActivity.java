package com.hoooopa.hoopa.hoopa.views.main.cook.choose;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;

/**
 * Created by Pray on 2018/4/29.
 */

public class ChooseActivity extends BaseActivity<IChooseView, ChoosePresenter> implements IChooseView {

    private ChoosePresenter presenter;

    @Override
    protected ChoosePresenter createPresenter() {
        return presenter;
    }



}
