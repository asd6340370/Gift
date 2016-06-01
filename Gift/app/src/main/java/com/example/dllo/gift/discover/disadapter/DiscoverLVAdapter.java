package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverLVAdapter extends BaseAdapter {
    private ListBean datas;
    private Context context;



    public DiscoverLVAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ListBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_discover, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url())
        .transform(new RoundRect(20)).centerCrop().fit().into(viewHolder.ivShow);

        viewHolder.tvTitleDiscover.setText(datas.getData().getItems().get(position).getTitle());
        viewHolder.checkBoxLikesCounts.setText(datas.getData().getItems().get(position).getLikes_count() + "");
        viewHolder.checkBoxLikesCounts.setChecked(datas.getData().getItems().get(position).isLiked());
        viewHolder.checkBoxLikesCounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                datas.getData().getItems().get(position).setLiked(checkBox.isChecked());
            }
        });
        return convertView;
    }


    class ViewHolder {
        private final ImageView ivTitleNew;
        private final TextView tvTitleDiscover;
        private ImageView ivShow;
        private CheckBox checkBoxLikesCounts;
        public ViewHolder(View view) {
            checkBoxLikesCounts =  (CheckBox)view.findViewById(R.id.checkBox_item_discover);
            tvTitleDiscover =  (TextView)view.findViewById(R.id.tv_listview_discover);
            ivTitleNew = (ImageView)view.findViewById(R.id.iv_new_title_listview_discover);
           ivShow = (ImageView) view.findViewById(R.id.iv_listview_discover);
        }
    }


}
