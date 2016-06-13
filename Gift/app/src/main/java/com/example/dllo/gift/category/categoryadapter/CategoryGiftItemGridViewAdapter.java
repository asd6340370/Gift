package com.example.dllo.gift.category.categoryadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.category.categorybean.CategoryGiftBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/1.
 */
public class CategoryGiftItemGridViewAdapter extends BaseAdapter{
    private CategoryGiftBean gridViewBeans;
    private int pos;
    private Context context;

    public void setGridViewBeans(CategoryGiftBean gridViewBeans,int p) {
        this.gridViewBeans = gridViewBeans;
        this.pos = p;
        notifyDataSetChanged();
    }

    public CategoryGiftItemGridViewAdapter(CategoryGiftBean gridViewBeans, int pos, Context context) {
        this.gridViewBeans = gridViewBeans;
        this.pos = pos;
        this.context = context;
    }

    public CategoryGiftItemGridViewAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return gridViewBeans == null ? 0 :gridViewBeans.getData().getCategories().get(pos).getSubcategories().size();
    }

    @Override
    public Object getItem(int position) {
        return gridViewBeans == null ? null : gridViewBeans.getData().getCategories().get(pos).getSubcategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGridViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_category_content,parent,false);
           holder = new MyGridViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyGridViewHolder) convertView.getTag();
        }
//        Log.d("CategoryItemGridViewAda", "pos:" + pos);
        holder.tvShow.setText(gridViewBeans.getData().getCategories().get(pos).getSubcategories().get(position).getName());
        Picasso.with(context).load(gridViewBeans.getData().getCategories().get(pos).getSubcategories().get(position).getIcon_url())
                .centerCrop().fit().into(holder.ivShow);
        return convertView;
    }

    class MyGridViewHolder{

        private final ImageView ivShow;
        private final TextView tvShow;

        public MyGridViewHolder(View view){
            ivShow = (ImageView)view.findViewById(R.id.iv_item_gridview_category_content);
            tvShow = (TextView) view.findViewById(R.id.tv_item_title_gridview_category_content);
        }

    }

}
