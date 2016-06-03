package com.example.dllo.gift.details;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.hot.HotAdapter;
import com.example.dllo.gift.hot.HotBean;
import com.example.dllo.gift.nettools.NetTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/3.
 */
public class DetailsCategoryGiftActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView menuSort;
    private TextView tvTitle;
    private GridView gridViewGift;
    private HotAdapter giftAdapter;
    private NetTools netTools;

    @Override
    public void initActivity() {
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_details_category_gift_gridview);
        back = (ImageView) findViewById(R.id.back_details_category_gift_gridview);
        back.setOnClickListener(this);
        menuSort = (ImageView) findViewById(R.id.menu_sort_details_category_gift_gridview);
        menuSort.setOnClickListener(this);

        tvTitle = (TextView) findViewById(R.id.tv_title_details_category_gift_gridview);

        gridViewGift = (GridView) findViewById(R.id.gridView_details_category_gift);
        giftAdapter = new HotAdapter(this);
        gridViewGift.setAdapter(giftAdapter);
        String url = "???";
        netTools = new NetTools();
        netTools.getDataForEventBus(url, HotBean.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_details_category_gift_gridview:
                finish();
                break;
            case R.id.menu_sort_details_category_gift_gridview:

                break;

        }

    }
    @Subscribe
    public void getDetailsData(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
