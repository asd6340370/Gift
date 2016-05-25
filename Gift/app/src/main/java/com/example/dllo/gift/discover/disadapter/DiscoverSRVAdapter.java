package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.discover.SpecialListBean;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverSRVAdapter extends RecyclerView.Adapter<DiscoverSRVAdapter.MyViewHolder> {
    private SpecialListBean datas;
    private Context context;

    public DiscoverSRVAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void getSpecialList (SpecialListBean listBean){
        setDatas(listBean);
    }

    public void setDatas(SpecialListBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_srv_discover,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    String url = datas.getData().getSecondary_banners().get(position).getImage_url();
        Picasso.with(context).load(url).centerCrop().placeholder(R.mipmap.ic_launcher).fit().into(holder.ivShow);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 :datas.getData().getSecondary_banners().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivShow;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivShow = (ImageView) itemView.findViewById(R.id.iv_srv_discover);

        }
    }

    public void unregister(){
        EventBus.getDefault().unregister(this);
    }
}
