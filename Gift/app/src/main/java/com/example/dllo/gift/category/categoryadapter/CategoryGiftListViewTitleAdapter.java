package com.example.dllo.gift.category.categoryadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.category.categorybean.CategoryGiftBean;

/**
 * Created by dllo on 16/6/1.
 */
public class CategoryGiftListViewTitleAdapter extends BaseAdapter {
    private CategoryGiftBean giftBean;
    private int mCheckedPosition = 0;

    public void setmCheckedPosition(int mCheckedPosition) {
        this.mCheckedPosition = mCheckedPosition;
        notifyDataSetChanged();
    }






    public void setGiftBean(CategoryGiftBean giftBean) {
        this.giftBean = giftBean;
        notifyDataSetChanged();
    }

    private Context context;

    public CategoryGiftListViewTitleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return giftBean == null ? 0 : giftBean.getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return giftBean == null ? null :giftBean.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryListViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_category_gift_title_left,parent,false);
            holder = new CategoryListViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (CategoryListViewHolder) convertView.getTag();
        }

        if (mCheckedPosition == position){
            holder.tvTitleLeft.setSelected(true);
            holder.tvTitleLeftIv.setSelected(true);
        }else {
            holder.tvTitleLeft.setSelected(false);
            holder.tvTitleLeftIv.setSelected(false);
        }


        holder.tvTitleLeft.setText(giftBean.getData().getCategories().get(position).getName());


        return convertView;
    }

    class CategoryListViewHolder {
        private final TextView tvTitleLeftIv;
        private TextView tvTitleLeft;

        public CategoryListViewHolder (View view) {
            tvTitleLeft = (TextView) view.findViewById(R.id.tv_listview_title_left);
            tvTitleLeftIv = (TextView) view.findViewById(R.id.tv_listview_title_left_iv);
        }
    }
}
