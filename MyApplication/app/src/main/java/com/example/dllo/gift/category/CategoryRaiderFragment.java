package com.example.dllo.gift.category;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class CategoryRaiderFragment extends BaseFragment {

    private ArrayList<Fragment> fragments;
    private ViewPager viewPagerCategory;

    @Override
    public int setLayout() {
        return R.layout.fragment_category_raider;
    }

    @Override
    public void initView(View view) {
        viewPagerCategory = (ViewPager) view.findViewById(R.id.viewPager_category);

    }

    @Override
    public void initData() {
        CategoryVPAdapter adapter = new CategoryVPAdapter(getActivity().getSupportFragmentManager());
        viewPagerCategory.setAdapter(adapter);

        //viewPager滑动
        CategoryGiftFragment categoryGiftFragment = new CategoryGiftFragment();
        fragments = new ArrayList<>();
        fragments.add(new CategoryRaiderFragment());
        fragments.add(categoryGiftFragment);
        adapter.setFragments(fragments);

    }
}
