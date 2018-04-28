package com.hoooopa.hoopa.hoopa.views.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.HoopaApplication;
import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.views.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class HomeFragment extends BaseFragment implements IHomeView {

    private Unbinder unbinder;

    private HomePresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new HomePresenter();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }


    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
