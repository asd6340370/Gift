package com.example.dllo.gift.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHoder> {
    private SearchBean beans;
    private Context context;

    public void setBeans(SearchBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    public SearchRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_search,parent,false);
        MyViewHoder hoder = new MyViewHoder(view);

        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.tvName.setText(beans.getData().getHot_words().get(position));
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.getData().getHot_words().size();
    }


    class MyViewHoder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        public MyViewHoder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_recycler_search);
        }
    }
}
