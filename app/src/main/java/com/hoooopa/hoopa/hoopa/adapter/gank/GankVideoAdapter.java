package com.hoooopa.hoopa.hoopa.adapter.gank;

import android.app.LauncherActivity;
import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.squareup.picasso.Picasso;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

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
        TxVideoPlayerController controller = new TxVideoPlayerController(context);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GankVideoAdapter.VideoRcvViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VideoRcvViewHolder extends RecyclerView.ViewHolder {

        private NiceVideoPlayer videoPlayer;
        private TxVideoPlayerController controller;

        public VideoRcvViewHolder(View itemView) {
            super(itemView);
            videoPlayer = (NiceVideoPlayer) itemView.findViewById(R.id.item_gank_video_player);
            ViewGroup.LayoutParams params = videoPlayer.getLayoutParams();
            params.width = itemView.getResources().getDisplayMetrics().widthPixels;
            params.height = (int) (params.width * 9f / 16f );
            videoPlayer.setLayoutParams(params);
        }

        public void setController(TxVideoPlayerController controller){
            this.controller = controller;
            videoPlayer.setController(controller);
        }

        public void bindData(AndroidBean data){
            controller.setTitle(data.getDesc());
            videoPlayer.setUp("http://v9-dy.ixigua.com/a0203a8cc5b2f1268596ea62c678abc7/5b195f12/video/m/2205d8d9fde4d264fe1855909ca133269251157da93000026a09d219352/",null);
        }
    }
}
