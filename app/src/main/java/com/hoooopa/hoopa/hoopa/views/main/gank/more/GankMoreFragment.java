package com.hoooopa.hoopa.hoopa.views.main.gank.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.gank.GankMoreAdapter;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;
import com.hoooopa.hoopa.hoopa.views.main.gank.detail.GankDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankMoreFragment extends BaseFragment implements IGankView.IGankMoreView{

    private Unbinder unbinder;

    @BindView(R.id.fragment_gank_all_refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.fragment_gank_all_rcv)
    RecyclerView rcvAll;

    private GankMorePresenter presenter= new GankMorePresenter(this);
    private GankMoreAdapter adapter;
    private List<AndroidBean> data;

    private int count = 10;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_gank_all,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initViews();
        initLisenter();
    }

    @Override
    protected void onVisible() {
        presenter.getMoreData(count,page);
    }

    private void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvAll.setLayoutManager(manager);
        refresh.setHeaderHeight(0);
    }

    private void initLisenter() {
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getMoreData(count, ++ page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //目前不需要refresh操作
            }
        });

    }

    @Override
    public void onMoreData_Success(final List<AndroidBean> moreData) {
        if (adapter == null){
            data = moreData;
            adapter = new GankMoreAdapter(getContext(), data, new GankMoreAdapter.onGankMoreItemLongClickListener() {
                @Override
                public void onItemLongClickListener(int position) {
                    Toast.makeText(getContext(),moreData.get(position).getDesc(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemClickListener(int position) {
                    Intent intent = new Intent(getActivity(), GankDetailActivity.class);
                    intent.putExtra("url",moreData.get(position).getUrl());
                    intent.putExtra("title",moreData.get(position).getDesc());
                    startActivity(intent);
                }
            });
            rcvAll.setAdapter(adapter);
        }else {
            data.addAll(moreData);
        }
        adapter.notifyDataSetChanged();
        refresh.finishLoadMore();
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
