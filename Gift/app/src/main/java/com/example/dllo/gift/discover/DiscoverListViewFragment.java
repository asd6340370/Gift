package com.example.dllo.gift.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.bmob.BmobData;
import com.example.dllo.gift.details.DetailsRaiderActivtiy;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.discover.disadapter.DiscoverLVAdapter;
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.tools.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverListViewFragment extends BaseFragment implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
    private DiscoverLVAdapter lvAdapter;
    private XListView lvDiscoverListView;
    private NetTools netTools;
    private String url;
    private ListBean listBean;
    private ArrayList<String> idArray;
    private BmobData bmobData;
    private BmobUser bmobUser;
    private List<String> ids;

    public static Fragment createListViewFragment (String url) {
        Fragment fragment = new DiscoverListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data",url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_discover_listview;
    }

    @Override
    public void initView(View view) {
        lvDiscoverListView = (XListView) view.findViewById(R.id.listView_discover_listView);
        lvDiscoverListView.setOnItemClickListener(this);

    }


    @Override
    public void initData() {
        //
        Bundle bundle = getArguments();
        url = bundle.getString("data");
//        Log.d("DiscoverListViewFragmen", url);
        lvAdapter = new DiscoverLVAdapter(context);
        lvDiscoverListView.setAdapter(lvAdapter);
        netTools = new NetTools();
        getListBeanData();

        lvDiscoverListView.setPullRefreshEnable(true);
        lvDiscoverListView.setPullLoadEnable(true);
        lvDiscoverListView.setXListViewListener(this);
        bmobUser = BmobUser.getCurrentUser(context);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void getListBeanData() {
        netTools.getNormalData(url, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                listBean =  gson.fromJson(result,ListBean.class);
                lvAdapter.setDatas(listBean);

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int index = (int) parent.getAdapter().getItemId(position);
        Intent intent = new Intent(context, DetailsRaiderActivtiy.class);

        idArray = new ArrayList<>();
        for (ListBean.DataBean.ItemsBean b :
                lvAdapter.getDatas().getData().getItems()) {
            idArray.add(String.valueOf(b.getId()));
        }
        intent.putStringArrayListExtra("idArray",idArray);
        intent.putExtra("position",index);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getListBeanData();
    }

    @Override
    public void onLoadMore() {
        String url = listBean.getData().getPaging().getNext_url();
        Log.d("DiscoverListViewFragmen", url);
        netTools.getNormalData(url, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                listBean = gson.fromJson(result,ListBean.class);

              List<ListBean.DataBean.ItemsBean> listItems =  listBean.getData().getItems();
                lvAdapter.setListItems(listItems);
                
                lvDiscoverListView.stopLoadMore();
            }
            @Override
            public void onFailed(VolleyError error) {

            }
        });


    }
}
