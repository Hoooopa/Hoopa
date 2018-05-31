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

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.item_douban_coming_iv_thumbnail)
    ImageView img;

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

        Slide slide = new Slide();
        slide.setDuration(500);
        slide.setSlideEdge(Gravity.RIGHT);
        getWindow().setSharedElementEnterTransition(slide);

    }
}
