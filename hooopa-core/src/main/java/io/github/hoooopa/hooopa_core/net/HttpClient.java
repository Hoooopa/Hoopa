package io.github.hoooopa.hooopa_core.net;

import io.github.hoooopa.hooopa_core.bean.douban.MovieListBean;
import io.github.hoooopa.hooopa_core.bean.douban.UsaMovieListBean;

import io.github.hoooopa.hooopa_core.bean.wan.Data;
import io.github.hoooopa.hooopa_core.bean.wan.WanBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pray on 2018/4/28.
 */

public interface HttpClient {

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
    Observable<UsaMovieListBean> getMovieUsa();


    //-------------------------wanAndroid_api----------------------------------//

    /**
     * 玩安卓项目数据
     * @param page 每页15条
     * @param cid
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<WanBean> getWanProjectData(@Path("page") int page, @Query("cid") int cid);



}
