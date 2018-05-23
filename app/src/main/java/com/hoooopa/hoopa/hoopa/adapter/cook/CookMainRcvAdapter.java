package com.hoooopa.hoopa.hoopa.adapter.cook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.bean.cookbean.CookBase;
import com.hoooopa.hoopa.hoopa.views.main.cook.detail.DetailActivity;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pray on 2018/4/30.
 */

public class CookMainRcvAdapter extends RecyclerView.Adapter<CookMainRcvAdapter.CookMainRcvViewHolder> {

    private Context context;
    private List<CookBase> data;
    private RecyclerView rcv;
    private onCookRcvClickListener listener;

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;

    public CookMainRcvAdapter(Context context , List<CookBase> data , RecyclerView rcv , onCookRcvClickListener listener) {
        this.context = context;
        this.data = data;
        this.rcv = rcv;
        this.listener = listener;
    }

    public class CookMainRcvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.item_cook_main_iv_thumbnail) SimpleDraweeView thumbnail;
        @BindView(R.id.item_cook_main_tv_title) TextView title;
        @BindView(R.id.item_cook_main_tv_content_1) TextView content1;
        @BindView(R.id.item_cook_main_tv_content_2) TextView content2;
        @BindView(R.id.item_cook_main_tv_meterial) TextView material;
        @BindView(R.id.item_cook_main_bt_detail) Button detail;
        @BindView(R.id.item_cook_main_bt_save) Button save;
        @BindView(R.id.item_cook_main_expandable_layout) ExpandableLayout hideLayout;
        @BindView(R.id.item_cook_main_rl)
        LinearLayout llLayout;

        private int position;

        public CookMainRcvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            hideLayout.setInterpolator(new OvershootInterpolator());
            hideLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
                @Override
                public void onExpansionUpdate(float expansionFraction, int state) {
                    rcv.smoothScrollToPosition(getAdapterPosition());
                }
            });
            RoundingParams params = RoundingParams.fromCornersRadius(50f);
            thumbnail.getHierarchy().setRoundingParams(params);

            llLayout.setOnClickListener(this);
            thumbnail.setOnClickListener(this);
            detail.setOnClickListener(this);
            save.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.item_cook_main_rl:
                    showHideLayout();
                    break;
                case R.id.item_cook_main_iv_thumbnail:
                    listener.onThumbClickListener(data.get(position).getPic());
                    break;
                case R.id.item_cook_main_bt_detail:
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("id",data.get(position).getId());
                    context.startActivity(intent);
                    break;
                case R.id.item_cook_main_bt_save:
                    saveCookInfo();
                    break;
                default:
                    break;
            }
        }

        public void bind(int position){
            this.position = position;
            hideLayout.collapse(false);
        }

        private void showHideLayout(){
            CookMainRcvViewHolder holder = (CookMainRcvViewHolder) rcv.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.hideLayout.collapse();
            }
            if (position == selectedItem){
                selectedItem = UNSELECTED;
            }else {
                hideLayout.expand();
                selectedItem = position;
            }
        }

        private void saveCookInfo(){
            Toast.makeText(context,"保存数据",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 绑定item
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CookMainRcvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cook_main,parent,false);
        CookMainRcvViewHolder holder = new CookMainRcvViewHolder(view);
        return holder;
    }


    /**
     * 把数据填入到item里
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CookMainRcvViewHolder holder,int position) {
        holder.bind(position);
        String m = "";
        for (int i = 0 ; i < data.get(position).getMaterial().size() ; i++){
            m = m + data.get(position).getMaterial().get(i).getMname() + ",";
        }
        Uri uri = Uri.parse(data.get(position).getPic());
        holder.thumbnail.setImageURI(uri);
        holder.title.setText(data.get(position).getName());
        holder.content1.setText("准备时间:"+"  " +data.get(position).getPreparetime()+ "    " + "烹饪时间:" + "  " + data.get(position).getCookingtime() );
        holder.content2.setText(data.get(position).getContent());
        holder.material.setText(m);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * 自定义RCV点击接口
     */
    public interface onCookRcvClickListener{
        void onThumbClickListener(String url);
    }

}
