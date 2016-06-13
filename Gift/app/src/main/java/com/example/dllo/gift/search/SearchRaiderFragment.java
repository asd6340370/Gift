package com.example.dllo.gift.search;

import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchRaiderFragment  extends BaseFragment{

    private ListView listViewRaiderSearch;

    @Override
    public int setLayout() {
        return R.layout.fragment_search_raider;
    }

    @Override
    public void initView(View view) {
        listViewRaiderSearch = (ListView) view.findViewById(R.id.listview_search_raider);

    }

    @Override
    public void initData() {

    }
}
