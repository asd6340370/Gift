package com.example.dllo.gift.details.detailsadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.details.detailsbean.DetailsSpecialBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/4.
 */
public class DetailsSpecialAdapter extends BaseAdapter {
    private DetailsSpecialBean specialBean;
    private Context context;

    public DetailsSpecialAdapter(Context context) {
        this.context = context;
    }

    public void setSpecialBean(DetailsSpecialBean specialBean) {
        this.specialBean = specialBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return specialBean == null ? 0 : specialBean.getData().getPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return specialBean == null ? null : specialBean.getData().getPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final DetailsSpecialViewHolder holder;
        if (convertView == null) {
            //复用item_listview_discover 行布局
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_discover, parent, false);
            holder = new DetailsSpecialViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (DetailsSpecialViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(specialBean.getData().getPosts().get(position).getCover_image_url())
                .transform(new RoundRect(20)).centerCrop().fit().into(holder.ivShow);
        holder.tvTitle.setText(specialBean.getData().getPosts().get(position).getTitle());
        holder.checkBoxLikecount.setText(String.valueOf(specialBean.getData().getPosts().get(position).getLikes_count()));
        holder.checkBoxLikecount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
               specialBean.getData().getPosts().get(position).setLiked(checkBox.isChecked());
            }
        });
        return convertView;
    }

    class DetailsSpecialViewHolder {

        private final ImageView ivShow;
        private final CheckBox checkBoxLikecount;
        private final TextView tvTitle;

        public DetailsSpecialViewHolder(View view) {
            ivShow = (ImageView) view.findViewById(R.id.iv_listview_discover);
            checkBoxLikecount = (CheckBox) view.findViewById(R.id.checkBox_item_discover);
            tvTitle = (TextView) view.findViewById(R.id.tv_listview_discover);
        }
    }
}
