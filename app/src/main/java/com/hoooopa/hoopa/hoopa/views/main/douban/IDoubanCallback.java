package com.hoooopa.hoopa.hoopa.views.main.douban;

import io.github.hoooopa.hooopa_core.bean.douban.MovieListBean;
import io.github.hoooopa.hooopa_core.bean.douban.UsaMovieListBean;

public interface IDoubanCallback {

    interface IDoubanMainCallback{
        /**
         * 轮播图：(正在热映)
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
        interface UsaCallback{

            void onUsaData_Success(UsaMovieListBean usaMovieListBean);

            void onUsaData_Failed(String error);
        }

    }

}
