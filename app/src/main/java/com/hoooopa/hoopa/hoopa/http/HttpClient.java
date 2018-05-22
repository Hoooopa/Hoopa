package com.hoooopa.hoopa.hoopa.http;

import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cooklist.CookFromListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pray on 2018/4/28.
 */

public interface HttpClient {

    //---------------------------菜谱信息---------------------------------------//

    /**
     * 根据关键字来获取菜谱列表
     * @param keyword  关键字
     * @param num      数量：最多20
     * @param appkey   开发者APPKEY
     * @return
     */
    @GET("search")
    Observable<CookFromListBean> getCookDataByKeyword( @Query("keyword") String keyword , @Query("num") int num , @Query("appkey") String appkey );

    /**
     * Api设置了classid值，根据classid来查询信息
     * @param classid 类别
     * @param start   开始id（这个id是每道菜的id）
     * @param num     最大数量：最多20
     * @param appkey
     * @return
     *
     * classid 在 305 到 389 中选择 。start
     */
    @GET("byclass")
    Observable<CookFromListBean> getCookDataByClassid( @Query("classid") int classid , @Query("start") int start , @Query("num") int num , @Query("appkey") String appkey );

    /**
     * 根据每道菜的具体id来请求。
     * @param id   范围 ：1 - 56390
     * @param appkey
     * @return
     */
    @GET("detail")
    Observable<CookFromIDBean> getCookDataByID( @Query("id") int id , @Query("appkey") String appkey );


    //---------------------------豆瓣电影信息------------------------------------//

    /**
     * 获取正在热映的电影数据
     * @param count  数量  （建议6条。据说黄金比例是0.618）
     * @return
     */
    @GET("in_theaters")
    Observable<MovieListBean> getMovieInTheaters(@Query("count") int count);

    /**
     * 获取即将上映的
     * @param count  数量 （建议12或18条。据说。。。。）
     * @return
     */
    @GET("coming_soon")
    Observable<MovieListBean> getMovieComingSoon(@Query("count") int count);

    /**
     * 获取豆瓣top250
     * @param start 0-249
     * @param count 用于上拉加载，建议上拉一次加载12条
     * @return
     */
    @GET("top250")
    Observable<MovieListBean> getMovieDoubanTop250(@Query("start") int start,@Query("count") int count);

    /**
     * 北美票房 直接Get,豆瓣给的是最新几天的数据
     */
    @GET("us_box")
    Observable<UsMovieListBean> getMovieUsBox();


    //-------------------------Gank干活集中营api----------------------------------//
}
