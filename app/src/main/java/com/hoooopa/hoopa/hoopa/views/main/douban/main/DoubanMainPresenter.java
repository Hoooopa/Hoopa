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

    public void getBannerData(int count){

        view.onBannerData_Start();

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

                view.onBannerData_Success(movieListBean,bannerID,bannerTitle,bannerImg);

            }

            @Override
            public void onBannerData_Failed(String error) {
                view.onBannerData_Failed(error);
            }
        });



    }

    public void getComingData(int count){

        view.onComing_Start();

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
//        //从数据库里拿数据
//        if (true )
//        {
//            //如果没有数据
//            view.onSaveData_null();
//        }else  {

            List<Subjects> subjects = new ArrayList<>();
            view.onSaveData_Got(subjects);
//        }



    }
}
