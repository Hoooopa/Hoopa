package com.hoooopa.hoopa.hoopa.views.main.douban.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hoooopa.hoopa.hoopa.R;
import io.github.hoooopa.hooopa_core.base.BaseFragment;

import io.github.hoooopa.hooopa_core.bean.douban.content.Subjects;
import com.hoooopa.hoopa.hoopa.views.main.douban.IDoubanView;
import com.hoooopa.hoopa.hoopa.views.main.douban.moviedetail.MovieDetailActivity;
import io.github.hoooopa.hooopa_core.widget.GlideImageLoader;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pray on 2018/4/27.
 */

public class DoubanMainFragment extends BaseFragment implements IDoubanView.IDoubanMainView {

    private Unbinder unbinder;

    @BindView(R.id.fragment_douban_banner) Banner bannerDouban;
    @BindView(R.id.fragment_douban_gv_coming) GridView gvComing;
    @BindView(R.id.fragment_douban_gv_doubantop) GridView gvDouban;
    @BindView(R.id.fragment_douban_rcv_save) RecyclerView rcvSave;
    @BindView(R.id.fragment_douban_edt_search) EditText edtSearch;
    @BindView(R.id.fragment_douban_tv_change) TextView tvChange;

    private DoubanMainPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_douban,container,false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new DoubanMainPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initViews();
        initListener();
    }


    private void initViews(){
        /**
         * banner的一系列操作
         * 还没加入数据
         */
        bannerDouban.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        bannerDouban.setImageLoader(new GlideImageLoader());
        bannerDouban.setBannerAnimation(Transformer.Default);
        bannerDouban.isAutoPlay(true);
        bannerDouban.setDelayTime(3500);
        bannerDouban.setIndicatorGravity(BannerConfig.RIGHT);
        bannerDouban.setBackgroundResource(R.drawable.douban_banner_default);

        /**
         * 收藏的rcv
         */
        rcvSave.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));
        rcvSave.setNestedScrollingEnabled(false);
    }

    /**
     * 视图可视时进行数据的请求
     */
    @Override
    protected void onVisible() {

        presenter.getBannerData();//轮播图建议6个数据.但是我还是要上10个数据
        presenter.getComingData();//即将上映6个数据
        presenter.getDoubanTopData();
        presenter.getSaveData();//得到sava收藏的数据//按照节省流量的角度考虑，应该把被Save的电影的剧照给保存本地然后之后直接加载本地的图片。但是那个略麻烦，等以后再做

    }

    private void initListener() {
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "跳转到搜索界面", Toast.LENGTH_SHORT).show();
            }
        });
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDoubanTopData();
            }
        });
    }


    /**
     * 设置轮播图的操作
     * @param bannerID
     * @param bannerTitle
     * @param bannerImg
     */
    @Override
    public void onBannerData_Success(final List<String> bannerID, List<String> bannerTitle, List<String> bannerImg) {
        bannerDouban.setBannerTitles(bannerTitle);
        bannerDouban.setImages(bannerImg);
        bannerDouban.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                intent.putExtra("id",bannerID.get(position));
                startActivity(intent);
            }
        });
        bannerDouban.start();//开始你的轮播
    }

    /**
     * 一系列对“即将上映coming”的操作
     */
    @Override
    public void onComing_Success(List<Subjects> subjects) { //返回Subject直接用于Rcv。然后点击详情的时候也直接传这个Subject.get(i)就好
        GVAdapter gvAdapter = new GVAdapter( subjects,getContext(), new GVAdapter.OnClickListener() {
            @Override
            public void onClick(Subjects subjects) {

            }
        });
        gvComing.setAdapter(gvAdapter);
    }


    private GVAdapter adapter ;
    private List<Subjects> doubanSubjects = new ArrayList<>();

    @Override
    public void onDoubanTop_Success(List<Subjects> subjects) {
        doubanSubjects.clear();
        doubanSubjects.addAll(subjects);
        if (adapter == null){
            adapter = new GVAdapter(doubanSubjects, getContext(), new GVAdapter.OnClickListener() {
                @Override
                public void onClick(Subjects subjects) {

                }
            });
            gvDouban.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onUsa_Success(List<Subjects> subjects) {

//        DoubanUsaAdapter adapter = new DoubanUsaAdapter(getContext(), subjects, new DoubanUsaAdapter.onComingItemClickListener() {
//            @Override
//            public void onItemClickListener(Subjects subjects) {
//
//            }
//        });
//
//        rcvUsa.setAdapter(adapter);
    }

    /**
     * 在收藏的时候让用户选择 优先观看，稍后再看，先记着等状态    所以item需要有改变优先级、删除等操作
     * @param subjects
     */
    @Override
    public void onSaveData_Got(List<Subjects> subjects) {

        DoubanSaveAdapter saveAdapter = new DoubanSaveAdapter(getContext(),subjects);
        rcvSave.setAdapter(saveAdapter);
    }

    @Override
    public void onDoubanTop_Faild(String error) {

    }

    @Override
    public void onUsa_Faild(String error) {

    }

    @Override
    public void onComing_Failed(String error) {

    }

    @Override
    public void onBannerData_Failed(String error) {

    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter = null;
    }
}

class GVAdapter extends BaseAdapter{

    private List<Subjects> subjects;
    private Context context;
    private OnClickListener onClickListener;

    GVAdapter(List<Subjects> subjects, Context context, OnClickListener onClickListener) {
        this.subjects = subjects;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onClick(Subjects subjects);
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_douban_coming,parent,false);
        }else {
            view = convertView;
        }
        ImageView imageView = view.findViewById(R.id.item_douban_iv_thumbnail);
        TextView tv = view.findViewById(R.id.item_douban_tv_title);
        if (subjects != null){
            Picasso.get().load(subjects.get(position).images.small).into(imageView);
            tv.setText(subjects.get(position).title);
        }

        return view;
    }
}

class DoubanUsaAdapter extends RecyclerView.Adapter<DoubanUsaAdapter.UsaRcvViewHolder> {

    private Context context;
    private List<Subjects> subjects;
    private  onComingItemClickListener onComingItemClickListener ;


    public DoubanUsaAdapter(Context context, List<Subjects> subjects, onComingItemClickListener listener){
        this.subjects = subjects;
        this.context = context;
        this.onComingItemClickListener = listener;
    }


    @NonNull
    @Override
    public UsaRcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_douban_coming,parent,false);
        UsaRcvViewHolder holder = new UsaRcvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsaRcvViewHolder holder, final int position) {
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

    public class UsaRcvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_douban_iv_thumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.item_douban_tv_title)
        TextView tvTitle;

        private int position;

        public UsaRcvViewHolder(View itemView) {
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

class DoubanSaveAdapter extends RecyclerView.Adapter<DoubanSaveAdapter.SaveRcvViewHolder>{

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
