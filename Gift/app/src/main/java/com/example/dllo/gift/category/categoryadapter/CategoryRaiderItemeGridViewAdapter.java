package com.example.dllo.gift.category.categoryadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.category.categorybean.CategoryRaiderBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/2.
 */
public class CategoryRaiderItemeGridViewAdapter extends BaseAdapter {
    private Context context;
    private CategoryRaiderBean raiderBeans;
    private int pos;

    public CategoryRaiderItemeGridViewAdapter(Context context, CategoryRaiderBean raiderBeans, int pos) {
        this.context = context;
        this.raiderBeans = raiderBeans;
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return raiderBeans == null ? 0 :raiderBeans.getData().getChannel_groups().get(pos).getChannels().size();
    }

    @Override
    public Object getItem(int position) {
        return raiderBeans == null ? null : raiderBeans.getData().getChannel_groups().get(pos).getChannels().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyRaiderGridViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_category_raider,parent,false);
            holder = new MyRaiderGridViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyRaiderGridViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(raiderBeans.getData().getChannel_groups().get(pos).getChannels().get(position).getIcon_url())
                .centerCrop().fit().into(holder.ivShow);
        holder.tvShow.setText(raiderBeans.getData().getChannel_groups().get(pos).getChannels().get(position).getName());
        return convertView;
    }
    class MyRaiderGridViewHolder {

        private final ImageView ivShow;
        private final TextView tvShow;

        public MyRaiderGridViewHolder(View view){
            ivShow = (ImageView) view.findViewById(R.id.iv_item_gridview_category_raider);
            tvShow = (TextView) view.findViewById(R.id.tv_item_title_gridview_category_raider);
        }
    }
}
