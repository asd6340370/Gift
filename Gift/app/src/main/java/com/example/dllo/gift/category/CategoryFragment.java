package com.example.dllo.gift.category;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/20.
 */
public class CategoryFragment extends BaseFragment {
    private ArrayList<Fragment> fragments;
    private ViewPager categoryViewPager ;
    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
        categoryViewPager = (ViewPager) view.findViewById(R.id.viewPager_category);

    }

    @Override
    public void initData() {
        //fragment中的fragment 的fragmentManager 需要用getchildFragmentManager()
        CategoryVPAdapter adapter = new CategoryVPAdapter(getChildFragmentManager());
        categoryViewPager .setAdapter(adapter);

        CategoryRaiderFragment categoryRaiderFragment = new CategoryRaiderFragment();
        CategoryGiftFragment categoryGiftFragment = new CategoryGiftFragment();
        fragments = new ArrayList<>();
        fragments.add(categoryRaiderFragment);
        fragments.add(categoryGiftFragment);
        adapter.setFragments(fragments);

    }
}
