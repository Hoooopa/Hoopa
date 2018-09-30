package com.hoooopa.hoopa.hoopa.views.main.douban.moviedetail;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Bundle;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Slide;

import android.view.Gravity;
import android.widget.ImageView;

import com.hoooopa.hoopa.hoopa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 电影详情
 */
public class MovieDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData(){

    }

    private void initView(){



    }
}
