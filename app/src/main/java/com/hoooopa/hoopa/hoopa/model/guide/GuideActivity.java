package com.hoooopa.hoopa.hoopa.model.guide;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.GuideAdapter;
import com.hoooopa.hoopa.hoopa.base.BaseActivity;
import com.hoooopa.hoopa.hoopa.model.home.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Pray on 2018/4/25.
 */

public class GuideActivity extends BaseActivity<IGuideView,GuidePresenter> implements IGuideView{

    @BindView(R.id.activity_guide_bt)
    Button btLogin;
    @BindView(R.id.activity_guide_vp)
    ViewPager vpGuide;
    @BindView(R.id.activity_guide_point)
    CircleIndicator point;

    private GuidePresenter presenter;     //presenter
    private GuideAdapter mAdapter;        //适配器
    private int imgListLength;            //图片列表的长度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        loadVpData();
        initListener();
    }

    /**
     * 绑定Presenter
     * @return
     */
    @Override
    protected GuidePresenter createPresenter() {
        presenter = new GuidePresenter(this,GuideActivity.this);
        return presenter;
    }


    /**
     * 加载VP需要的数据
     */
    private void loadVpData(){
        presenter.loadVpData();
    }


    /**
     * 设置VP和小点儿
     * @param imageViews
     * @param length
     */
    @Override
    public void initVp(List<View> imageViews , int length) {
        imgListLength = length;
        mAdapter = new GuideAdapter(GuideActivity.this,imageViews);
        vpGuide.setAdapter(mAdapter);
        point.setViewPager(vpGuide);
    }


    private void initListener(){

        // Button监听，跳转到MainActivity
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

        // VP的页面变化监听
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imgListLength - 1){
                    //最后一页的时候把登录按钮使用动画显示出来，隐藏小点儿
                    point.setVisibility(View.GONE);
                    btLogin.setVisibility(View.VISIBLE);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1f);
                    alphaAnimation.setDuration(1500);
                    btLogin.startAnimation(alphaAnimation);
                }else {
                    //如果不是最后一页，则隐藏按钮，显示小点儿
                    btLogin.setVisibility(View.GONE);
                    point.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
