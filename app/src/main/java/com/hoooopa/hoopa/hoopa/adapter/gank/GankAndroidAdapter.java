package com.hoooopa.hoopa.hoopa.adapter.gank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankAndroidAdapter extends RecyclerView.Adapter<GankAndroidAdapter.AndroidRcvViewHolder>{

    private List<AndroidBean> data;
    private Context context;

    public GankAndroidAdapter(Context context , List<AndroidBean> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AndroidRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gank_code,parent,false);
        AndroidRcvViewHolder holder = new AndroidRcvViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AndroidRcvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_gank_code_desc) TextView desc;
        @BindView(R.id.item_gank_code_name_and_type) TextView nameAndType;
        @BindView(R.id.item_gank_code_img) ImageView img;
        @BindView(R.id.item_gank_code_time) TextView time;

        public AndroidRcvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull GankAndroidAdapter.AndroidRcvViewHolder holder, int position) {
        holder.desc.setText(data.get(position).getDesc());
        if (data.get(position).getImages() == null){
            holder.img.setVisibility(View.GONE);
        }else {
            Picasso.get().load(data.get(position).getImages().get(0)).into(holder.img);
        }
        holder.nameAndType.setText(data.get(position).getWho() + "·" +data.get(position).getType());
        String time1 = data.get(position).getPublishedAt().split("T")[0];
        String time2 = time1.split("-")[1] + "-" + time1.split("-")[2];
        holder.time.setText(time2);
    }

}
