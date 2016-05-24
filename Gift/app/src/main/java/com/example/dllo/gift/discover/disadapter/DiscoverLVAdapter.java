package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverLVAdapter extends BaseAdapter {
    private ArrayList<String> datas;
    private Context context;

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public DiscoverLVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null :datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_discover, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvShow.setText(datas.get(position));
        return convertView;
    }


    class ViewHolder {
        private TextView tvShow;
        public ViewHolder(View view) {
           tvShow = (TextView) view.findViewById(R.id.tv_listview_discover);
        }
    }
}
