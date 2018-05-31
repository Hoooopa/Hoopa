package com.hoooopa.hoopa.hoopa.views.main.cook.detail;

import android.os.Bundle;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Pray on 2018/4/29.
 */

public class CookDetailActivity extends BaseActivity<ICookDetailView,CookDetailPresenter> implements ICookDetailView {

    private CookDetailPresenter presenter;

    @Override
    protected CookDetailPresenter createPresenter() {
        presenter = new CookDetailPresenter();
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);

    }

}
