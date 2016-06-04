package com.example.dllo.gift.category.categoryadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.tools.RecyclerOnClickListener;
import com.example.dllo.gift.category.categorybean.CategoryRaiderSpecialRVBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/2.
 */
public class CategoryRaiderSpecialRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRaiderSpecialRecyclerViewAdapter.HeaderRecycleViewHolder> {
    private CategoryRaiderSpecialRVBean beans;
    private Context context;
    private RecyclerOnClickListener recyclerOnClickListener;

    public CategoryRaiderSpecialRVBean getBeans() {
        return beans;
    }

    public void setRecyclerOnClickListener(RecyclerOnClickListener recyclerOnClickListener) {
        this.recyclerOnClickListener = recyclerOnClickListener;
    }

    public CategoryRaiderSpecialRecyclerViewAdapter(Context context) {
        EventBus.getDefault().register(this);
        this.context = context;
    }
    @Subscribe
    public void getData(CategoryRaiderSpecialRVBean beans){
        setBeans(beans);
    }

    public void setBeans(CategoryRaiderSpecialRVBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public HeaderRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_header_category_raider,parent,false);
        HeaderRecycleViewHolder holder = new HeaderRecycleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HeaderRecycleViewHolder holder, final int position) {
//        Log.d("CategoryHeaderRecyclerV", beans.getData().getCollections().get(position).getBanner_image_url());
        Picasso.with(context).load(beans.getData().getCollections().get(position).getBanner_image_url())
                .transform(new RoundRect(14)).centerCrop().fit().into(holder.ivShow);
        if (position == 0){
            holder.ivShow.setPadding(30,0,0,0);
        }
        holder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerOnClickListener.onClick(position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.getData().getCollections().size();
    }

    class HeaderRecycleViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivShow;

        public HeaderRecycleViewHolder(View itemView) {
            super(itemView);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_item_header_category_raider);
        }
    }
    public void unregister(){
        EventBus.getDefault().unregister(this);
    }

}
