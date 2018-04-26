package com.hoooopa.hoopa.hoopa.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hoooopa.hoopa.hoopa.model.guide.GuideActivity;

import java.util.List;

/**
 * Created by Pray on 2018/4/26.
 */

public class GuideAdapter extends PagerAdapter {

    private List<View> views;
    private Context context;

    public GuideAdapter(Context context , List<View> views){
        this.views = views;
        this.context = context;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ( view == object ) ;
    }
}
