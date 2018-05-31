package com.hoooopa.hoopa.hoopa.views.main.gank.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.views.main.cook.mian.CookPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankAllFragment extends BaseFragment implements IGankAllView{

    private Unbinder unbinder;

    private GankAllPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank_all,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new GankAllPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
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
