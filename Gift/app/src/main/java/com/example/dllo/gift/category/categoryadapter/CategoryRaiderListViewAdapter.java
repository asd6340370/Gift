package com.example.dllo.gift.category.categoryadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.category.categorybean.CategoryRaiderBean;
import com.example.dllo.gift.tools.NoScrollGridView;

/**
 * Created by dllo on 16/6/2.
 */
public class CategoryRaiderListViewAdapter extends BaseAdapter {
    private Context context;
    private CategoryRaiderBean raiderBeans;

    public void setRaiderBeans(CategoryRaiderBean raiderBeans) {
        this.raiderBeans = raiderBeans;
        notifyDataSetChanged();
    }

    public CategoryRaiderListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return raiderBeans == null ? 0 :raiderBeans.getData().getChannel_groups().size();
    }

    @Override
    public Object getItem(int position) {
        return raiderBeans == null ? null : raiderBeans.getData().getChannel_groups().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyRaiderListViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_category_raider,parent,false);
            holder = new MyRaiderListViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyRaiderListViewHolder) convertView.getTag();
        }

        holder.ivTitle.setText(raiderBeans.getData().getChannel_groups().get(position).getName());

        CategoryRaiderItemeGridViewAdapter gridViewAdapter = new CategoryRaiderItemeGridViewAdapter(context,raiderBeans,position);
        holder.gridViewRaider.setAdapter(gridViewAdapter);
        return convertView;
    }

    class MyRaiderListViewHolder {

        private final TextView ivTitle;
        private final NoScrollGridView gridViewRaider;

        public MyRaiderListViewHolder(View view){
            ivTitle = (TextView) view.findViewById(R.id.tv_item_title_category_raider);
            gridViewRaider = (NoScrollGridView) view.findViewById(R.id.item_gridview_category_raider);

        }
    }
}
