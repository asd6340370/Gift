package com.example.dllo.gift.details.detailsadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.category.categorybean.CategoryRaiderSpecialRVBean;
import com.example.dllo.gift.discover.disbean.SpecialListHeaderBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/4.
 */
public class DetailsCategoryRaiderSpecialallAdapter extends BaseAdapter{
    CategoryRaiderSpecialRVBean specialRVBean;
    private Context context;

    public void setSpecialRVBean(CategoryRaiderSpecialRVBean specialRVBean) {
        this.specialRVBean = specialRVBean;
        notifyDataSetChanged();
    }

    public DetailsCategoryRaiderSpecialallAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return specialRVBean == null ? 0 :specialRVBean.getData().getCollections().size();
    }

    @Override
    public Object getItem(int position) {
        return specialRVBean == null ? null : specialRVBean.getData().getCollections().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpecialAllViewHolder holder;
        if (convertView == null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_details_category_raider_specialall,parent,false);
            holder = new SpecialAllViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SpecialAllViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(specialRVBean.getData().getCollections().get(position).getTitle());
        holder.tvSubTitle.setText(specialRVBean.getData().getCollections().get(position).getSubtitle());
        Picasso.with(context).load(specialRVBean.getData().getCollections().get(position).getCover_image_url())
                .transform(new RoundRect(20)).centerCrop().fit().into(holder.ivShow);
        return convertView;
    }
    class SpecialAllViewHolder {

        private final TextView tvTitle;
        private final TextView tvSubTitle;
        private final ImageView ivShow;

        public SpecialAllViewHolder(View view){
            tvTitle = (TextView) view.findViewById(R.id.tv_item_title_category_raider_specialall);
            tvSubTitle = (TextView) view.findViewById(R.id.tv_item_subtitle_category_raider_specialall);
            ivShow = (ImageView) view.findViewById(R.id.iv_detials_category_raider_specialall);
        }

    }
}
