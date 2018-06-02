package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankAndroidFragment extends BaseFragment implements IGankAndroidView {

    private Unbinder unbinder;

    private GankAndroidPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank_android,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new GankAndroidPresenter(this);
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
        presenter.getAndroidDate();
    }

    private void initViews() {
    }

    private void initLisenter() {
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
