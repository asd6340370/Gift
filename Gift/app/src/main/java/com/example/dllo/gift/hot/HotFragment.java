package com.example.dllo.gift.hot;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.bmob.UserBmobBean;
import com.example.dllo.gift.details.DetailsPurchaseActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.google.gson.Gson;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/5/19.
 */
public class HotFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView gridViewHot;

    private HotBean hotBean;
    private HotBean.DataBean.ItemsBean.DataItem dataItem;
    private UserBmobBean userBmobBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView(View view) {
        gridViewHot = (GridView) view.findViewById(R.id.gridView_hot);
        gridViewHot.setOnItemClickListener(this);
    }

    @Override
    public void initData() {


        final HotAdapter adapter = new HotAdapter(context);
        gridViewHot.setAdapter(adapter);

        NetTools netTools = new NetTools();
        netTools.getHotData(new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                hotBean = gson.fromJson(result, HotBean.class);
                adapter.setDatas(hotBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });




    }

    //GridView 行布局item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dataItem = hotBean.getData().getItems().get(position).getData();
        userBmobBean = new UserBmobBean();
        userBmobBean.setKey("gift");
        userBmobBean.setId(String.valueOf(dataItem.getId()));
        userBmobBean.setImgUrl(dataItem.getCover_image_url());
        userBmobBean.setLikeCount(String.valueOf(dataItem.getFavorites_count()));
        userBmobBean.setPrice(dataItem.getPrice());
        userBmobBean.setTitleName(dataItem.getName());
        userBmobBean.setPurchaseUrl(dataItem.getPurchase_url());
        String purchaseUrl = hotBean.getData().getItems().get(position).getData().getPurchase_url();
        String urlId = String.valueOf(hotBean.getData().getItems().get(position).getData().getId());
        String titleName = hotBean.getData().getItems().get(position).getData().getName();
        Intent intent = new Intent(context, DetailsPurchaseActivity.class);
//        intent.putExtra("buy",dataItem);
        intent.putExtra("bmobBean", (Parcelable) userBmobBean);
        intent.putExtra("purchaseUrl",purchaseUrl);
        intent.putExtra("urlId",urlId);
        intent.putExtra("titleName",titleName);
        startActivity(intent);


    }
}
