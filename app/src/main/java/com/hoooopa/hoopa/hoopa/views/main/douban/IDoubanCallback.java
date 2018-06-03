package com.hoooopa.hoopa.hoopa.views.main.douban;

import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;

public interface IDoubanCallback {

    interface IDoubanMainCallback{
        /**
         * 轮播图：正在热映
         */
        interface BannerCallback{

            void onBannerData_Success(MovieListBean movieListBean);

            void onBannerData_Failed(String error);
        }



        /**
         * 即将上映
         */
        interface ComingCallback{

            void onComingData_Success(MovieListBean movieListBean);

            void onComingData_Failed(String error);
        }


        /**
         * Top250
         */
        interface Top250Callback{

            void onDoubanTop250_Success(MovieListBean movieListBean);

            void onDoubanTop250_Failed(String error);
        }


        /**
         * 北美票房榜
         */
        interface UsBoxCallback{

            void onUsBoxData_Success(UsMovieListBean usMovieListBean);

            void onUsBoxData_Failed(String error);
        }

    }

}
