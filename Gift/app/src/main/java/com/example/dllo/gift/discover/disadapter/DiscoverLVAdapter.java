package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.discover.disbean.SpecialListBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverLVAdapter extends BaseAdapter {
    private SpecialListBean datas;
    private Context context;

    public void setDatas(SpecialListBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public DiscoverLVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null :datas.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_discover_selection, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url())
        .centerCrop().fit().into(viewHolder.ivShow);

        return convertView;
    }


    class ViewHolder {
        private ImageView ivShow;
        public ViewHolder(View view) {
           ivShow = (ImageView) view.findViewById(R.id.iv_listview_discover_selection);
        }
    }
}
