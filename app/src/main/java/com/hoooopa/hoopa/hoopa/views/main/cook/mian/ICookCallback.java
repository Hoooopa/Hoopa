package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;

/**
 * Created by Pray on 2018/4/28.
 */

public interface ICookCallback {

    interface CookDataByClassidCallback{

        void onCookDataByClassid_Success(CookFromListBean data);

        void onCookDataByClassid_Failure(String data);

    }


    interface CookDataByKeywordCallback{

        void onCookDataByKeyword_Success(CookFromListBean cookFromListBean);

        void onCookDataByKeyword_Failure(String data);

    }

    interface CooDataByIdCallback{

        void onCookDataById_Success(CookFromIDBean cookFromIDBean);

        void onCookDataById_Failure(String data);

    }


}
