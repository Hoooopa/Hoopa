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

public class GuidePresenter extends BasePresenter<IGuideView> {


    private int[] imgList1 = {R.drawable.iv_spalsh_bg1, R.drawable.iv_spalsh_bg2, R.drawable.iv_spalsh_bg4, R.drawable.iv_spalsh_bg3};//清晨
    private int[] imgList2 = {R.drawable.iv_spalsh_bg2, R.drawable.iv_spalsh_bg4, R.drawable.iv_spalsh_bg3, R.drawable.iv_spalsh_bg1};//白天
    private int[] imgList3 = {R.drawable.iv_spalsh_bg4, R.drawable.iv_spalsh_bg3, R.drawable.iv_spalsh_bg1, R.drawable.iv_spalsh_bg2};//傍晚
    private int[] imgList4 = {R.drawable.iv_spalsh_bg3, R.drawable.iv_spalsh_bg1, R.drawable.iv_spalsh_bg2, R.drawable.iv_spalsh_bg4};//夜晚
    private int[] imgList ;     //选择用上述哪个list

    private List<View> mImageView;   //该List<View> 返回给Activity，用于ViewPager的Adapter
    private IGuideView view;     //实现接口
    private Context context;     //上下文，用于行成ImageView并加入到List<View> mImageVIiew内部


    public GuidePresenter(IGuideView view , Context context){
        this.view = view;
        this.context = context;
    }


    /**
     * 把int的图片资源转化为ImageView添加到mImageView内
     * 然后调用接口方法将mImageView返回给GuideActivity用于ViewPager
     */
    public void loadVpData(){
        mImageView = new ArrayList<>();
        imgList = imgListSetByHour();
        for (int i = 0 ; i < imgList.length ; i++ ){
            //转化资源，形成List<View>
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imgList[i]);
            mImageView.add(imageView);
        }
        //返回数据
        view.initVp(mImageView,mImageView.size());
    }


    /**
     * 根据时间来选择哪组图片
     * @return
     */
    private int[] imgListSetByHour(){
        //得到Hour
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        //判断并返回图片组
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
