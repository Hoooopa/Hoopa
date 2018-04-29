package com.hoooopa.hoopa.hoopa.views.main.cook.detail;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.WindowManager;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Pray on 2018/4/29.
 */

public class DetailActivity extends BaseActivity<IDetailView,DetailPresenter> implements IDetailView {

    private DetailPresenter presenter;

    @Override
    protected DetailPresenter createPresenter() {
        presenter = new DetailPresenter();
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);
        ButterKnife.bind(this);

    }

}
