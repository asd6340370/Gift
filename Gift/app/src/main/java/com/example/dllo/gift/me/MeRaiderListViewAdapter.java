package com.example.dllo.gift.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.bmob.UserBmobBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/19.
 */
public class MeRaiderListViewAdapter extends BaseAdapter {
    private List<UserBmobBean> datas;
    private Context context;

    public void setDatas(List<UserBmobBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public MeRaiderListViewAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_me_raider, parent, false);
             viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //毕加索解析图片
        Picasso.with(context).load(datas.get(position).getImgUrl())
              .transform(new RoundRect(15)).centerCrop().fit().into(viewHolder.ivShow);
        viewHolder.tvTitle.setText(datas.get(position).getTitleName());

        return convertView;
    }


    class ViewHolder {

        private final TextView tvTitle;
        private final ImageView ivShow;
        public ViewHolder (View itemView){
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_listview_me_raider);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_listview_me_raider);


        }

    }
}
