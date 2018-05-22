package com.hoooopa.hoopa.hoopa.views.main.cook.mian;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.CookMainRcvAdapter;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;

import com.hoooopa.hoopa.hoopa.widget.ClearEditText;
import com.hoooopa.hoopa.hoopa.widget.ScrollLinearLayoutManager;
import com.hoooopa.hoopa.hoopa.views.main.cook.detail.DetailActivity;
import com.hoooopa.hoopa.hoopa.widget.GlideImageLoader;
import com.hoooopa.hoopa.hoopa.widget.TranslucentNestedScrollView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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

    @BindView(R.id.fragment_cook_banner)
    Banner bannerCook;
    @BindView(R.id.fragment_cook_rcv)
    RecyclerView rcvCook;
    @BindView(R.id.fragment_cook_refresh)
    SmartRefreshLayout refreshCook;
    @BindView(R.id.fragment_cook_toobar)
    Toolbar toolbarCook;
    @BindView(R.id.fragment_cook_scroll)
    TranslucentNestedScrollView scrollCook;
    @BindView(R.id.fragment_cook_iv_save)
    ImageView ivSave;
    @BindView(R.id.fragment_cook_ll_search)
    LinearLayout llSearch;
    @BindView(R.id.fragment_cook_et_key)
    ClearEditText etKey;
    @BindView(R.id.fragment_cook_iv_search)
    ImageView ivSearch;

    private Unbinder unbinder;
    private CookPresenter presenter;
    private List<String> bannerId = new ArrayList<>();
    private int rcvStart = 0 ;

    private CookMainRcvAdapter adapter;
    private List<CookBase> rcvDataBase = new ArrayList<>();

    private float headerHeight;//顶部高度
    private float minHeaderHeight;//顶部最低高度，即Bar的高度

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
        bannerCook.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bannerCook.setImageLoader(new GlideImageLoader());
        bannerCook.setBannerAnimation(Transformer.Default);
        bannerCook.isAutoPlay(true);
        bannerCook.setDelayTime(3500);
        bannerCook.setIndicatorGravity(BannerConfig.RIGHT);
        bannerCook.setBackgroundResource(R.drawable.douban_banner_default);

        /**
         * Rcv的操作
         */
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getContext());
        scrollLinearLayoutManager.setScrollEnabled(false);
        rcvCook.setLayoutManager(scrollLinearLayoutManager);

        /**
         * SmartRefresh的操作
         */
        refreshCook.setRefreshHeader(new ClassicsHeader(getContext()));

        /**
         * 修改hintal颜色啊~
         */
        etKey.setHintTextColor(getResources().getColor(R.color.textColorSecondary));
    }

    private void initData(){
        presenter.getBannerData();
        presenter.getRcvData(rcvStart);       //伪随机推荐(请求start为0，classid随机)

        //用于计算Toolbar与header高度差
        headerHeight = getResources().getDimension(R.dimen.dimen_banner);
        minHeaderHeight = getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
    }

    private void initLisenter(){
        /**
         * banner点击跳转到DetailActivity
         */
        bannerCook.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("classid",bannerId.get(position));
                startActivity(intent);
            }
        });

        refreshCook.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getBannerData();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                presenter.getRcvData(rcvStart);
            }
        });

        /**
         * 在这里操作顶部的搜索的颜色之类的操作
         */
        scrollCook.setOnScrollChangedListener(new TranslucentNestedScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(NestedScrollView who, int l, int t, int oldl, int oldt) {

                //Y轴偏移量
                float scrollY = who.getScrollY();

                //变化率
                float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值
                float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);

                //Toolbar背景色透明度
                toolbarCook.setBackgroundColor(Color.argb((int) (offset * 255), 255,255,255));

                if (offset > 0.50){
                    llSearch.setBackground(getActivity().getDrawable(R.drawable.ll_cook_search_bg_grey));
                    etKey.setHintTextColor(Color.WHITE);
                    ivSearch.setImageResource(R.drawable.ic_cook_search_white);
                }else {
                    llSearch.setBackground(getActivity().getDrawable(R.drawable.ll_cook_search_bg_white));
                    etKey.setHintTextColor(getResources().getColor(R.color.textColorSecondary));
                    ivSearch.setImageResource(R.drawable.ic_cook_search_grey);
                }

            }
        });

    }




    @Override
    public void onBannerData_Start() {
        //做一些开始加载的动作
    }

    @Override
    public void onBannerData_Restart() {
        presenter.getBannerData();
    }

    /**
     * 对数据进行操作，给Banner和Rcv填充数据
     * @param bannerData banner的数据
     */
    @Override
    public void onBannerData_Success(List<CookBase> bannerData,List<String> bannerId, List<String> bannerUrl,List<String> bannerTitle) {
        //得到轮播图图片的ID
        this.bannerId = bannerId;
        //加载轮播图数据
        bannerCook.setImages(bannerUrl);
        bannerCook.setBannerTitles(bannerTitle);
        bannerCook.start();//注意：别漏了.轮播图.start();

        refreshCook.finishRefresh();
    }

    @Override
    public void onBannerData_Failure(String data) {
        refreshCook.finishRefresh();
    }

    @Override
    public void onRcvData_Start() {

    }

    @Override
    public void onRcvData_Restart() {
        presenter.getRcvData(rcvStart);
    }

    @Override
    public void onRcvData_Success(List<CookBase> rcvData) {
        if (adapter == null){
            rcvDataBase = rcvData;
            adapter = new CookMainRcvAdapter(getContext(),rcvDataBase,rcvCook, new CookMainRcvAdapter.onCookRcvClickListener() {
                @Override
                public void onThumbClickListener(String url) {
                    Toast.makeText(getContext(),url,Toast.LENGTH_SHORT).show();
                }
            });
            rcvCook.setAdapter(adapter);
        }else {
            rcvDataBase.addAll(rcvData);
        }
        adapter.notifyDataSetChanged();
        refreshCook.finishLoadMore();
    }

    @Override
    public void onRcvData_Failure(String data) {
        refreshCook.finishLoadMore();
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
