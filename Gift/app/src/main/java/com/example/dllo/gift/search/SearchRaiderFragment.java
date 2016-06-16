package com.example.dllo.gift.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;

/**
 * Created by ZHI on 2016/6/11.
 */
public class SearchRaiderFragment  extends BaseFragment{

    private ListView listViewRaiderSearch;
    private MyBroadCast myBroadCast;

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

        //注册动态广播
        myBroadCast = new MyBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.dllo.gift.SEARCH");
        context.registerReceiver(myBroadCast,intentFilter);

    }

    class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
//            getNetData(name);
        }
    }

}
