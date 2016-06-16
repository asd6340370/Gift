package com.example.dllo.gift.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.details.DetailsPurchaseActivity;
import com.example.dllo.gift.details.detailsadapter.DetailsCategoryGiftGVAdapter;
import com.example.dllo.gift.details.detailsbean.DetailsCategoryGiftBean;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.bmob.UserBmobBean;
import com.google.gson.Gson;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchGiftFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView gridViewGiftSearch;
    private DetailsCategoryGiftGVAdapter giftGVAdapter;
    private NetTools netTools;
    private DetailsCategoryGiftBean giftBean;
    private UserBmobBean userBmobBean;
    private MyBroadCast myBroadCast;


    @Override
    public int setLayout() {
        return R.layout.fragment_search_gift;
    }

    @Override
    public void initView(View view) {
        gridViewGiftSearch = (GridView) view.findViewById(R.id.gridview_gift_search);
        gridViewGiftSearch.setOnItemClickListener(this);

    }

    @Override
    public void initData() {
        giftGVAdapter = new DetailsCategoryGiftGVAdapter(context);
        gridViewGiftSearch.setAdapter(giftGVAdapter);
        netTools = new NetTools();

        //注册动态广播
         myBroadCast = new MyBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.dllo.gift.SEARCH");
        context.registerReceiver(myBroadCast,intentFilter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(myBroadCast);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        userBmobBean = new UserBmobBean();
        userBmobBean.setKey("gift");
        userBmobBean.setId(String.valueOf(giftBean.getData().getItems().get(position).getId()));
        userBmobBean.setImgUrl(giftBean.getData().getItems().get(position).getCover_image_url());
        userBmobBean.setLikeCount(String.valueOf(giftBean.getData().getItems().get(position).getFavorites_count()));
        userBmobBean.setPrice(giftBean.getData().getItems().get(position).getPrice());
        userBmobBean.setTitleName(giftBean.getData().getItems().get(position).getName());
        userBmobBean.setPurchaseUrl(giftBean.getData().getItems().get(position).getPurchase_url());


        String purchaseUrl = giftBean.getData().getItems().get(position).getPurchase_url();
        String urlId = String.valueOf(giftBean.getData().getItems().get(position).getId());
        String titleName = giftBean.getData().getItems().get(position).getName();
        Intent intent = new Intent(context, DetailsPurchaseActivity.class);

        intent.putExtra("bmobBean", (Parcelable) userBmobBean);
        intent.putExtra("purchaseUrl",purchaseUrl);
        intent.putExtra("urlId",urlId);
        intent.putExtra("titleName",titleName);
        startActivity(intent);
    }
    private void getNetData(String name) {
        netTools.getNormalData(URLValues.SEARCH_GIFT + name, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                giftBean = gson.fromJson(result,DetailsCategoryGiftBean.class);
                giftGVAdapter.setDatas(giftBean);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    class MyBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            getNetData(name);
        }
    }


}
