package com.example.dllo.gift.details.detailsadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.gift.LoginActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.bmob.BmobData;
import com.example.dllo.gift.bmob.UserBmobBean;
import com.example.dllo.gift.details.detailsbean.DetailsSpecialBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/6/4.
 */
public class DetailsSpecialAdapter extends BaseAdapter {
    private DetailsSpecialBean specialBean;
    private Context context;
    private UserBmobBean userBmobBean;
    private BmobData bmobData;

    public DetailsSpecialAdapter(Context context) {
        this.context = context;
        bmobData = new BmobData(context);
        bmobData.queryAllLike(this);
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
        } else {
            holder = (DetailsSpecialViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(specialBean.getData().getPosts().get(position).getCover_image_url())
                .transform(new RoundRect(20)).centerCrop().fit().into(holder.ivShow);
        holder.tvTitle.setText(specialBean.getData().getPosts().get(position).getTitle());

        //查询设置checkBox状态
        bmobData.queryIsLikeRaiders(String.valueOf(specialBean.getData().getPosts().get(position).getId()),holder.checkBoxLikecount);

        holder.checkBoxLikecount.setText(String.valueOf(specialBean.getData().getPosts().get(position).getLikes_count()));
        holder.checkBoxLikecount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO checkBox
                setDataCheckBoxSelected(holder, position);
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
    private void setDataCheckBoxSelected(final DetailsSpecialViewHolder viewHolder, int position) {
        BmobUser user = BmobUser.getCurrentUser(context);
        if (user == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            viewHolder.checkBoxLikecount.setChecked(false);
        } else if (viewHolder.checkBoxLikecount.isChecked() && BmobUser.getCurrentUser(context) != null) {
            userBmobBean = new UserBmobBean();
            userBmobBean.setKey("raider");
            userBmobBean.setUserName(BmobUser.getCurrentUser(context).getUsername());
            userBmobBean.setId(String.valueOf(specialBean.getData().getPosts().get(position).getId()));
            userBmobBean.setImgUrl(specialBean.getData().getPosts().get(position).getCover_image_url());
            userBmobBean.setTitleName(specialBean.getData().getPosts().get(position).getTitle());
            userBmobBean.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    bmobData.queryAllLike();
                }
                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    viewHolder.checkBoxLikecount.setChecked(false);
                }
            });

        } else {

            BmobQuery<UserBmobBean> query = new BmobQuery<>();
            //条件查询
            query.addWhereEqualTo("userName",BmobUser.getCurrentUser(context).getUsername());
            query.addWhereEqualTo("id", String.valueOf(specialBean.getData().getPosts().get(position).getId()));
            //查询到对应id的数据库内容
            query.findObjects(context, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {
                    //遍历这个List
                    for (UserBmobBean bmobCollectBean : list) {
                        //进行删除
                        bmobCollectBean.delete(context, new DeleteListener() {
                            @Override
                            public void onSuccess() {
                                //删除成功
                                Toast.makeText(context, "取消喜欢成功", Toast.LENGTH_SHORT).show();
                                bmobData.queryAllLike();
                            }
                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(context, "删除失败" + s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                @Override
                public void onError(int i, String s) {

                }
            });

        }
    }
}
