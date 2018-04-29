package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;

import java.util.List;

/**
 * Created by Pray on 2018/4/28.
 */

public interface ICookView {

    /**
     * 轮播图Banner的数据
     */
    void onBannerData_Start();

    void onBannerData_Restart();

    void onBannerData_Success(List<CookBase> bannerData);   //成功则把返回的图片数据插入进去

    void onBannerData_Failure(String data);   //失败则显示没有数据的那个图片


    /**
     * 列表Rcv的数据
     */
    void onRcvData_Start();

    void onRcvData_Restart();

    void onRcvData_Success(List<CookBase> rcvData);

    void onRcvData_Failure(String data);




}
