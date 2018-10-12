package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import io.github.hoooopa.hooopa_core.base.BasePresenter;
import io.github.hoooopa.hooopa_core.bean.douban.MovieListBean;
import io.github.hoooopa.hooopa_core.bean.douban.content.Subjects;
import com.hoooopa.hoopa.hoopa.views.main.douban.DoubanModel;
import com.hoooopa.hoopa.hoopa.views.main.douban.IDoubanCallback;
import com.hoooopa.hoopa.hoopa.views.main.douban.IDoubanView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Pray on 2018/4/27.
 */

public class DoubanMainPresenter extends BasePresenter<IDoubanView.IDoubanMainView> {

    private IDoubanView.IDoubanMainView view;

    public DoubanMainPresenter(IDoubanView.IDoubanMainView view){
        this.view = view;
    }

    private DoubanModel doubanModel = new DoubanModel();

    /**
     * 获取和返回"轮播图"的数据
     * @param
     */
    public void getBannerData(){

        doubanModel.getBannerData(8, new IDoubanCallback.IDoubanMainCallback.BannerCallback() {
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
     * @param
     */
    public void getComingData(){

        doubanModel.getComingData(6, new IDoubanCallback.IDoubanMainCallback.ComingCallback() {
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

    public void getDoubanTopData(){
        doubanModel.getTop250Data(new Random().nextInt(244),6, new IDoubanCallback.IDoubanMainCallback.Top250Callback() {
            @Override
            public void onDoubanTop250_Success(MovieListBean movieListBean) {
                List<Subjects> subjects = movieListBean.subjects;
                view.onDoubanTop_Success(subjects);
            }

            @Override
            public void onDoubanTop250_Failed(String error) {

            }
        });
    }

    public void getSaveData() {
            List<Subjects> subjects = new ArrayList<>();
            view.onSaveData_Got(subjects);
    }
}
