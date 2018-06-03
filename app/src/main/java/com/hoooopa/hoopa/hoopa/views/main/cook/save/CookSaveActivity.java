package com.hoooopa.hoopa.hoopa.views.main.cook.save;

import com.hoooopa.hoopa.hoopa.base.BaseActivity;
import com.hoooopa.hoopa.hoopa.base.IBaseView;
import com.hoooopa.hoopa.hoopa.views.main.cook.ICookView;

/**
 * Created by Pray on 2018/4/29.
 */

public class CookSaveActivity extends BaseActivity<ICookView.ICookSaveView,CookSavePresenter> implements ICookView.ICookSaveView {

    private CookSavePresenter presenter;

    @Override
    protected CookSavePresenter createPresenter() {
        return presenter;
    }
}
