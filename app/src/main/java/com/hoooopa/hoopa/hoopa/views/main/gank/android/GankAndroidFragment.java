package com.hoooopa.hoopa.hoopa.views.main.gank.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.adapter.gank.GankAndroidAdapter;
import com.hoooopa.hoopa.hoopa.base.BaseFragment;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;
import com.hoooopa.hoopa.hoopa.views.main.gank.detail.GankDetailActivity;
import com.hoooopa.hoopa.hoopa.widget.ScrollLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.List;
import java.util.TooManyListenersException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankAndroidFragment extends BaseFragment implements IGankView.IGankAndroidView{

    private Unbinder unbinder;
    @BindView(R.id.fragment_gank_android_refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.fragment_gank_android_rcv)
    RecyclerView rcvAndroid;

    private GankAndroidPresenter presenter;
    private GankAndroidAdapter adapter;
    private List<AndroidBean> androidData;

    private int count = 10;
    private int page = 1;

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
        presenter.getAndroidDate(count,page);
    }

    private void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvAndroid.setLayoutManager(manager);
        refresh.setHeaderHeight(0);
    }

    private void initLisenter() {
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getAndroidDate(count, ++ page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //目前不需要refresh操作
            }
        });

        rcvAndroid.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = ((RecyclerView.ViewHolder) holder).itemView.findViewById(R.id.item_gank_video_player);
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });
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


    @Override
    public void onAndroidData_Success(final List<AndroidBean> data) {
        if (adapter == null){
            androidData = data;
            adapter = new GankAndroidAdapter(getContext(), androidData, new GankAndroidAdapter.onGankAndroidItemLongClickListener() {
                @Override
                public void onItemLongClickListener(int position) {
                    Toast.makeText(getContext(),data.get(position).getDesc(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemClickListener(int position) {
                    Intent intent = new Intent(getActivity(), GankDetailActivity.class);
                    intent.putExtra("url",data.get(position).getUrl());
                    intent.putExtra("title",data.get(position).getDesc());
                    startActivity(intent);
                }
            });
            rcvAndroid.setAdapter(adapter);
        }else {
            androidData.addAll(data);
        }
        adapter.notifyDataSetChanged();
        refresh.finishLoadMore();
    }

    @Override
    public void onAndroidData_error(String e) {

    }


}
