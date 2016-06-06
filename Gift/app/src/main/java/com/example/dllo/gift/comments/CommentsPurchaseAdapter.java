package com.example.dllo.gift.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.tools.Circle;
import com.example.dllo.gift.tools.DateUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/31.
 */
public class CommentsPurchaseAdapter extends BaseAdapter {
    private CommentsPurchaseBean beans;
    private Context context;

    public void setbeans(CommentsPurchaseBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    public CommentsPurchaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.getData().getComments().size();
    }

    @Override
    public Object getItem(int position) {
        return beans == null ? null : beans.getData().getComments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_comments_purchase, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        if (position == beans.getData().getComments().size() - 1) {
            holder.bottomLine.setVisibility(View.GONE);
        }


        if (!beans.getData().getComments().get(position).getUser().getAvatar_url().trim().equals("")) {
            Picasso.with(context).load(beans.getData().getComments().get(position).getUser().getAvatar_url())
                    .transform(new Circle()).centerCrop().fit().error(R.mipmap.me_avatar_boy).into(holder.ivUserIcon);
        } else {
            holder.ivUserIcon.setImageResource(R.mipmap.me_avatar_boy);
        }
        holder.tvUserName.setText(beans.getData().getComments().get(position).getUser().getNickname());
        holder.tvUserTime.setText(DateUtils.formatData("MM/dd/yyyy, hh:mm a", beans.getData().getComments().get(position).getCreated_at()));
        holder.tvUserContent.setText(beans.getData().getComments().get(position).getContent());

        return convertView;
    }

    class MyViewHolder {

        private TextView tvUserTime;
        private ImageView ivUserIcon;
        private TextView tvUserName;
        private TextView tvUserContent;
        private TextView bottomLine;

        public MyViewHolder(View view) {
            bottomLine = (TextView)view.findViewById(R.id.tv_bottom_line_comments_purchase);
            ivUserIcon = (ImageView) view.findViewById(R.id.iv_icon_user_comments_purchase);
            tvUserName = (TextView) view.findViewById(R.id.tv_username_comments_purchase);
            tvUserContent = (TextView) view.findViewById(R.id.tv_content_comments_purchase);
            tvUserTime = (TextView) view.findViewById(R.id.tv_usertime_comments_purchase_ago);

        }
    }
}
