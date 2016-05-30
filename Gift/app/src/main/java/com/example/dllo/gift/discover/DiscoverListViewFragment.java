package com.example.dllo.gift.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.example.dllo.gift.DiscoverListViewDetailsActivtiy;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.discover.disadapter.DiscoverLVAdapter;
import com.example.dllo.gift.discover.disadapter.DiscoverSLVAdapter;
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverListViewFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private DiscoverLVAdapter lvAdapter;
    private ListView lvDiscoverListView;
    private NetTools netTools;
    private String  num;
    private RequestQueue queue;
    private ListBean listBean;

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
        lvDiscoverListView = (ListView) view.findViewById(R.id.listView_discover_listView);
        lvDiscoverListView.setOnItemClickListener(this);

    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        num = bundle.getString("data");
        lvAdapter = new DiscoverLVAdapter(context);
        lvDiscoverListView.setAdapter(lvAdapter);
        netTools = new NetTools();
        netTools.getDiscoverList(num, new NetListener() {
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
        String title = listBean.getData().getItems().get(position).getTitle();
        Intent intent = new Intent(context, DiscoverListViewDetailsActivtiy.class);
        ArrayList<String> urlDatas = new ArrayList<>();
        for (ListBean.DataBean.ItemsBean b :
                listBean.getData().getItems()) {
            urlDatas.add(b.getUrl());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("url",urlDatas);
        intent.putExtras(bundle);
        intent.putExtra("position",position);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
