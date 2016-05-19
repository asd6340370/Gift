package com.example.dllo.gift.discover;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

/**
 * Created by dllo on 16/5/19.
 */
public class DiscoverFragment extends BaseFragment {

    private TabLayout discoverTabLayout;
    private ViewPager discoverViewPager;
    private ListView discoverListView;

    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {
        discoverTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_discover);
        discoverViewPager = (ViewPager) view.findViewById(R.id.viewPager_discover);
        discoverListView = (ListView) view.findViewById(R.id.listView_discover);

    }

    @Override
    public void initData() {


    }
}
