package com.hoooopa.hoopa.hoopa.views.main.gank.girls;

import android.os.Bundle;
import android.view.DragAndDropPermissions;
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

public class GankGirlsFragment extends BaseFragment implements IGankView.IGankGirlsView{

    private Unbinder unbinder;

    @BindView(R.id.tvgirl)
    TextView v;
    private GankGirlsPresenter presenter;

    private int count = 10;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank_girls,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new GankGirlsPresenter(this);
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
        presenter.getGrilsData(count,page);
    }

    private void initViews() {
    }

    private void initLisenter() {
    }


    @Override
    public void onGirlsData_Success(List<AndroidBean> data) {
        v.setText(data.get(0).getDesc());
    }

    @Override
    public void onGirlsData_error(String e) {

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
