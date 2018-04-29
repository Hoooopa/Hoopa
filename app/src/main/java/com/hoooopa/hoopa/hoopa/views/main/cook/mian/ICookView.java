package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;

import java.util.List;

/**
 * Created by Pray on 2018/4/28.
 */

public interface ICookView {

    /**
     * 开始加载数据
     *
     * 注：在这里可以做一些数据没有加载出来之前的事
     */
    void onQueryCookData_Start();


    /**
     * 数据请求成功
     * @param bannerData banner的数据
     * @param rcvData    rcv列表的数据
     */
    void onQueryCookData_Success(List<CookBase> bannerData , List<CookBase> rcvData);   //成功则把返回的图片数据插入进去


    /**
     * 数据请求失败
     * @param data
     */
    void onQueryCookData_Failure(String data);   //失败则显示没有数据的那个图片


}
