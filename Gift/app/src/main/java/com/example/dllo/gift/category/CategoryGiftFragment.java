package com.example.dllo.gift.category;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.category.categoryadapter.CategoryGiftListViewContentAdapter;
import com.example.dllo.gift.category.categoryadapter.CategoryGiftListViewTitleAdapter;
import com.example.dllo.gift.category.categorybean.CategoryGiftBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/19.
 */
public class CategoryGiftFragment extends BaseFragment{

    private ListView listViewTitleLeft;
    private CategoryGiftListViewTitleAdapter titleAdapter;
    private NetTools netTools;
    private CategoryGiftBean giftBean;
    private ListView listViewContentRight;
    private CategoryGiftListViewContentAdapter contentAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_category_gift;
    }

    @Override
    public void initView(View view) {
        listViewTitleLeft = (ListView) view.findViewById(R.id.listView_title_category_gift);
        listViewContentRight = (ListView) view.findViewById(R.id.listView_content_category_gift);



    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        titleAdapter = new CategoryGiftListViewTitleAdapter(context);
        listViewTitleLeft.setAdapter(titleAdapter);
        //设置listview单选模式
//        listViewTitleLeft.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        contentAdapter = new CategoryGiftListViewContentAdapter(context);
        listViewContentRight.setAdapter(contentAdapter);

        netTools = new NetTools();
        netTools.getDataForEventBus(URLValues.CATEGORY_GIFT, CategoryGiftBean.class);

        setListViewLeftRightChange();
    }
    //收到网络解析的数据
    @Subscribe
    public void getGiftBeanData(CategoryGiftBean giftBean){
        titleAdapter.setGiftBean(giftBean);
        contentAdapter.setGiftBean(giftBean);
    }

    //左右两个listview 联动
    public void setListViewLeftRightChange(){
        listViewTitleLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传点击位置position到adapter
                titleAdapter.setmCheckedPosition(position);
                //设置右侧显示位置与左侧一直
                listViewContentRight.setSelection(position);
                //刷新  重绘控件
                contentAdapter.notifyDataSetInvalidated();
            }
        });
        //右侧联动
        listViewContentRight.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lastPos != firstVisibleItem){
                    lastPos = firstVisibleItem;
                    listViewTitleLeft.smoothScrollToPosition(firstVisibleItem);
                    titleAdapter.setmCheckedPosition(firstVisibleItem);
                }
            }
        });
    }
    int lastPos = 0;

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
