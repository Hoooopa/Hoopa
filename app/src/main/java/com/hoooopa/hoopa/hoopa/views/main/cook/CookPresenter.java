package com.hoooopa.hoopa.hoopa.views.main.cook;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
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

    public CookPresenter(ICookView view){
        this.view = view;
    }

    public void getBannerAndRcvData(Boolean first){

        if (first){
            view.onQueryCookData_Start();
        }else {
            view.onQueryCookData_ReStart();
        }

        new CookModel().getCookDataByClassid(getRandomClassid(),getRandomStart(),15, new ICookCallback.CookDataByClassidCallback() {
            @Override
            public void onCookDataByClassid_Success(CookFromListBean cookFromListBean) {
                view.onQueryCookData_Success(cookFromListBean.getResult().getResult().getList().get(1).getContent(),"6566666666666666");
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

    private List<Integer> getRandomID(){

        List<Integer> ids =new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);

        return ids;
    }




}
