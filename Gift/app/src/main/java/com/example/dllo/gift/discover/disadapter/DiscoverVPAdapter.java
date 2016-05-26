package com.example.dllo.gift.discover.disadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class DiscoverVPAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public DiscoverVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 :fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }


}
