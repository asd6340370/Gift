package com.example.dllo.gift.hot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class HotAdapter extends BaseAdapter {
    private ArrayList<String> datas;
    private Context context;

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public HotAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
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

        viewHolder.tvTitle.setText(datas.get(position));



        return convertView;
    }


    class ViewHolder {

        private final TextView tvTitle;

        public ViewHolder (View itemView){
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_gv_hot);
        }

    }
}
