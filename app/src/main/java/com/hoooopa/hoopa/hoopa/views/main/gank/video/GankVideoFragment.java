package com.hoooopa.hoopa.hoopa.views.main.gank.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/6/1.
 */

public class GankVideoFragment extends BaseFragment implements IGankVideoView {


    private Unbinder unbinder;

    private GankVideoPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank_girls,container,false);
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

    private void initLisenter() {
    }

    private void initViews() {
    }

    private void initData(){

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
