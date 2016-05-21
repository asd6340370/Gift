package com.example.dllo.gift.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.gift.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverSRVAdapter extends RecyclerView.Adapter<DiscoverSRVAdapter.MyViewHolder> {
    private ArrayList<Integer> datas;
    private Context context;

    public void setDatas(ArrayList<Integer> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public DiscoverSRVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp_discover,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.ivShow.setImageResource(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 :datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivShow;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivShow = (ImageView) itemView.findViewById(R.id.iv_vp_discover);

        }
    }
}
