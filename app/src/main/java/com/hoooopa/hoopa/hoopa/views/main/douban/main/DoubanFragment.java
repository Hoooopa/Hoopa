package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.UsMovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.content.Subjects;
import com.hoooopa.hoopa.hoopa.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class DoubanFragment extends BaseFragment implements IDoubanView{

    private Unbinder unbinder;

    @BindView(R.id.fragment_douban_banner)
    Banner bannerDouban;

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
        initViews();
        initListener();
    }



    private void initDatas(){
        //轮播图建议6个数据.但是我还是要上10个数据
        presenter.getBannerData(10);
        //即将上映18个数据
        presenter.getComingData(18);
    }

    private void initViews(){
        /**
         * banner的一系列操作
         * 还没加入数据
         */
        bannerDouban.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        bannerDouban.setImageLoader(new GlideImageLoader());
        bannerDouban.setBannerAnimation(Transformer.Default);
        bannerDouban.isAutoPlay(true);
        bannerDouban.setDelayTime(3500);
        bannerDouban.setIndicatorGravity(BannerConfig.RIGHT);
        bannerDouban.setBackgroundResource(R.drawable.douban_banner_default);

    }

    private void initListener(){

        bannerDouban.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });


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
    public void onBannerData_Success(MovieListBean movieListBean, List<String> bannerID, List<String> bannerTitle, List<String> bannerImg) {

        bannerDouban.setBannerTitles(bannerTitle);
        bannerDouban.setImages(bannerImg);
        bannerDouban.start();


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
    public void onComing_Success(List<Subjects> subjects) { //返回Subject直接用于Rcv。然后点击详情的时候也直接传这个Subject.get(i)就好

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
