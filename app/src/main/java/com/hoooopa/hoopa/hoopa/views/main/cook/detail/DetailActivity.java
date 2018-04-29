package com.hoooopa.hoopa.hoopa.views.main.cook.detail;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;

/**
 * Created by Pray on 2018/4/29.
 */

public class DetailActivity extends BaseActivity<IDetailView,DetailPresenter> implements IDetailView {

    private DetailPresenter presenter;

    @Override
    protected DetailPresenter createPresenter() {
        return presenter;
    }
}
