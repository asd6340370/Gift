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
public class CategoryListViewContentAdapter extends BaseAdapter {
    private CategoryGiftBean giftBean;
    private Context context;

    public void setGiftBean(CategoryGiftBean giftBean) {
        this.giftBean = giftBean;
        notifyDataSetChanged();
    }

    public CategoryListViewContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return giftBean == null ? 0 :giftBean.getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return giftBean == null ? null : giftBean.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewContentViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_category_gift_content_right,parent,false);
            holder = new ListViewContentViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ListViewContentViewHolder) convertView.getTag();
        }
        holder.tvTitleItemListView.setText(giftBean.getData().getCategories().get(position).getName());
        return convertView;
    }

    class ListViewContentViewHolder {

        private final TextView tvTitleItemListView;

        public ListViewContentViewHolder(View view){
            tvTitleItemListView = (TextView) view.findViewById(R.id.tv_item_title_category_gift);

        }
    }
}
