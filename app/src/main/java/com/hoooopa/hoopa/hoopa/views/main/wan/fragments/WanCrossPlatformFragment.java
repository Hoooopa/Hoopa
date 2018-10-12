package com.hoooopa.hoopa.hoopa.views.main.wan.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.hoooopa.hooopa_core.base.BaseFragment;

public class WanCrossPlatformFragment extends BaseFragment {


    private Unbinder unbinder;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wan,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected void onVisible() {

    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

}
