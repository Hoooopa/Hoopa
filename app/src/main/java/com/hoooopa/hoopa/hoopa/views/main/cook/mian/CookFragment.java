package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;
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
        presenter.getBannerAndRcvData();
    }

    private void initLisenter(){

        cookBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public void onQueryCookData_Start() {
        //  presenter.getBannerAndRcvData();
    }



    /**
     * 对数据进行操作，给Banner和Rcv填充数据
     * @param bannerData banner的数据
     * @param rcvData    rcv列表的数据
     */
    @Override
    public void onQueryCookData_Success(List<CookBase> bannerData, List<CookBase> rcvData) {
        bannerId = getBannerId(bannerData);
        cookBanner.setImages(getBannerImageUrls(bannerData));
        cookBanner.setBannerTitles(getBannerTitles(bannerData));
        cookBanner.start();



    }

    @Override
    public void onQueryCookData_Failure(String data) {
        // presenter.getBannerAndRcvData();    //这个操作存疑！在数据少于16及请求出错的时候会重新请求数据
    }

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
     *
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
