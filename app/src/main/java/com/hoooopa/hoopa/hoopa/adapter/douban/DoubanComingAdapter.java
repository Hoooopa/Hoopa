package com.hoooopa.hoopa.hoopa.adapter.douban;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.content.Subjects;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoubanComingAdapter extends RecyclerView.Adapter<DoubanComingAdapter.ComingRcvViewHolder> {

    private Context context;
    private List<Subjects> subjects;
    private  onComingItemClickListener onComingItemClickListener ;


    public DoubanComingAdapter(Context context, List<Subjects> subjects, onComingItemClickListener listener){
        this.subjects = subjects;
        this.context = context;
        this.onComingItemClickListener = listener;
    }


    @NonNull
    @Override
    public ComingRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_douban_coming,parent,false);
        ComingRcvViewHolder holder = new ComingRcvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComingRcvViewHolder holder, final int position) {
        holder.bind(position);
        Picasso.get().load(subjects.get(position).images.small).into(holder.ivThumbnail);
        holder.tvTitle.setText(subjects.get(position).title);
        holder.ivThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onComingItemClickListener.onItemClickListener(subjects.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ComingRcvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_douban_coming_iv_thumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.item_douban_coming_tv_title)
        TextView tvTitle;

        private int position;

        public ComingRcvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(int position){
            this.position = position;
        }
    }


    public interface onComingItemClickListener{
        void onItemClickListener(Subjects subjects);
    }


}
