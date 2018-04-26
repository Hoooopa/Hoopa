package com.hoooopa.hoopa.hoopa.model.guide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.constants.Constants;
import com.hoooopa.hoopa.hoopa.model.base.BaseActivity;
import com.hoooopa.hoopa.hoopa.model.home.MainActivity;

import java.lang.ref.WeakReference;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Pray on 2018/4/25.
 */

public class SpalshActivity extends BaseActivity {

    @BindView(R.id.actvity_spalsh_iv)
    ImageView iv;

    private int durationKeepTime = 2500 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);
        ButterKnife.bind(this);

        setIv();
        startAnimation();
    }

    /**
     * 根据当前时间设置展示的图片
     */
    private void setIv(){
        //获取当前的小时
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        //根据时间设置图片
        if (6 < hour & hour <= 9 ){
            iv.setImageResource(R.drawable.iv_spalsh_bg1);
        }else if (9 < hour & hour <= 17 ){
            iv.setImageResource(R.drawable.iv_spalsh_bg2);
        }else if (17 < hour & hour <=21){
            iv.setImageResource(R.drawable.iv_spalsh_bg3);
        }else {
            iv.setImageResource(R.drawable.iv_spalsh_bg4);
        }
    }

    /**
     * 动画
     */
    private void startAnimation(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.4f, 1.0f, 1.4f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(durationKeepTime);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(Constants.FIRST_INSTALL_APP){
                    Constants.FIRST_INSTALL_APP = false;
                    mHandler.sendEmptyMessageDelayed(0,3000);
                }else {
                    mHandler.sendEmptyMessageDelayed(1,3000);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv.startAnimation(scaleAnimation);
    }

    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0 :startActivity(new Intent(SpalshActivity.this,GuideActivity.class));
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        finish();
                        break;
                case 1 :startActivity(new Intent(SpalshActivity.this, MainActivity.class));
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        finish();
                        break;
                default:break;
            }
            super.handleMessage(msg);
        }
    };

}
