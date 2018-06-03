package com.hoooopa.hoopa.hoopa.views.main.cook.choose;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;
import com.hoooopa.hoopa.hoopa.base.IBaseView;
import com.hoooopa.hoopa.hoopa.views.main.cook.ICookView;

/**
 * Created by Pray on 2018/4/29.
 */

public class CookChooseActivity extends BaseActivity<ICookView.ICookChooseView, CookChoosePresenter> implements ICookView.ICookChooseView {

    private CookChoosePresenter presenter;

    @Override
    protected CookChoosePresenter createPresenter() {
        return presenter;
    }



}
