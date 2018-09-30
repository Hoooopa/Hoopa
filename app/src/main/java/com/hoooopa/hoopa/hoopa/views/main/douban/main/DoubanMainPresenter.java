package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.content.Subjects;
import com.hoooopa.hoopa.hoopa.views.main.douban.DoubanModel;
import com.hoooopa.hoopa.hoopa.views.main.douban.IDoubanCallback;
import com.hoooopa.hoopa.hoopa.views.main.douban.IDoubanView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pray on 2018/4/27.
 */

public class DoubanMainPresenter extends BasePresenter<IDoubanView.IDoubanMainView> {

    private IDoubanView.IDoubanMainView view;

    public DoubanMainPresenter(IDoubanView.IDoubanMainView view){
        this.view = view;
    }

    /**
     * 获取和返回"轮播图"的数据
     * @param count
     */
    public void getBannerData(int count){

        new DoubanModel().getBannerData(count, new IDoubanCallback.IDoubanMainCallback.BannerCallback() {
            @Override
            public void onBannerData_Success(MovieListBean movieListBean) {
                List<String> bannerID = new ArrayList<>();
                List<String> bannerTitle = new ArrayList<>();
                List<String> bannerImg = new ArrayList<>();

                for (int i = 0 ; i < movieListBean.subjects.size() ; i ++){
                    bannerID.add(movieListBean.subjects.get(i).id);
                    bannerTitle.add(movieListBean.subjects.get(i).title);
                    bannerImg.add(movieListBean.subjects.get(i).images.medium);
                }

                view.onBannerData_Success(bannerID, bannerTitle, bannerImg);
            }

            @Override
            public void onBannerData_Failed(String error) {
                view.onBannerData_Failed(error);
            }

        });

    }

    /**
     * 获取了返回"即将上映"的数据
     * @param count
     */
    public void getComingData(int count){

        new DoubanModel().getComingData(count, new IDoubanCallback.IDoubanMainCallback.ComingCallback() {
            @Override
            public void onComingData_Success(MovieListBean movieListBean) {
                List<Subjects> subjects = movieListBean.subjects;
                view.onComing_Success(subjects);
            }

            @Override
            public void onComingData_Failed(String error) {
                view.onComing_Failed(error);
            }
        });

    }

    public void getSaveData() {
            List<Subjects> subjects = new ArrayList<>();
            view.onSaveData_Got(subjects);
    }
}
