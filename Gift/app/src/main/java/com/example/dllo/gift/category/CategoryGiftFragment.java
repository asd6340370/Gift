package com.example.dllo.gift.category;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseFragment;
import com.example.dllo.gift.category.categoryadapter.CategoryListViewContentAdapter;
import com.example.dllo.gift.category.categoryadapter.CategoryListViewTitleAdapter;
import com.example.dllo.gift.category.categorybean.CategoryGiftBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/19.
 */
public class CategoryGiftFragment extends BaseFragment{

    private ListView listViewTitle;
    private CategoryListViewTitleAdapter titleAdapter;
    private NetTools netTools;
    private CategoryGiftBean giftBean;
    private ListView listViewContent;
    private CategoryListViewContentAdapter contentAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_category_gift;
    }

    @Override
    public void initView(View view) {
        listViewTitle = (ListView) view.findViewById(R.id.listView_title_category_gift);
        listViewContent = (ListView) view.findViewById(R.id.listView_content_category_gift);



    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        titleAdapter = new CategoryListViewTitleAdapter(context);
        listViewTitle.setAdapter(titleAdapter);
        contentAdapter = new CategoryListViewContentAdapter(context);
        listViewContent.setAdapter(contentAdapter);

        netTools = new NetTools();
        netTools.getDataForEventBus(URLValues.CATEGORY_GIFT, CategoryGiftBean.class);

        setListViewLeftRightChange();

    }
    @Subscribe
    public void getGiftBean(CategoryGiftBean giftBean){
        titleAdapter.setGiftBean(giftBean);
        contentAdapter.setGiftBean(giftBean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //左右两个listview 联动
    public void setListViewLeftRightChange(){
        listViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传点击位置position到adapter
                titleAdapter.setmCheckedPosition(position);
                //设置右侧显示位置与左侧一直
                listViewContent.setSelection(position);
                //刷新  重绘控件
                contentAdapter.notifyDataSetInvalidated();
            }
        });



        listViewContent.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (lastPos != firstVisibleItem){
                    lastPos = firstVisibleItem;
                    listViewTitle.smoothScrollToPosition(firstVisibleItem);

                }
            }
        });
    }
    int lastPos = 0;

}
