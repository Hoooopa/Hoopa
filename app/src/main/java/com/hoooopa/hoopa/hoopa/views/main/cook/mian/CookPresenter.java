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

    /**
     * 获取轮播图Bnnaer的数据
     */
    public void getBannerData(){

        view.onBannerData_Start();

        new CookModel().getCookDataByClassid(getRandomClassid() , getRandomStart() ,6 , new ICookCallback.CookDataByClassidCallback() {
            @Override
            public void onCookDataByClassid_Success(CookFromListBean cookFromListBean) {
                try {
                    for (int i = 0 ; i < 6 ; i++ ) {
                        bannerCookList.add(cookFromListBean.getResult().getResult().getList().get(i));
                    }
                    view.onBannerData_Success(bannerCookList);
                } catch (Exception e){
                    view.onBannerData_Restart();
                }
            }

            @Override
            public void onCookDataByClassid_Failure(String data) {
                /**
                 * 用错误代码来判断是否是返回的数据Gson解析错误
                 */
                if (data.contains("com.google.gson")){
                    view.onBannerData_Restart();
                }else {
                    view.onBannerData_Failure(data);
                }
            }
        });

    }


    /**
     * 获取列表Rcv的数据
     */
    public void getRcvData(int start){

        view.onRcvData_Start();

        new CookModel().getCookDataByClassid(getRandomClassid(), start, 10, new ICookCallback.CookDataByClassidCallback() {
            @Override
            public void onCookDataByClassid_Success(CookFromListBean data) {
                try {
                    for (int i = 0 ; i < 10 ; i++ ) {
                        rcvCookList.add(data.getResult().getResult().getList().get(i));
                    }
                    view.onRcvData_Success(rcvCookList);
                }catch (Exception e){
                    view.onRcvData_Restart();
                }
            }

            @Override
            public void onCookDataByClassid_Failure(String data) {
                view.onRcvData_Failure(data);
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
