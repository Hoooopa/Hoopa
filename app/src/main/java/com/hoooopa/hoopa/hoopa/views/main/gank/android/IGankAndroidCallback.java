package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import com.hoooopa.hoopa.hoopa.bean.gankbean.android.Androidbean;

public interface IGankAndroidCallback {

    void onGankAndroidData_Success(Androidbean androidData);

    void onGankAndroidData_Error();

}
