package com.example.dllo.gift.discover;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.gift.FriendActivity;
import com.example.dllo.gift.details.DetailsRaiderActivtiy;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.details.DetailsSpecialActivity;
import com.example.dllo.gift.discover.disadapter.DiscoverSLVAdapter;
import com.example.dllo.gift.discover.disadapter.DiscoverSRVAdapter;
import com.example.dllo.gift.discover.disadapter.DiscoverSVPAdapter;
import com.example.dllo.gift.discover.disbean.ListBean;

import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.tools.RecyclerOnClickListener;
import com.example.dllo.gift.DateActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/20.
 */
public class DiscoverSelectionFragment extends BaseFragment implements AdapterView.OnItemClickListener {


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
    private ListBean listBean;
    private ImageView newImageView;

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
        listViewDiscoverSelection.setOnItemClickListener(this);


    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);

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
        srvAdapter = new DiscoverSRVAdapter(context);
        rvDiscoverSelection.setAdapter(srvAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDiscoverSelection.setLayoutManager(manager);
        srvAdapter.setRecyclerOnClickListener(new RecyclerOnClickListener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 1 :
                        Intent intentfriend = new Intent(context, FriendActivity.class);
                        startActivity(intentfriend);
                        break;
                    case 2 :
                        Intent intentdate = new Intent(context, DateActivity.class);
                        startActivity(intentdate);
                        break;
                    case 3:
                        break;
                    default:
                    String url = srvAdapter.getDatas().getData().getSecondary_banners().get(position).getTarget_url();
                    String urlId = Uri.parse(url).getQueryParameter("topic_id");
                    Intent intent = new Intent(context, DetailsSpecialActivity.class);
                    intent.putExtra("urlId",urlId);
                    startActivity(intent);
                        break;
                }
            }
        });
    }

    @Subscribe
    public void getData(ListBean listBean) {
        slvAdapter.setDatas(listBean);
        this.listBean = listBean;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获得position 正确位置 index
        int index = (int) parent.getAdapter().getItemId(position);
        view.findViewById(R.id.iv_new_title_listview_discover_selection).setVisibility(View.GONE);
        listBean.getData().getItems().get(index).setStatus(1);
        slvAdapter.setDatas(listBean);

        ArrayList<ListBean.DataBean.ItemsBean> itemsBeens = new ArrayList<>();
        ArrayList<String> idArray = new ArrayList<>();
        //遍历创建id集合
        for (ListBean.DataBean.ItemsBean b : listBean.getData().getItems()) {
            idArray.add(String.valueOf(b.getId()));
        }
        //跳转页面并传值 id集合 和 位置信息 position
        Intent intent = new Intent(context, DetailsRaiderActivtiy.class);
        intent.putStringArrayListExtra("idArray", idArray);
        intent.putExtra("position", index);
        startActivity(intent);
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
        //取消EventBus注册
        svpAdapter.unregister();
        srvAdapter.unregister();
        EventBus.getDefault().unregister(this);

    }


}
