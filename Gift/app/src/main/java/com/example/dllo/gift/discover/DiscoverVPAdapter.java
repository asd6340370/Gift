package com.example.dllo.gift.discover;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class DiscoverVPAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
    private DiscoverTitlesBean titlesBean;

    public void setTitlesBean(DiscoverTitlesBean titlesBean) {
        this.titlesBean = titlesBean;
        notifyDataSetChanged();
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public DiscoverVPAdapter(FragmentManager fm) {
        super(fm);
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void getTitlesBean(DiscoverTitlesBean titlesBean){
        setTitlesBean(titlesBean);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return titlesBean == null ? 0 : titlesBean.getData().getChannels().size();
    }

    @Override
    public Fragment getItem(int position) {

            if (position == 0) {
                return new DiscoverSelectionFragment();
            } else {

                String url = "http://api.liwushuo.com/v2/channels/"+ titlesBean.getData().getChannels().get(position).getId() + "/items?limit=20&ad=2&gender=2&offset=0&generation=1";
                return DiscoverListViewFragment.createListViewFragment(url);

            }



    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlesBean.getData().getChannels().get(position).getName();
    }
    public void unregister(){
        EventBus.getDefault().unregister(this);
    }


}
