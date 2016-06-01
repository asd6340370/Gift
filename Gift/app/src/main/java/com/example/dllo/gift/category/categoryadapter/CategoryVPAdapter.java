package com.example.dllo.gift.category.categoryadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class CategoryVPAdapter extends FragmentPagerAdapter {

private ArrayList<Fragment> fragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public CategoryVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 :fragments.size();
    }
}
