package com.example.dllo.gift.search;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchFragment extends BaseFragment {

    private TabLayout tabLayoutSearch;
    private ViewPager viewPagerSearch;
    private SearchViewPagerAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView(View view) {
        tabLayoutSearch = (TabLayout) view.findViewById(R.id.tablayout_search);
        viewPagerSearch = (ViewPager) view.findViewById(R.id.viewpager_search);
    }

    @Override
    public void initData() {
        adapter = new SearchViewPagerAdapter(getChildFragmentManager());
        viewPagerSearch.setAdapter(adapter);
        tabLayoutSearch.setupWithViewPager(viewPagerSearch);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SearchGiftFragment());
        fragments.add(new SearchRaiderFragment());
        adapter.setFragments(fragments);
    }
}
