package com.hoooopa.hoopa.hoopa;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;


/**
 * Created by Pray on 2018/4/25.
 * 一个程序有且仅有一个Application，通过Application能做很多事
 * 在Manifest中指定Application：android:name=".HoopaApplication"
 *
 * 规定几个命名规范：
 *   drawable内:
 *      bt_guide_bg.xml
 *      iv_spalsh_bg.png
 *   layout内:
 *      activity_guide.xml
 *      fragemnt_cookbook.xml
 *   layout内的控件:
 *      actvity_spalsh_iv
 *      item_cookbook_tv_1
 *   资源命名
 *      guide_bt_color
 *      guide_bt_text
 *
 *   Java内部变量命名
 *      btLogin
 *      vpGuide
 */

public class HoopaApplication extends Application {

    private HoopaApplication app;
    private Activity mActivity;

    @Override
    public void onCreate(){
        super.onCreate();
        app  = this;

        activityLifecycleCallback();

    }

    /**
     * 设置Activity的生命周期监听
     */

    private void activityLifecycleCallback(){

        app.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivity = activity;

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }




}
