package io.github.hoooopa.hooopa_core.base;

import android.support.v4.app.Fragment;


/**
 * Created by Pray on 2018/4/25.
 */

public abstract class BaseFragment extends Fragment {


    //防止懒加载时多次加载。
    private boolean isPrepared = false;
    /**
     * 根据Fragment是否被看到来实现数据的懒加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isPrepared){
            onVisible();
            isPrepared = true;
        }
    }

    protected abstract void onVisible();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
    }
}
