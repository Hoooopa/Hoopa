package com.hoooopa.hoopa.hoopa.adapter.gank;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;

import java.util.List;

public class GankVideoAdapter extends RecyclerView.Adapter<GankVideoAdapter.VideoRcvViewHolder>{

    private Context context;
    private List<AndroidBean> data;

    public GankVideoAdapter(Context context, List<AndroidBean> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GankVideoAdapter.VideoRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gank_video,parent,false);
        VideoRcvViewHolder holder = new VideoRcvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GankVideoAdapter.VideoRcvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VideoRcvViewHolder extends RecyclerView.ViewHolder {

        public VideoRcvViewHolder(View itemView) {
            super(itemView);
        }
    }
}
