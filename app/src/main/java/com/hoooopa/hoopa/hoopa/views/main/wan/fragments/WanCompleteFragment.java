package com.hoooopa.hoopa.hoopa.views.main.wan.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.WanAdapter;
import com.hoooopa.hoopa.hoopa.views.main.wan.WanModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.hoooopa.hooopa_core.base.BaseFragment;
import io.github.hoooopa.hooopa_core.bean.wan.Datas;

public class WanCompleteFragment extends BaseFragment {

    private Unbinder unbinder;


    @BindView(R.id.fragment_wan_rcv)
    RecyclerView rcv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wan,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(manager);

    }

    @Override
    protected void onVisible() {
        WanModel.getWanProjectData(new WanModel.OnResultListener() {
            @Override
            public void onSuccess(List<Datas> datas) {
                WanAdapter adapter = new WanAdapter(datas, getContext(), new WanAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onImgClick(int position) {

                    }

                    @Override
                    public void onLongClick(int position) {

                    }
                });
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailed(String msg) {

            }
        });


    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
