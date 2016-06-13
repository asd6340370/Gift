package com.example.dllo.gift.discover.disadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.tools.RoundRect;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverLVAdapter extends BaseAdapter {
    private  BmobData bmobData;

    private ListBean datas;
    private Context context;
    private List<ListBean.DataBean.ItemsBean> ListItems;
    private UserBmobBean userBmobBean;

    public ListBean getDatas() {
        return datas;
    }

    public void setListItems(List<ListBean.DataBean.ItemsBean> listItems) {
        ListItems = listItems;
        datas.getData().getItems().addAll(listItems);
        notifyDataSetChanged();
    }



    public DiscoverLVAdapter(Context context) {
        this.context = context;
        bmobData = new BmobData(context);
        bmobData.queryAllLike(this);
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
        return datas == null ? null : datas.getData().getItems().get(position);
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
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url())
                .transform(new RoundRect(20)).centerCrop().fit().into(viewHolder.ivShow);

        viewHolder.tvTitleDiscover.setText(datas.getData().getItems().get(position).getTitle());
        viewHolder.checkBoxLikesCounts.setText(datas.getData().getItems().get(position).getLikes_count() + "");
        //查询
        bmobData.queryIsLikeRaiders(String.valueOf(datas.getData().getItems().get(position).getId()),viewHolder.checkBoxLikesCounts);
//        viewHolder.checkBoxLikesCounts.setChecked(datas.getData().getItems().get(position).isLiked());

        viewHolder.checkBoxLikesCounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO checkBox
                setDataCheckBoxSelected(viewHolder, position);
                CheckBox checkBox = (CheckBox) v;
                datas.getData().getItems().get(position).setLiked(checkBox.isChecked());
            }
        });
        return convertView;
    }

    private void setDataCheckBoxSelected(final ViewHolder viewHolder, int position) {
        BmobUser user = BmobUser.getCurrentUser(context);
        if (user == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            viewHolder.checkBoxLikesCounts.setChecked(false);
        } else if (viewHolder.checkBoxLikesCounts.isChecked() && BmobUser.getCurrentUser(context) != null) {
            userBmobBean = new UserBmobBean();
            userBmobBean.setKey("raider");
            userBmobBean.setUserName(BmobUser.getCurrentUser(context).getUsername());
            userBmobBean.setId(String.valueOf(datas.getData().getItems().get(position).getId()));
            userBmobBean.setImgUrl(datas.getData().getItems().get(position).getCover_image_url());
            userBmobBean.setTitleName(datas.getData().getItems().get(position).getTitle());
            userBmobBean.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    bmobData.queryAllLike();
                }
                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    viewHolder.checkBoxLikesCounts.setChecked(false);
                }
            });

        } else {

            BmobQuery<UserBmobBean> query = new BmobQuery<>();
            //条件查询
            query.addWhereEqualTo("userName",BmobUser.getCurrentUser(context).getUsername());
            query.addWhereEqualTo("id", String.valueOf(datas.getData().getItems().get(position).getId()));
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


    class ViewHolder {

        private TextView tvTitleDiscover;
        private ImageView ivShow;
        private CheckBox checkBoxLikesCounts;

        public ViewHolder(View view) {
            checkBoxLikesCounts = (CheckBox) view.findViewById(R.id.checkBox_item_discover);
            tvTitleDiscover = (TextView) view.findViewById(R.id.tv_listview_discover);
            ivShow = (ImageView) view.findViewById(R.id.iv_listview_discover);
        }
    }


}
