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
public class CommentsAdapter extends BaseAdapter {
    private CommentsBean commentsBean;
    private Context context;

    public void setCommentsBean(CommentsBean commentsBean) {
        this.commentsBean = commentsBean;
        notifyDataSetChanged();
    }

    public CommentsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentsBean == null ? 0 : commentsBean.getData().getComments().size();
    }

    @Override
    public Object getItem(int position) {
        return commentsBean == null ? null : commentsBean.getData().getComments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_comments, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }


        if (!commentsBean.getData().getComments().get(position).getUser().getAvatar_url().trim().equals("")) {
            Picasso.with(context).load(commentsBean.getData().getComments().get(position).getUser().getAvatar_url())
                    .transform(new Circle()).centerCrop().fit().error(R.mipmap.me_avatar_boy).into(holder.ivUserIcon);
        }else {
            holder.ivUserIcon.setImageResource(R.mipmap.me_avatar_boy);
        }
        holder.tvUserName.setText(commentsBean.getData().getComments().get(position).getUser().getNickname());
        holder.tvUserTimeAgo.setText(DateUtils.getStandardDate(String.valueOf(commentsBean.getData().getComments().get(position).getCreated_at())));
        holder.tvUserTime.setText(DateUtils.formatData(", hh:mm a", commentsBean.getData().getComments().get(position).getCreated_at()));
        holder.tvUserContent.setText(commentsBean.getData().getComments().get(position).getContent());
        holder.checkBoxUserLike.setText(String.valueOf(commentsBean.getData().getComments().get(position).getLikes_count()));
        return convertView;
    }

    class MyViewHolder {

        private final TextView tvUserTime;
        private ImageView ivUserIcon;
        private TextView tvUserName;
        private TextView tvUserTimeAgo;
        private CheckBox checkBoxUserLike;
        private TextView tvUserContent;

        public MyViewHolder(View view) {
            ivUserIcon = (ImageView) view.findViewById(R.id.iv_icon_user_comments);
            tvUserName = (TextView) view.findViewById(R.id.tv_username_comments);
            tvUserTimeAgo = (TextView) view.findViewById(R.id.tv_usertime_comments_ago);
            checkBoxUserLike = (CheckBox) view.findViewById(R.id.checkbox_userlike_comments);
            tvUserContent = (TextView) view.findViewById(R.id.tv_content_comments);
            tvUserTime = (TextView) view.findViewById(R.id.tv_usertime_comments);

        }
    }
}