package com.example.dllo.gift.details;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.details.detailsadapter.DetailsCategoryGiftGVAdapter;
import com.example.dllo.gift.details.detailsbean.DetailsCategoryGiftBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.MyPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/3.
 */
public class DetailsCategoryGiftActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, MyPopupWindow.MenuGiftSortOnClickListener {

    private ImageView back;
    private ImageView menuSort;
    private TextView tvTitle;
    private GridView gridViewGift;
    private DetailsCategoryGiftGVAdapter detailsGiftAdapter;
    private NetTools netTools;
    private DetailsCategoryGiftBean giftBean;
    private MyPopupWindow myPopupWindow;
    private String urlId;
    private String url;

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
        gridViewGift.setOnItemClickListener(this);
        detailsGiftAdapter = new DetailsCategoryGiftGVAdapter(this);
        gridViewGift.setAdapter(detailsGiftAdapter);
        //接收页面跳转传来的值
        Intent intent = getIntent();
        String titleNanme = intent.getStringExtra("titleName");
         urlId = intent.getStringExtra("urlId");
//        Log.d("DetailsCategoryGiftActi", titleNanme);
//        Log.d("DetailsCategoryGiftActi", urlId);
        //设置标题
        tvTitle.setText(titleNanme);
        if (urlId == null){
             url = URLValues.CATEGORY_GIFT_SELECT;
        }else {
            //拼接url
            url = URLValues.CATEGORY_GIFT_DETAILS_BEFORE + urlId + URLValues.CATEGORY_GIFT_DETAILS_AFTER;
        }

//        Log.d("DetailsCategoryGiftActi", url);
        //网络解析
        netTools = new NetTools();
        netTools.getDataForEventBus(url, DetailsCategoryGiftBean.class);

        myPopupWindow = new MyPopupWindow(this,R.id.menu_sort_details_category_gift_gridview,1);
        myPopupWindow.setMenuGiftSortOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_details_category_gift_gridview:
                finish();
                break;
            case R.id.menu_sort_details_category_gift_gridview:
                myPopupWindow.showGiftMenuSortPopupWindow();
                break;

        }

    }

    @Subscribe
    public void getDetailsData(DetailsCategoryGiftBean giftBean) {
        detailsGiftAdapter.setDatas(giftBean);
        this.giftBean = giftBean;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String purchaseUrl = giftBean.getData().getItems().get(position).getPurchase_url();
            String urlId = String.valueOf(giftBean.getData().getItems().get(position).getId());
            String titleName = giftBean.getData().getItems().get(position).getName();
            Intent intent = new Intent(this, DetailsPurchaseActivity.class);
            intent.putExtra("purchaseUrl",purchaseUrl);
            intent.putExtra("urlId",urlId);
            intent.putExtra("titleName",titleName);
            startActivity(intent);

    }

    //popupWindow点击事件
    @Override
    public void getHotUrl() {
        String urlHot = URLValues.CATEGORY_GIFT_DETAILS_BEFORE + urlId + URLValues.CATEGORY_GIFT_DETAILS_AFTER + "&sort=hot";
        netTools.getDataForEventBus(urlHot, DetailsCategoryGiftBean.class);
    }

    @Override
    public void getDefaultUrl() {
        String urlDefault = URLValues.CATEGORY_GIFT_DETAILS_BEFORE + urlId + URLValues.CATEGORY_GIFT_DETAILS_AFTER;
        netTools.getDataForEventBus(urlDefault, DetailsCategoryGiftBean.class);
    }

    @Override
    public void getPriceHighToLow() {
        String url = URLValues.CATEGORY_GIFT_DETAILS_BEFORE + urlId + URLValues.CATEGORY_GIFT_DETAILS_AFTER +"&sort=price:desc";
        netTools.getDataForEventBus(url, DetailsCategoryGiftBean.class);
    }

    @Override
    public void getPriceLowToHigh() {
        String url = URLValues.CATEGORY_GIFT_DETAILS_BEFORE + urlId + URLValues.CATEGORY_GIFT_DETAILS_AFTER +"&sort=price:asc";
        netTools.getDataForEventBus(url, DetailsCategoryGiftBean.class);
    }
}
