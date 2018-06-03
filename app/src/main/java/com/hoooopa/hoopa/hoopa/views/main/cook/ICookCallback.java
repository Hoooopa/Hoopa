package com.hoooopa.hoopa.hoopa.views.main.cook;

import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;

public interface ICookCallback {

    interface ICookMainCallback {

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

    interface ICookDetailCallback{

    }

    interface ICookChooseCallback{

    }

    interface ICookSaveCallback{

    }

}
