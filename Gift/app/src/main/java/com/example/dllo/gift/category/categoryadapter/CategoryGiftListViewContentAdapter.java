package com.example.dllo.gift.category.categoryadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.category.categorybean.CategoryGiftBean;
import com.example.dllo.gift.details.DetailsCategoryGiftActivity;

/**
 * Created by dllo on 16/6/1.
 */
public class CategoryGiftListViewContentAdapter extends BaseAdapter  {
    private CategoryGiftBean giftBean;
    private Context context;
    private CategoryGiftItemGridViewAdapter gridViewAdapter;


    public void setGiftBean(CategoryGiftBean giftBean) {
        this.giftBean = giftBean;
        notifyDataSetChanged();
    }

    public CategoryGiftListViewContentAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return giftBean == null ? 0 : giftBean.getData().getCategories().size();
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
        final ListViewContentViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_category_gift_content_right, parent, false);
            holder = new ListViewContentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ListViewContentViewHolder) convertView.getTag();
        }
        holder.tvTitleItemListView.setText(giftBean.getData().getCategories().get(position).getName());
        //初始化gridview适配
//        Log.d("CategoryListViewContent", "position:" + position);
        gridViewAdapter = new CategoryGiftItemGridViewAdapter(giftBean, position, context);
        holder.gridView.setAdapter(gridViewAdapter);
        holder.pos = position;
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("CategoryGiftListViewCon", "giftBean" + giftBean.getData().getCategories().get(holder.pos).getSubcategories().get(position).getId());
//                Log.d("CategoryGiftListViewCon", "giftBean" + giftBean.getData().getCategories().get(holder.pos).getSubcategories().get(position).getParent_id());
//                Log.d("CategoryGiftListViewCon", giftBean.getData().getCategories().get(holder.pos).getSubcategories().get(position).getName());
                String urlId = String.valueOf(giftBean.getData().getCategories().get(holder.pos).getSubcategories().get(position).getId());
                String titleName = giftBean.getData().getCategories().get(holder.pos).getSubcategories().get(position).getName();
                Intent intent = new Intent(context, DetailsCategoryGiftActivity.class);
                intent.putExtra("id",urlId);
                intent.putExtra("titleName",titleName);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    class ListViewContentViewHolder {
        private  TextView tvTitleItemListView;
        private  GridView gridView;
        private int pos;
        public ListViewContentViewHolder(View view) {
            tvTitleItemListView = (TextView) view.findViewById(R.id.tv_item_title_category_gift);
            gridView = (GridView) view.findViewById(R.id.item_gridview_category_gift_content);

        }
    }
}
