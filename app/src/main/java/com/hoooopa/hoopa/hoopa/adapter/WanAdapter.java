package com.hoooopa.hoopa.hoopa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.hoooopa.hooopa_core.bean.wan.Datas;

public class WanAdapter extends RecyclerView.Adapter<WanAdapter.WanViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onImgClick(int position);
        void onLongClick(int position);
    }

    private OnItemClickListener onItemClickListener;
    private List<Datas> datas;
    private Context context;

    public WanAdapter(List<Datas> datas, Context context, OnItemClickListener onItemClickListener) {
        this.datas = datas;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wan,parent,false);
        WanViewHolder holder = new WanViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WanViewHolder holder, final int position) {

        Picasso.get().load(datas.get(position).getEnvelopePic()).into(holder.img);
        holder.tvDesc.setText(datas.get(position).getDesc());
        holder.tvTime.setText(datas.get(position).getNiceDate());
        holder.tvTitle.setText(datas.get(position).getTitle());
        holder.tvWriter.setText(datas.get(position).getAuthor());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

        holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class WanViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_wan_ll) LinearLayout ll;
        @BindView(R.id.item_wan_desc) TextView tvDesc;
        @BindView(R.id.item_wan_img) ImageView img;
        @BindView(R.id.item_wan_time) TextView tvTime;
        @BindView(R.id.item_wan_title) TextView tvTitle;
        @BindView(R.id.item_wan_writer) TextView tvWriter;

        public WanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
