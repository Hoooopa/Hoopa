package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;

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

    public void getBannerAndRcvData(){


        view.onQueryCookData_Start();


        new CookModel().getCookDataByClassid(getRandomClassid(),getRandomStart(),16, new ICookCallback.CookDataByClassidCallback() {
            @Override
            public void onCookDataByClassid_Success(CookFromListBean cookFromListBean) {

                if (cookFromListBean.getResult().getResult().getList().size() < 16 ){
                    view.onQueryCookData_Failure("数据请求失败");
                }else {

                for (int i = 0 ; i < 16 ; i++ ){
                    if (i < 6){
                        bannerCookList.add(cookFromListBean.getResult().getResult().getList().get(i));
                    }else {
                        rcvCookList.add(cookFromListBean.getResult().getResult().getList().get(i));
                    }
                }
                view.onQueryCookData_Success(bannerCookList,rcvCookList);

                }
            }

            @Override
            public void onCookDataByClassid_Failure(String data) {
                view.onQueryCookData_Failure(data);
            }
        });

    }

    /**
     * 获取随机的Classid值
     * 2 - 625
     * @return classid
     */
    private int getRandomClassid(){
        return new Random().nextInt(624) + 2 ;
    }

    /**
     * 获取随机stat值
     * 0 - 5
     * @return stat
     */
    private int getRandomStart(){
        return new Random().nextInt(4);
    }


}
