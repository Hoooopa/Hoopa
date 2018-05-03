package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;

import java.util.ArrayList;
import java.util.Arrays;
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

    private int[] uselessId =new int[]{1,113,144,213,233,269,301,390,453,524,561};

    /**
     * 获取轮播图Bnnaer的数据
     */
    public void getBannerData(){

        view.onBannerData_Start();

        new CookModel().getCookDataByClassid(getRandomClassid() , getRandomStart() ,6 , new ICookCallback.CookDataByClassidCallback() {
            @Override
            public void onCookDataByClassid_Success(CookFromListBean cookFromListBean) {
                List<CookBase> bannerCookList = cookFromListBean.getResult().getResult().getList();
                List<String> bannerId = new ArrayList<>();
                List<String> bannerUrl = new ArrayList<>();
                List<String> bannerTitle = new ArrayList<>();
                for (int i = 0 ; i < bannerCookList.size() ; i++ ) {
                    bannerId.add(bannerCookList.get(i).getId());
                    bannerUrl.add(bannerCookList.get(i).getPic());
                    bannerTitle.add(bannerCookList.get(i).getName());
                }
                view.onBannerData_Success(bannerCookList,bannerId,bannerUrl,bannerTitle);
            }

            @Override
            public void onCookDataByClassid_Failure(String data) {
                view.onBannerData_Failure(data);
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
                List<CookBase> rcvCookList = data.getResult().getResult().getList();
                view.onRcvData_Success(rcvCookList);
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
        int i;
        do { //do-while循环用于保证id不是那几个特殊的id
            i = new Random().nextInt(624) + 2;
        }while (Arrays.asList(uselessId).contains(i));
        return i;
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
