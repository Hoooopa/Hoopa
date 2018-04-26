package com.hoooopa.hoopa.hoopa.model.guide;

import android.content.Context;
import android.location.GpsSatellite;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.hoooopa.hoopa.hoopa.HoopaApplication;
import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BasePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pray on 2018/4/26.
 */

public class GuidePresenter extends BasePresenter {

    //清晨
    private int[] imgList1 = {R.drawable.iv_spalsh_bg1, R.drawable.iv_spalsh_bg2, R.drawable.iv_spalsh_bg4, R.drawable.iv_spalsh_bg3};
    //白天
    private int[] imgList2 = {R.drawable.iv_spalsh_bg2, R.drawable.iv_spalsh_bg4, R.drawable.iv_spalsh_bg3, R.drawable.iv_spalsh_bg1};
    //傍晚
    private int[] imgList3 = {R.drawable.iv_spalsh_bg4, R.drawable.iv_spalsh_bg3, R.drawable.iv_spalsh_bg1, R.drawable.iv_spalsh_bg2};
    //夜晚
    private int[] imgList4 = {R.drawable.iv_spalsh_bg3, R.drawable.iv_spalsh_bg1, R.drawable.iv_spalsh_bg2, R.drawable.iv_spalsh_bg4};

    private int[] imgList ;

    private List<View> mImageView;

    private IGuideView view;

    private Context context;

    public GuidePresenter(IGuideView view , Context context){
        this.view = view;
        this.context = context;
    }

    public void loadVpData(){
        mImageView = new ArrayList<>();
        imgList = imgListSetByHour();
        for (int i = 0 ; i < imgList.length ; i++ ){
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imgList[i]);
            mImageView.add(imageView);
        }
        view.initVp(mImageView,mImageView.size());

    }

    private int[] imgListSetByHour(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (6 < hour & hour <= 9){
            return imgList1;
        }if (9<hour&hour<=17){
            return imgList2;
        }if (17<hour&hour<=21){
            return imgList3;
        }else {
            return imgList4;
        }
    }

}
