package com.hoooopa.hoopa.hoopa.adapter.gank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.gankbean.AndroidBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankMoreAdapter extends RecyclerView.Adapter<GankMoreAdapter.GankMoreViewHolder> {

    private List<AndroidBean> data;
    private Context context;
    private GankMoreAdapter.onGankMoreItemLongClickListener onLongClickListener;

    public GankMoreAdapter(Context context, List<AndroidBean> data,onGankMoreItemLongClickListener onLongClickListener){
        this.context = context;
        this.data = data;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public GankMoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gank_code,parent,false);
        GankMoreViewHolder holder = new GankMoreViewHolder(view);
        return holder;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GankMoreViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_gank_code_desc)
        TextView desc;
        @BindView(R.id.item_gank_code_name_and_type) TextView nameAndType;
        @BindView(R.id.item_gank_code_img)
        ImageView img;
        @BindView(R.id.item_gank_code_time) TextView time;
        @BindView(R.id.item_gank_code_ll)
        LinearLayout ll;

        public GankMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull GankMoreViewHolder holder, final int position) {
        holder.desc.setText(data.get(position).getDesc());
        if (data.get(position).getImages() == null){
            holder.img.setVisibility(View.GONE);
        }else {
            try {
                Picasso.get().load(data.get(position).getImages().get(0)).into(holder.img);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        holder.nameAndType.setText(data.get(position).getWho() + "·" +data.get(position).getType());
        String time1 = data.get(position).getPublishedAt().split("T")[0];
        String time2 = time1.split("-")[1] + "-" + time1.split("-")[2];
        holder.time.setText(time2);
        holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onLongClickListener.onItemLongClickListener(position);
                return true;
            }
        });
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLongClickListener.onItemClickListener(position);
            }
        });
    }

    public interface onGankMoreItemLongClickListener{
        void onItemLongClickListener(int position);
        void onItemClickListener(int position);
    }

}
