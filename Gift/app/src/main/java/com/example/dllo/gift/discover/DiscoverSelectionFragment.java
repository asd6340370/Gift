package com.example.dllo.gift.discover;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.discover.disadapter.DiscoverSLVAdapter;
import com.example.dllo.gift.discover.disadapter.DiscoverSRVAdapter;
import com.example.dllo.gift.discover.disadapter.DiscoverSVPAdapter;
import com.example.dllo.gift.discover.disbean.SpecialListHeaderBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;

/**
 * Created by dllo on 16/5/20.
 */
public class DiscoverSelectionFragment extends BaseFragment {


    private ViewPager vpDiscoverSelection;
    private NetTools netTools;
    private RecyclerView rvDiscoverSelection;
    private Handler handler;
    private Thread thread;
    private boolean flag;
    private View headerView;
    private ListView listViewDiscoverSelection;
    private DiscoverSRVAdapter srvAdapter;
    private DiscoverSVPAdapter svpAdapter;
    private DiscoverSLVAdapter slvAdapter;

    private SpecialListHeaderBean headerBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_discover_selection;
    }

    @Override
    public void initView(View view) {

        listViewDiscoverSelection = (ListView) view.findViewById(R.id.listView_discover_selection);
        //listView 的heardview
        headerView = LayoutInflater.from(context).inflate(R.layout.header_discover_selection, null);
        vpDiscoverSelection = (ViewPager) headerView.findViewById(R.id.viewPager_selection_discover);
        rvDiscoverSelection = (RecyclerView) headerView.findViewById(R.id.recyclerView_selection_discover);

    }

    @Override
    public void initData() {

        netTools = new NetTools();
        //speciallist
        netTools.getDiscoverSpecialList();
        slvAdapter = new DiscoverSLVAdapter(context);
        listViewDiscoverSelection.addHeaderView(headerView);
        listViewDiscoverSelection.setAdapter(slvAdapter);



        //viewPager
        netTools.getDiscoverBanner();//获得数据并添加数据
        svpAdapter = new DiscoverSVPAdapter(context);
        vpDiscoverSelection.setAdapter(svpAdapter);
        //轮播
//        runBanner();


        //recyclerView
        netTools.getDiscoverSpecialListHeader();
//        netTools.getData(URLValues.DISCOVER_SPECIAL_LIST_Header,headerBean,SpecialListHeaderBean.class);
        srvAdapter = new DiscoverSRVAdapter(context);
        rvDiscoverSelection.setAdapter(srvAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDiscoverSelection.setLayoutManager(manager);


    }

    private void runBanner() {
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
    }


    @Override
    public void onResume() {
        super.onResume();
        flag = true;
//        Log.d("DiscoverSelectionFragme", "onresume");
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
//        Log.d("DiscoverSelectionFragme", "onpause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        svpAdapter.unregister();
        srvAdapter.unregister();
        slvAdapter.unregister();
//        Log.d("DiscoverSelectionFragme", "ondestroy");
    }

}
