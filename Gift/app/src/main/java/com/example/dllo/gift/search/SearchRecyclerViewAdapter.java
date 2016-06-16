package com.example.dllo.gift.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.tools.RecyclerOnClickListener;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHoder> {
    private SearchBean beans;
    private Context context;
    private SearchRecyclerViewOnClickListerner onClickListener;

    public void setOnClickListener(SearchRecyclerViewOnClickListerner onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

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
    public void onBindViewHolder(MyViewHoder holder, final int position) {
        holder.tvName.setText(beans.getData().getHot_words().get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = beans.getData().getHot_words().get(position);
                onClickListener.onItemClick(position,name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.getData().getHot_words().size();
    }


    class MyViewHoder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final LinearLayout linearLayout;

        public MyViewHoder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_recycler_search);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout_item_recycler_secarch);
        }
    }
}
