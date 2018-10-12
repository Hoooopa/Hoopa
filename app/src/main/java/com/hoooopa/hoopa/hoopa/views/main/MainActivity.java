package com.hoooopa.hoopa.hoopa.views.main;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import io.github.hoooopa.hooopa_core.base.BaseFragment;

import com.hoooopa.hoopa.hoopa.views.main.douban.main.DoubanMainFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.main.WanMainFragment;
import com.hoooopa.hoopa.hoopa.views.main.home.main.HomeMainFragment;
import io.github.hoooopa.hooopa_core.widget.MyViewPager;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity主要用途是切换Fragement，不存在数据请求等操作，所以也不需要使用MVP模式。
 */


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_vp) MyViewPager vpMain;
    @BindView(R.id.activity_main_drawer)
    DrawerLayout dlDrawer;

    @BindView(R.id.activity_main_status_bar)
    View statusBar;

    @BindView(R.id.activity_main_fl_drawer) FrameLayout flDrawer;
    @BindView(R.id.activity_main_fl_home) FrameLayout flHome;
    @BindView(R.id.activity_main_fl_wan) FrameLayout flWan;
    @BindView(R.id.activity_main_fl_douban) FrameLayout flDouban;
    @BindView(R.id.activity_main_fl_search) FrameLayout flSearch;
    @BindView(R.id.activity_main_img_home) ImageView ivHome;
    @BindView(R.id.activity_main_img_wan) ImageView ivWan;
    @BindView(R.id.activity_main_img_douban) ImageView ivDouban;
    @BindView(R.id.activity_main_img_search) ImageView ivSearch;



    List<BaseFragment> fragmentList = new ArrayList<>();

    private int vpOffscreenPage = 2 ;    //这个地方是存在隐患的！！！！记一下。好像是BaseFragment的attachView方法不对？

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initContentFragment();
        initListener();
    }

    private void initView(){

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = statusBarHeight;
            statusBar.setLayoutParams(params);
        }


    }

    /**
     * 初始化VP的内容
     */
    private void initContentFragment(){

        fragmentList.add(new HomeMainFragment());
        fragmentList.add(new WanMainFragment());
        fragmentList.add(new DoubanMainFragment());

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
        vpMain.setCurrentItem(1);
        ivWan.setSelected(true);
    }


    /**
     * 初始化Listener
     */
    private void initListener(){

        flHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivHome.setSelected(true);
                ivWan.setSelected(false);
                ivDouban.setSelected(false);
                vpMain.setCurrentItem(0);
            }
        });

        flWan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivHome.setSelected(false);
                ivWan.setSelected(true);
                ivDouban.setSelected(false);
                vpMain.setCurrentItem(1);
            }
        });

        flDouban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivHome.setSelected(false);
                ivWan.setSelected(false);
                ivDouban.setSelected(true);
                vpMain.setCurrentItem(2);
            }
        });

        flDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlDrawer.openDrawer(Gravity.START);
            }
        });

    }


    /**
     * 返回键的操作
     */
    @Override
    public void onBackPressed() {

        if (NiceVideoPlayerManager.instance().onBackPressd()){
            return;
        }

        //下述IF判断用于实现“再按一次退出程序”功能
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }

        super.onBackPressed();

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
