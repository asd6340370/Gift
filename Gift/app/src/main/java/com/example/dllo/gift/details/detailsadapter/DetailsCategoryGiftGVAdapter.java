package com.example.dllo.gift.details.detailsadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.details.detailsbean.DetailsCategoryGiftBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/19.
 */
public class DetailsCategoryGiftGVAdapter extends BaseAdapter {
    private DetailsCategoryGiftBean datas;
    private Context context;

    public void setDatas(DetailsCategoryGiftBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public DetailsCategoryGiftGVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_hot, parent, false);
             viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //毕加索解析图片
        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url())
              .centerCrop().fit().into(viewHolder.ivShow);

        viewHolder.tvTitle.setText(datas.getData().getItems().get(position).getName());
        viewHolder.tvPrice.setText(datas.getData().getItems().get(position).getPrice());
        viewHolder.tvLikes.setText(String.valueOf(datas.getData().getItems().get(position).getFavorites_count()));


        return convertView;
    }


    class ViewHolder {

        private final TextView tvTitle;
        private final ImageView ivShow;
        private final TextView tvPrice;
        private final TextView tvLikes;

        public ViewHolder (View itemView){
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_gv_hot);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_gv_hot);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_gv_hot);
            tvLikes = (TextView) itemView.findViewById(R.id.tv_like_gv_hot);

        }

    }
}
