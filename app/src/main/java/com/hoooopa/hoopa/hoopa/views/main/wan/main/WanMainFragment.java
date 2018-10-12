package com.hoooopa.hoopa.hoopa.views.main.wan.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import io.github.hoooopa.hooopa_core.base.BaseFragment;

import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanAnimFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanArchitectureFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanBaseFucFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanCalendarFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanCameraFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanCompleteFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanCreativeFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanCrossPlatformFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanDatabaseFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanDialogFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanFastAppFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanFormFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanHardwareFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanHomeFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanImageViewFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanKeyboardFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanKtuFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanNetFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanPluginFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanQrCodeFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanRcvFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanRefreshFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanSourceFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanTvFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanVideoFragment;
import com.hoooopa.hoopa.hoopa.views.main.wan.fragments.WanVpFragment;
import io.github.hoooopa.hooopa_core.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class WanMainFragment extends BaseFragment  {

    private Unbinder unbinder;

    @BindView(R.id.fragment_gank_tablayout)
    TabLayout tbGank;
    @BindView(R.id.fragment_gank_vp)
    MyViewPager vpGank;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_wan_main,container,false);
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
        initFragmentList();
        initTitleList();

    }



    private void initViews() {

        vpGank.setOffscreenPageLimit(4);

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

        tbGank.setTabMode(TabLayout.MODE_SCROLLABLE);

        tbGank.setupWithViewPager(vpGank); //设置title必须在设置vp之后
        initTab();
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

    private void initFragmentList() {
        fragmentList.add(new WanHomeFragment());
        fragmentList.add(new WanCompleteFragment());
        fragmentList.add(new WanCrossPlatformFragment());
        fragmentList.add(new WanSourceFragment());
        fragmentList.add(new WanAnimFragment());
        fragmentList.add(new WanRcvFragment());
        fragmentList.add(new WanBaseFucFragment());
        fragmentList.add(new WanNetFragment());
        fragmentList.add(new WanTvFragment());
        fragmentList.add(new WanKeyboardFragment());
        fragmentList.add(new WanFastAppFragment());
        fragmentList.add(new WanCalendarFragment());
        fragmentList.add(new WanKtuFragment());
        fragmentList.add(new WanHardwareFragment());
        fragmentList.add(new WanFormFragment());
        fragmentList.add(new WanCreativeFragment());
        fragmentList.add(new WanImageViewFragment());
        fragmentList.add(new WanVideoFragment());
        fragmentList.add(new WanCameraFragment());
        fragmentList.add(new WanRefreshFragment());
        fragmentList.add(new WanArchitectureFragment());
        fragmentList.add(new WanDialogFragment());
        fragmentList.add(new WanDatabaseFragment());
        fragmentList.add(new WanPluginFragment());
        fragmentList.add(new WanVpFragment());
        fragmentList.add(new WanQrCodeFragment());
        fragmentList.add(new WanRcvFragment());
    }

    private void initTitleList(){
        titleList.add("主页");
        titleList.add("完整项目");
        titleList.add("跨平台app");
        titleList.add("资源聚合类");
        titleList.add("动画");
        titleList.add("Rcv列表");
        titleList.add("项目基础功能");
        titleList.add("net&file");
        titleList.add("TextView");
        titleList.add("键盘");
        titleList.add("快应用");
        titleList.add("日历&时钟");
        titleList.add("K线图");
        titleList.add("硬件相关");
        titleList.add("表格类");
        titleList.add("创意汇");
        titleList.add("ImageView");
        titleList.add("音视频&相机");
        titleList.add("相机");
        titleList.add("下拉刷新");
        titleList.add("架构");
        titleList.add("对话框");
        titleList.add("数据库");
        titleList.add("AS插件");
        titleList.add("ViewPager");
        titleList.add("二维码");
        titleList.add("富文本");
    }

    private void initTab(){
        tbGank.getTabAt(0).setText(titleList.get(0));
        tbGank.getTabAt(1).setText(titleList.get(1));
        tbGank.getTabAt(2).setText(titleList.get(2));
        tbGank.getTabAt(3).setText(titleList.get(3));
        tbGank.getTabAt(4).setText(titleList.get(4));
        tbGank.getTabAt(5).setText(titleList.get(5));
        tbGank.getTabAt(6).setText(titleList.get(6));
        tbGank.getTabAt(7).setText(titleList.get(7));
        tbGank.getTabAt(8).setText(titleList.get(8));
        tbGank.getTabAt(9).setText(titleList.get(9));
        tbGank.getTabAt(10).setText(titleList.get(10));
        tbGank.getTabAt(11).setText(titleList.get(11));
        tbGank.getTabAt(12).setText(titleList.get(12));
        tbGank.getTabAt(13).setText(titleList.get(13));
        tbGank.getTabAt(14).setText(titleList.get(14));
        tbGank.getTabAt(15).setText(titleList.get(15));
        tbGank.getTabAt(16).setText(titleList.get(16));
        tbGank.getTabAt(17).setText(titleList.get(17));
        tbGank.getTabAt(18).setText(titleList.get(18));
        tbGank.getTabAt(19).setText(titleList.get(19));
        tbGank.getTabAt(20).setText(titleList.get(20));
        tbGank.getTabAt(21).setText(titleList.get(21));
        tbGank.getTabAt(22).setText(titleList.get(22));
        tbGank.getTabAt(23).setText(titleList.get(23));
        tbGank.getTabAt(24).setText(titleList.get(24));
        tbGank.getTabAt(25).setText(titleList.get(25));
        tbGank.getTabAt(26).setText(titleList.get(26));
    }
}
