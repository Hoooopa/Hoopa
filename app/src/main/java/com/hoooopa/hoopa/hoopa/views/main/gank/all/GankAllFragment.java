package com.hoooopa.hoopa.hoopa.views.main.gank.all;

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

public class GankAllFragment extends BaseFragment implements IGankView.IGankAllView{

    private Unbinder unbinder;

    @BindView(R.id.testalltv)
    TextView test;

    private GankAllPresenter presenter;



    private int count = 10;
    private int page = 1;

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
        initData();
        initViews();
        initLisenter();
    }

    private void initData(){
        presenter.getIosData(count,page);
        presenter.getMoreData(count,page);
        presenter.getWebData(count,page);
    }

    private void initViews() {
    }

    private void initLisenter() {
    }

    @Override
    public void onIosData_Success(List<AndroidBean> iosData) {
        test.setText(iosData.get(0).getDesc());
    }

    @Override
    public void onIosData_Error(String e) {

    }

    @Override
    public void onWebData_Success(List<AndroidBean> webData) {

    }

    @Override
    public void onWebData_Error(String e) {

    }

    @Override
    public void onMoreData_Success(List<AndroidBean> moreData) {

    }

    @Override
    public void onMoreData_Error(String e) {

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
