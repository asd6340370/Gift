package com.example.dllo.gift.details;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.base.BaseActivity;
import com.example.dllo.gift.details.detailsadapter.DetailsCategoryGiftGVAdapter;
import com.example.dllo.gift.details.detailsbean.CategoryGiftSelectionMenuBean;
import com.example.dllo.gift.details.detailsbean.DetailsCategoryGiftBean;
import com.example.dllo.gift.nettools.NetListener;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.MyPopupWindow;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/6/3.
 */
public class DetailsCategoryGiftSelectionActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, MyPopupWindow.MenuGiftSortOnClickListener {

    private ImageView back;
    private ImageView menuSort;
    private TextView tvTitle;
    private GridView gridViewGift;
    private DetailsCategoryGiftGVAdapter detailsGiftAdapter;
    private NetTools netTools;
    private DetailsCategoryGiftBean giftBean;
    private MyPopupWindow myPopupWindow;
    private FrameLayout frameLayout;
    private RadioButton btnTarget;
    private RadioButton btnScene;
    private RadioButton btnPrice;
    private RadioButton btnPersonality;
    private CategoryGiftSelectionMenuFragment menuFragment;
    private CategoryGiftSelectionMenuBean menuBean;
    private LinearLayout linearLayoutSelection;
    private FrameLayout frameLayout1;
    private int pos = 0;
    private String[] url;
    private String urlDetails;
    private RadioButton[] buttons;
    private int[] index ;
    private int popupPosition = 0;


    @Override
    public void initActivity() {
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_details_category_gift_selection_gridview);
        back = (ImageView) findViewById(R.id.back_details_category_gift_selection_gridview);
        back.setOnClickListener(this);
        menuSort = (ImageView) findViewById(R.id.menu_sort_details_category_gift_selection_gridview);
        menuSort.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title_details_category_gift_selection_gridview);
        frameLayout1 = (FrameLayout) findViewById(R.id.framelayout_selection);
        gridViewGift = (GridView) findViewById(R.id.gridView_details_category_gift_selection);
        gridViewGift.setOnItemClickListener(this);
        detailsGiftAdapter = new DetailsCategoryGiftGVAdapter(this);
        gridViewGift.setAdapter(detailsGiftAdapter);
        //接收页面跳转传来的值
        Intent intent = getIntent();
        String titleNanme = intent.getStringExtra("titleName");
        //设置标题
        tvTitle.setText(titleNanme);
        //拼接url
        String urlNormal = URLValues.CATEGORY_GIFT_SELECT;
        //网络解析
        netTools = new NetTools();
        netTools.getDataForEventBus(urlNormal, DetailsCategoryGiftBean.class);
        myPopupWindow = new MyPopupWindow(this, R.id.menu_sort_details_category_gift_selection_gridview, 1);
        myPopupWindow.setMenuGiftSortOnClickListener(this);
        //筛选功能方法
        menuFragment = new CategoryGiftSelectionMenuFragment();
        selectionGift();
        menuFragment.setOnClickListener(new CategoryGiftSelectionMenuFragment.MenuFragmentOnClickListener() {
            @Override
            public void onClickListener() {
                closeWindow();
            }
        });
        buttons = new RadioButton[4];
        buttons[0] = btnTarget;
        buttons[1] = btnScene;
        buttons[2] = btnPersonality;
        buttons[3] = btnPrice;
        url = new String[4];
        url[0] = "";
        url[1] = "";
        url[2] = "";
        url[3] = "";
        index = new int[4];
        menuFragment.setItemOnClickListener(new CategoryGiftSelectionMenuFragment.MenuFragmentItemOnClickListener() {
            @Override
            public void onItemClickListener(int position) {
                index [pos]= position;
                Log.d("DetailsCategoryGiftSele",  pos +" " +  position+ " " );
                buttons[pos].setText(menuBean.getData().getFilters().get(pos).getChannels().get(position).getName());
                url[pos] = menuBean.getData().getFilters().get(pos).getChannels().get(position).getKey();
                switch (popupPosition){
                    case 0:
                        urlDetails = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&offset=0";
                        break;
                    case 1:
                        urlDetails = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&sort=hot&personality=&offset=0";
                        break;
                    case 2:
                        urlDetails = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&sort=price:asc&personality=&offset=0";
                        break;
                    case 3:
                        urlDetails = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&sort=price:desc&personality=&offset=0";
                        break;
                }
//                Log.d("DetailsCategoryGiftSele", urlDetails);
                netTools.getDataForEventBus(urlDetails, DetailsCategoryGiftBean.class);
                closeWindow();

            }
        });
        //menuFragment网络解析数据
        String urlMenu = URLValues.CATEGORY_GIFT_SELECT_MENU_FRAGMENT;
        netTools.getNormalData(urlMenu, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                menuBean = gson.fromJson(result, CategoryGiftSelectionMenuBean.class);

                MyChannelsBean myChannelsBean = new MyChannelsBean("", "全部");
                for (int i = 0; i < 4; i++) {
                    menuBean.getData().getFilters().get(i).getChannels().add(0, myChannelsBean);
                }
            }

            @Override
            public void onFailed(VolleyError error) {
            }
        });
    }

    private void selectionGift() {
        linearLayoutSelection = (LinearLayout) findViewById(R.id.linearlayout_category_gift_menu_selection);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout_category_gift_selection);
        btnTarget = (RadioButton) findViewById(R.id.btn_target_selection);
        btnScene = (RadioButton) findViewById(R.id.btn_scene_selection);
        btnPrice = (RadioButton) findViewById(R.id.btn_price_selection);
        btnPersonality = (RadioButton) findViewById(R.id.btn_personality_selection);
        btnTarget.setOnClickListener(this);
        btnScene.setOnClickListener(this);
        btnPrice.setOnClickListener(this);
        btnPersonality.setOnClickListener(this);
        btnTarget.setChecked(true);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout_category_gift_selection, menuFragment).commit();

    }

    private int id = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_details_category_gift_selection_gridview:
                finish();
                break;
            case R.id.menu_sort_details_category_gift_selection_gridview:
                myPopupWindow.showGiftMenuSortPopupWindow();
                break;
            default:
                showWindow(v);
                break;
        }

    }

    private void showWindow(View v) {
        //头列表button点击事件
        if (frameLayout.getVisibility() == View.GONE) {
            id = v.getId();
            frameLayout.setVisibility(View.VISIBLE);
            ColorDrawable drawable = new ColorDrawable(0x50000000);
            frameLayout1.setBackground(drawable);
            ObjectAnimator inAnimator = ObjectAnimator.ofFloat(frameLayout, "translationY", -1000, 0);
            inAnimator.setDuration(300);
            inAnimator.start();
            switch (v.getId()) {
                case R.id.btn_target_selection:
                    //将位置信息嵌入实体类中 发送
                    EventBus.getDefault().post(index[0]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(0));
                    pos = 0;
                    break;
                case R.id.btn_scene_selection:
                    EventBus.getDefault().post(index[1]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(1));
                    pos = 1;
                    break;
                case R.id.btn_personality_selection:
                    EventBus.getDefault().post(index[2]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(2));
                    pos = 2;
                    break;
                case R.id.btn_price_selection:
                    EventBus.getDefault().post(index[3]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(3));
                    pos = 3;
                    break;
            }

        } else if (v.getId() != id) {
            id = v.getId();
            //TODO 改变数据
            switch (v.getId()) {
                case R.id.btn_target_selection:
                    EventBus.getDefault().post(index[0]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(0));
                    pos = 0;
                    break;
                case R.id.btn_scene_selection:
                    EventBus.getDefault().post(index[1]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(1));
                    pos = 1;
                    break;
                case R.id.btn_personality_selection:
                    EventBus.getDefault().post(index[2]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(2));
                    pos = 2;
                    break;
                case R.id.btn_price_selection:
                    EventBus.getDefault().post(index[3]);
                    EventBus.getDefault().post(menuBean.getData().getFilters().get(3));
                    pos = 3;
                    break;
            }
        } else {
            closeWindow();
            id = 0;
        }
    }

    //关闭菜单窗口
    private void closeWindow() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(frameLayout, "translationY", 0, -1000);
        objectAnimator.setDuration(300);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ColorDrawable drawable = new ColorDrawable(0x00000000);
                frameLayout1.setBackground(drawable);
                frameLayout.setVisibility(View.GONE);
                btnTarget.setChecked(false);
                btnScene.setChecked(false);
                btnPrice.setChecked(false);
                btnPersonality.setChecked(false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    //判断点击位置在指定view上
    private boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())) {
            return false;
        }
        return true;
    }

    //复写
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (inRangeOfView(linearLayoutSelection, ev) == false) {
            closeWindow();
        }
        return super.dispatchTouchEvent(ev);
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

    //跳转
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (frameLayout.getVisibility() == View.GONE) {
            String purchaseUrl = giftBean.getData().getItems().get(position).getPurchase_url();
            String urlId = String.valueOf(giftBean.getData().getItems().get(position).getId());
            String titleName = giftBean.getData().getItems().get(position).getName();
            Intent intent = new Intent(this, DetailsPurchaseActivity.class);
            intent.putExtra("purchaseUrl", purchaseUrl);
            intent.putExtra("urlId", urlId);
            intent.putExtra("titleName", titleName);
            startActivity(intent);
        }

    }

    //popupWindow点击事件
    @Override
    public void getHotUrl() {
        popupPosition = 1;
//        String urlHot = URLValues.CATEGORY_GIFT_SELECT_HOT;
        String urlHot = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&sort=hot&personality=&offset=0";
        netTools.getDataForEventBus(urlHot, DetailsCategoryGiftBean.class);
    }

    @Override
    public void getDefaultUrl() {
        popupPosition = 0;
//        String urlDefault = URLValues.CATEGORY_GIFT_SELECT;
        String urlDefault = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&offset=0";
        netTools.getDataForEventBus(urlDefault, DetailsCategoryGiftBean.class);
    }

    @Override
    public void getPriceHighToLow() {
        popupPosition = 3;
//        String url = URLValues.CATEGORY_GIFT_SELECT_PRICEL_HIGHTOLOW;
        String urlHigh = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&sort=price:desc&personality=&offset=0";
        netTools.getDataForEventBus(urlHigh, DetailsCategoryGiftBean.class);
    }

    @Override
    public void getPriceLowToHigh() {
        popupPosition = 2;
//        String url = URLValues.CATEGORY_GIFT_SELECT_PRICEL_LOWTOHIGH;
        String urlLow = "http://api.liwushuo.com/v2/search/item_by_type?limit=20&target=" + url[0] + "&scene="
                + url[1] + "&price=" + url[3] + "&personality=" + url[2] + "&sort=price:asc&personality=&offset=0";
        netTools.getDataForEventBus(urlLow, DetailsCategoryGiftBean.class);
    }

    class MyChannelsBean extends CategoryGiftSelectionMenuBean.DataBean.FiltersBean.ChannelsBean {
        private String key ;
        private String name ;

        public MyChannelsBean(String key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }
    }
}
