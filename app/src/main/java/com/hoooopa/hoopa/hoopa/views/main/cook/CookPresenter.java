package com.hoooopa.hoopa.hoopa.views.main.cook;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;
import com.hoooopa.hoopa.hoopa.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Pray on 2018/4/28.
 */

public class CookPresenter extends BasePresenter<ICookView> {

    private ICookView view;

    private List<CookBase> bannerCookList = new ArrayList<>();

    private List<CookBase> rcvCookList = new ArrayList<>();


    public CookPresenter(ICookView view){
        this.view = view;
    }

    public void getBannerAndRcvData(Boolean first){

        if (first){
            view.onQueryCookData_Start();
        }else {
            view.onQueryCookData_ReStart();
        }

        new CookModel().getCookDataByClassid(getRandomClassid(),getRandomStart(),16, new ICookCallback.CookDataByClassidCallback() {
            @Override
            public void onCookDataByClassid_Success(CookFromListBean cookFromListBean) {
                for (int i = 0 ; i < 16 ; i++ ){
                    if (i < 6){
                        bannerCookList.add(cookFromListBean.getResult().getResult().getList().get(i));
                    }else {
                        rcvCookList.add(cookFromListBean.getResult().getResult().getList().get(i));
                    }
                }
                view.onQueryCookData_Success(bannerCookList,rcvCookList);
            }

            @Override
            public void onCookDataByClassid_Failure(String data) {
                view.onBannerAndRcv_Failure(data);
            }
        });

    }

    /**
     * 获取随机的Classid值
     * 范围为 2 - 625
     * @return classid
     */
    private int getRandomClassid(){
        return new Random().nextInt(624) + 2 ;
    }

    private int getRandomStart(){
        return new Random().nextInt(4);
    }


}
