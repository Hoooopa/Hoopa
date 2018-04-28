package com.hoooopa.hoopa.hoopa.views.main.cook;

import java.util.List;

/**
 * Created by Pray on 2018/4/28.
 */

public interface ICookView {

    void onQueryCookData_Start();

    void onQueryCookData_ReStart();

    void onQueryCookData_Success(String bannerData , String rcvData);   //成功则把返回的图片数据插入进去

    void onBannerAndRcv_Failure(String data);   //失败则显示没有数据的那个图片


}
