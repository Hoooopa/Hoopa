package com.hoooopa.hoopa.hoopa.adapter.douban;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.doubanbean.content.Subjects;

import java.util.List;

import butterknife.ButterKnife;

public class DoubanSaveAdapter extends RecyclerView.Adapter<DoubanSaveAdapter.SaveRcvViewHolder>{

    private Context context;
    private List<Subjects> subjects;

    public DoubanSaveAdapter(Context context, List<Subjects> subjects){
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public DoubanSaveAdapter.SaveRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_douban_save,parent,false);
        SaveRcvViewHolder holder = new SaveRcvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoubanSaveAdapter.SaveRcvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
       // return subjects.size();
        return 10;
    }

    public class SaveRcvViewHolder extends RecyclerView.ViewHolder {


        public SaveRcvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
