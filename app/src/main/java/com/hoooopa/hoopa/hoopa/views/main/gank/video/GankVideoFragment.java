package com.hoooopa.hoopa.hoopa.views.main.gank.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/6/1.
 */

public class GankVideoFragment extends BaseFragment implements IGankView.IGankVideoView {


    private Unbinder unbinder;

    @BindView(R.id.tstvideo)
    TextView tst;

    private GankVideoPresenter presenter;



    private int count = 10;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank_video,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new GankVideoPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initData();
        initViews();
        initLisenter();
    }

    private void initData(){
        presenter.getVideoData(count,page);
    }

    private void initLisenter() {
    }

    private void initViews() {
    }




    @Override
    public void onVideoData_Success(List<AndroidBean> data) {
        tst.setText(data.get(0).getDesc());
    }

    @Override
    public void onVideoData_error(String e) {

    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //由于做不到basefragment，所以只有手动了
        presenter = null;
        unbinder.unbind();
    }
}
