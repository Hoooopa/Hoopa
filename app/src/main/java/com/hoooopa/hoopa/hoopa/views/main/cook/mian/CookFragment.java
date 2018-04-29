package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;
import com.hoooopa.hoopa.hoopa.views.main.cook.detail.DetailActivity;
import com.hoooopa.hoopa.hoopa.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/29.
 */

public class CookFragment extends BaseFragment implements ICookView {

    private Unbinder unbinder;

    private CookPresenter presenter;

    @BindView(R.id.fragment_cook_banner)
    Banner cookBanner;

    private List<String> bannerImageUrls = new ArrayList<>();
    private List<String> bannerTitles = new ArrayList<>();
    private List<String> bannerId = new ArrayList<>();

    private int start = 0 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cook,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new CookPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
        initLisenter();
    }

    private void initViews(){
        /**
         * banner的一系列操作
         * 还没加入数据
         */
        cookBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        cookBanner.setImageLoader(new GlideImageLoader());
        cookBanner.setBannerAnimation(Transformer.Default);
        cookBanner.isAutoPlay(true);
        cookBanner.setDelayTime(3500);
        cookBanner.setIndicatorGravity(BannerConfig.RIGHT);

    }

    private void initData(){
        presenter.getBannerData();
        presenter.getRcvData(start);
    }

    private void initLisenter(){

        /**
         * banner点击跳转到DetailActivity
         */
        cookBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("classid",bannerId.get(position));
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBannerData_Start() {
        //做一些开始加载的动作
    }

    @Override
    public void onBannerData_Restart() {
        //从新请求一边数据
        presenter.getBannerData();
    }


    /**
     * 对数据进行操作，给Banner和Rcv填充数据
     * @param bannerData banner的数据
     */
    @Override
    public void onBannerData_Success(List<CookBase> bannerData) {
        //得到轮播图图片的ID
        bannerId = getBannerId(bannerData);
        //加载轮播图数据
        cookBanner.setImages(getBannerImageUrls(bannerData));
        cookBanner.setBannerTitles(getBannerTitles(bannerData));
        //注意：别漏了.轮播图.start();
        cookBanner.start();
    }

    @Override
    public void onBannerData_Failure(String data) {

    }

    @Override
    public void onRcvData_Start() {

    }

    @Override
    public void onRcvData_Restart() {

    }

    @Override
    public void onRcvData_Success(List<CookBase> rcvData) {
        //RecycleView和Expandablelayout的结合
    }

    @Override
    public void onRcvData_Failure(String data) {

    }

    //以下数据操作应该到Presenter里去做！明天改

    /**
     * 得到Banner的图片地址
     *
     * @param bannerData
     * @return
     */

    private List getBannerImageUrls(List<CookBase> bannerData){
        for (int i = 0 ; i < bannerData.size() ; i++ ){
            bannerImageUrls.add(bannerData.get(i).getPic());
        }
        return bannerImageUrls;
    }

    /**
     * 得到Bnnaer的图片标题
     * @param bannerData
     * @return
     */
    private List<String> getBannerTitles(List<CookBase> bannerData){
        for (int i = 0 ; i < bannerData.size() ; i++ ){
            bannerTitles.add(bannerData.get(i).getName());
        }
        return bannerTitles;
    }


    /**
     * 获得Banner加载的id
     */
    private List<String> getBannerId(List<CookBase> bannerData){
        for (int i = 0 ; i < bannerData.size() ; i++){
            bannerId.add(bannerData.get(i).getId());
        }
        return bannerId;
    }





    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //由于做不到basefragment，所以只有手动了
        presenter = null;
        unbinder.unbind();
    }

}
