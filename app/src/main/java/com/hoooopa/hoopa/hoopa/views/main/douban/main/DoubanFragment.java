package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class DoubanFragment extends BaseFragment implements IDoubanView{

    private Unbinder unbinder;

    private DoubanPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_douban,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new DoubanPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }



    private void initDatas(){
        presenter.getBannerData(6);
        presenter.getComingData(18);
    }


    /**
     * 一系列对Banner的操作
     */
    @Override
    public void onBannerData_Start() {

    }

    @Override
    public void onBannerData_Failed(String error) {

    }

    @Override
    public void onBannerData_Success(MovieListBean movieListBean) {
        MovieListBean movieListBean1 = movieListBean;
        int i = 1+2;
    }


    /**
     * 一系列对“即将上映coming”的操作
     */
    @Override
    public void onComing_Start() {

    }

    @Override
    public void onComing_Failed(String error) {

    }

    @Override
    public void onComing_Success(MovieListBean movieListBean) {

    }



    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
