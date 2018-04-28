package com.hoooopa.hoopa.hoopa.views.main.cook;

import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;

/**
 * Created by Pray on 2018/4/28.
 */

public interface ICookCallback {

    void onBannerDataSucess(CookFromIDBean data);

    void onBannerDataFailure(String data);


}
