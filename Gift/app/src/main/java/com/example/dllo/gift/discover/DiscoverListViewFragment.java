package com.example.dllo.gift.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.discover.disadapter.DiscoverSLVAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/21.
 */
public class DiscoverListViewFragment extends BaseFragment{

    private ArrayList<String> datas;
    private DiscoverSLVAdapter lvAdapter;
    private ListView lvDiscoverListView;

    public static Fragment createListViewFragment (String data) {
        Fragment fragment = new DiscoverListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data",data);
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

    }

    @Override
    public void initData() {

//        Bundle bundle = getArguments();
//        String data = bundle.getString("data");



    }
}
