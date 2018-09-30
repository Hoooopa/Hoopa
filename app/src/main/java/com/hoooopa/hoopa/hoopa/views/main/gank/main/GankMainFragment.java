package com.hoooopa.hoopa.hoopa.views.main.gank.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.views.main.gank.more.GankMoreFragment;
import com.hoooopa.hoopa.hoopa.views.main.gank.android.GankAndroidFragment;
import com.hoooopa.hoopa.hoopa.views.main.gank.girls.GankGirlsFragment;
import com.hoooopa.hoopa.hoopa.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class GankMainFragment extends BaseFragment  {

    private Unbinder unbinder;

    @BindView(R.id.fragment_gank_tablayout)
    TabLayout tbGank;
    @BindView(R.id.fragment_gank_vp)
    MyViewPager vpGank;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank,container,false);
        unbinder = ButterKnife.bind(this,view);
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
        fragmentList.add(new GankAndroidFragment());
        fragmentList.add(new GankMoreFragment());
        fragmentList.add(new GankGirlsFragment());
        titleList.add("大安卓");
        titleList.add("More");
        titleList.add("福利");
    }

    private void initViews() {

        vpGank.setOffscreenPageLimit(3);

        vpGank.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        tbGank.setTabMode(TabLayout.MODE_FIXED);
        tbGank.setupWithViewPager(vpGank); //设置title必须在设置vp之后
        tbGank.getTabAt(0).setText(titleList.get(0));
        tbGank.getTabAt(1).setText(titleList.get(1));
        tbGank.getTabAt(2).setText(titleList.get(2));


    }

    private void initListener(){
        tbGank.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpGank.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onVisible() {

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
