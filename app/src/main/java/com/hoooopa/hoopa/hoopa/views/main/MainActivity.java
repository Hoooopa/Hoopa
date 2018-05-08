package com.hoooopa.hoopa.hoopa.views.main;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;

import com.hoooopa.hoopa.hoopa.views.main.cook.mian.CookFragment;
import com.hoooopa.hoopa.hoopa.views.main.douban.main.DoubanFragment;
import com.hoooopa.hoopa.hoopa.views.main.gank.main.GankFragment;
import com.hoooopa.hoopa.hoopa.views.main.home.main.HomeFragment;
import com.hoooopa.hoopa.hoopa.views.main.last.main.LastFragment;
import com.jaeger.library.StatusBarUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity主要用途是切换Fragement，不存在数据请求等操作，所以也不需要使用MVP模式。
 */


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_bottombar)
    BottomBar bbMain;
    @BindView(R.id.activity_main_vp)
    ViewPager vpMain;

    List<BaseFragment> fragmentList = new ArrayList<>();

    private int vpOffscreenPage = 4 ;    //这个地方是存在隐患的！！！！记一下。好像是BaseFragment的attachView方法不对？

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   StatusBarUtil.setColor(MainActivity.this, Color.argb(125,0,188,212));
        ButterKnife.bind(this);

        initContentFragment();
        initListener();
    }

    /**
     * 初始化VP的内容
     */
    private void initContentFragment(){

        fragmentList.add(new HomeFragment());
        fragmentList.add(new DoubanFragment());
        fragmentList.add(new GankFragment());
        fragmentList.add(new CookFragment());
        fragmentList.add(new LastFragment());
        vpMain.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        vpMain.setOffscreenPageLimit(vpOffscreenPage);

    }


    /**
     * 初始化Listener
     */
    private void initListener(){

        vpMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bbMain.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bbMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.tab_home:   vpMain.setCurrentItem(0);break;
                    case R.id.tab_douban: vpMain.setCurrentItem(1);break;
                    case R.id.tab_gank:   vpMain.setCurrentItem(2);break;
                    case R.id.tab_cook:   vpMain.setCurrentItem(3);break;
                    case R.id.tab_other:  vpMain.setCurrentItem(4);break;
                    default:break;
                }
            }
        });

        bbMain.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                //这里需要做一个Activity给Fragment传值。如果是第二次点击，需要刷新当前Fragment的数据
            }
        });

    }


    /**
     * 返回键的操作
     */
    @Override
    public void onBackPressed() {
        //下述IF判断用于实现“再按一次退出程序”功能
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
