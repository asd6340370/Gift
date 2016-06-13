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
import com.example.dllo.gift.hot.HotBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/19.
 */
public class MeGiftGirdViewAdapter extends BaseAdapter {
    private List<UserBmobBean> datas;
    private Context context;

    public void setDatas(List<UserBmobBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public MeGiftGirdViewAdapter(Context context) {
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
        //毕加索解析图片
        Picasso.with(context).load(datas.get(position).getImgUrl())
              .centerCrop().fit().into(viewHolder.ivShow);

        viewHolder.tvTitle.setText(datas.get(position).getTitleName());
        viewHolder.tvPrice.setText(datas.get(position).getPrice());
        viewHolder.tvLikes.setText(datas.get(position).getLikeCount());


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
