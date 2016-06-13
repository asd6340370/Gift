package com.example.dllo.gift.search;

import android.view.View;
import android.widget.GridView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchGiftFragment extends BaseFragment {

    private GridView gridViewGiftSearch;

    @Override
    public int setLayout() {
        return R.layout.fragment_search_gift;
    }

    @Override
    public void initView(View view) {
        gridViewGiftSearch = (GridView) view.findViewById(R.id.gridview_gift_search);

    }

    @Override
    public void initData() {

    }
}
