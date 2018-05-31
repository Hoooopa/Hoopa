package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.douban.DoubanComingAdapter;
import com.hoooopa.hoopa.hoopa.adapter.douban.DoubanSaveAdapter;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.MovieListBean;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.content.Subjects;
import com.hoooopa.hoopa.hoopa.views.main.douban.moviedetail.MovieDetailActivity;
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

public class DoubanFragment extends BaseFragment implements IDoubanView {

    private Unbinder unbinder;

    @BindView(R.id.fragment_douban_banner)
    Banner bannerDouban;
    @BindView(R.id.fragment_douban_rcv_coming)
    RecyclerView rcvComing;
    @BindView(R.id.fragment_douban_rcv_save)
    RecyclerView rcvSave;

    private DoubanPresenter presenter;

    private DoubanComingAdapter comingAdapter;
    private DoubanSaveAdapter saveAdapter;

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
        //得到sava收藏的数据
        presenter.getSaveData(); //按照节省流量的角度考虑，应该把被Save的电影的剧照给保存本地然后之后直接加载本地的图片。但是那个略麻烦，等以后再做
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

        /**
         * RCV的一系列操作
         */
        rcvComing.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false));
        rcvComing.setNestedScrollingEnabled(false);
        rcvSave.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));
        rcvSave.setNestedScrollingEnabled(false);

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
        bannerDouban.start();//开始你的轮播

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
        comingAdapter = new DoubanComingAdapter(getContext(), subjects, new DoubanComingAdapter.onComingItemClickListener() {
            @Override
            public void onItemClickListener(Subjects subjects) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MovieDetailActivity.class);

            }
        });
        rcvComing.setAdapter(comingAdapter);


    }

    @Override
    public void onSaveData_null() {
        //如果没有数据就显示(哎呀，把想看的电影收藏起来吧)
    }

    @Override
    public void onSaveData_Got(List<Subjects> subjects) {
        //一个Recycleview，     .在收藏的时候让用户选择 优先观看，稍后再看，先记着等状态    所以item需要有改变优先级、删除等操作
        saveAdapter = new DoubanSaveAdapter(getContext(),subjects);
        rcvSave.setAdapter(saveAdapter);
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
