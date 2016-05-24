package com.example.dllo.gift.discover;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.discover.disadapter.DiscoverSRVAdapter;
import com.example.dllo.gift.discover.disadapter.DiscoverSVPAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/20.
 */
public class DiscoverSelectionFragment extends BaseFragment {

    private ArrayList<Integer> datas;
    private ViewPager vpDiscoverSelection;
    private ImageView view;
    private RecyclerView rvDiscoverSelection;
    private Handler handler;
    private Thread thread;
    private boolean flag;

    @Override
    public int setLayout() {
        return R.layout.fragment_discover_selection;
    }

    @Override
    public void initView(View view) {
        vpDiscoverSelection = (ViewPager) view.findViewById(R.id.viewPager_selection_discover);
        rvDiscoverSelection = (RecyclerView) view.findViewById(R.id.recyclerView_selection_discover);

    }

    @Override
    public void initData() {


        //viewPager
        DiscoverSVPAdapter svpAdapter = new DiscoverSVPAdapter(context);
        datas = new ArrayList<>();
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher_gift);
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher_gift);
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher_gift);
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher_gift);
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher_gift);
        datas.add(R.mipmap.ic_launcher);
        datas.add(R.mipmap.ic_launcher_gift);
        svpAdapter.setDatas(datas);
        vpDiscoverSelection.setAdapter(svpAdapter);
//        轮播
//        handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                vpDiscoverSelection.setCurrentItem(msg.what);
//                handler.sendEmptyMessageDelayed(msg.what + 1 , 2000);
//                return false;
//            }
//        });
//        handler.sendEmptyMessageDelayed(0, 1000);
        //开线程轮播
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                vpDiscoverSelection.setCurrentItem(msg.what);
                return false;
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                flag = true;
                while (flag) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(i++);
                }
            }
        });
        thread.start();

        //recyclerView
        DiscoverSRVAdapter srvAdapter = new DiscoverSRVAdapter(context);
        srvAdapter.setDatas(datas);
        rvDiscoverSelection.setAdapter(srvAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDiscoverSelection.setLayoutManager(manager);

    }

    @Override
    public void onResume() {
        super.onResume();
        flag = true;
        Log.d("DiscoverSelectionFragme", "onresume");
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
        Log.d("DiscoverSelectionFragme", "onpause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DiscoverSelectionFragme", "ondestroy");
    }
}
