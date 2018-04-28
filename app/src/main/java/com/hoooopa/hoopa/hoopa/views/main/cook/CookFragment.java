package com.hoooopa.hoopa.hoopa.views.main.cook;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.base.BasePresenter;

import java.lang.ref.PhantomReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class CookFragment extends BaseFragment implements ICookView {

    private Unbinder unbinder;

    private CookPresenter presenter;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cook,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new CookPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews(){
        presenter.getBannerAndRcvData(true);
    }



    @Override
    public void onQueryCookData_Start() {

    }

    @Override
    public void onQueryCookData_ReStart() {

    }

    @Override
    public void onQueryCookData_Success(String bannerData, String rcvData) {
        tv1.setText(bannerData);
        tv2.setText(rcvData);
    }

    @Override
    public void onBannerAndRcv_Failure(String data) {
        presenter.getBannerAndRcvData(false);
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
