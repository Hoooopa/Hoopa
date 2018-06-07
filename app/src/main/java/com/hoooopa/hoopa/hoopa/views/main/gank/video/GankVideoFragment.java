package com.hoooopa.hoopa.hoopa.views.main.gank.video;

import android.os.Bundle;
import android.print.PrinterId;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.gank.GankVideoAdapter;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/6/1.
 */

public class GankVideoFragment extends BaseFragment implements IGankView.IGankVideoView {


    private Unbinder unbinder;

    @BindView(R.id.fragment_gank_video_rcv)
    RecyclerView rcvVideo;

    private GankVideoPresenter presenter;
    private GankVideoAdapter adapter;


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

    private void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvVideo.setLayoutManager(manager);

    }

    private void initLisenter() {
    }






    @Override
    public void onVideoData_Success(List<AndroidBean> data) {
        adapter = new GankVideoAdapter(getContext(),data);
        rcvVideo.setAdapter(adapter);
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

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
}
