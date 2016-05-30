package com.example.dllo.gift.discover;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.nettools.NetTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class DiscoverFragment extends BaseFragment {
    private TabLayout discoverTabLayout;
    private ViewPager discoverViewPager;
    private DiscoverVPAdapter adapter;
    private NetTools netTools;

    @Override
    public int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView(View view) {
        discoverTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_discover);
        discoverViewPager = (ViewPager) view.findViewById(R.id.viewPager_discover);
    }

    @Override
    public void initData() {
        netTools = new NetTools();

        netTools.getDiscoverTitles();
        adapter = new DiscoverVPAdapter(getChildFragmentManager());
        discoverViewPager.setAdapter(adapter);

        discoverTabLayout.setupWithViewPager(discoverViewPager);
        discoverTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.unregister();
    }
}
