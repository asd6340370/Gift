package com.example.dllo.gift.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.gift.LoginActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.bmob.BmobData;
import com.example.dllo.gift.bmob.UserBmobBean;
import com.example.dllo.gift.comments.CommentsActivity;
import com.example.dllo.gift.details.detailsbean.DetailsRaiderBean;
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.nettools.NetTools;
import com.example.dllo.gift.nettools.URLValues;
import com.example.dllo.gift.tools.MyPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/5/28.
 */
public class DetailsRaiderActivtiy extends AppCompatActivity implements View.OnClickListener {


    private WebView webViewDiscoverDetails;
    private String url, title;
    private TextView tvTitleShow;
    private ListBean listBean;
    private int position;
    private MyPopupWindow popupWindow;
    private ArrayList<ListBean.DataBean.ItemsBean> itemsBeens;
    private CheckBox checkBoxLikesDiscoverDetail;
    private TextView tvShareDiscoverDetails, tvCommentsDiscoverDetails;
    private NetTools netTools;
    private String urlId;
    private DetailsRaiderBean raiderBean;
    private UserBmobBean getUserBmobBean;
    private BmobData bmobData;
    private UserBmobBean userBmobBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_listview_details);
        //注册EventBus
        EventBus.getDefault().register(this);
        findViewById(R.id.back_title_discover_details).setOnClickListener(this);
        checkBoxLikesDiscoverDetail = (CheckBox) findViewById(R.id.checkBox_listview_discover_detail);
        checkBoxLikesDiscoverDetail.setOnClickListener(this);
        tvShareDiscoverDetails = (TextView) findViewById(R.id.iv_share_listview_discover_details);
        tvShareDiscoverDetails.setOnClickListener(this);
        tvCommentsDiscoverDetails = (TextView) findViewById(R.id.iv_comments_listview_discover_details);
        tvCommentsDiscoverDetails.setOnClickListener(this);
        webViewDiscoverDetails = (WebView) findViewById(R.id.webView_listview_discover_details);


        ArrayList<String> idData = new ArrayList<>();
        //接受页面传递的数据
        Intent intent = getIntent();
        idData = intent.getStringArrayListExtra("idArray");
        position = intent.getIntExtra("position", 0);
        String urlIdFavorite = intent.getStringExtra("urlId");
        if (idData != null) {
            //获取当前界面id 用于url拼接 和传到评论页面
            urlId = idData.get(position);
        } else {
            urlId = urlIdFavorite;
        }

        netTools = new NetTools();
        //解析网络数据
        netTools.getDataForEventBus(URLValues.DETAILS_RAIDER_BEFORE + urlId + URLValues.DETAILS_RAIDER_AFTER,
                DetailsRaiderBean.class);

        //初始化popupWindow
        popupWindow = new MyPopupWindow(this, R.id.iv_share_listview_discover_details);

        if (BmobUser.getCurrentUser(this) != null){
            //查询数据库 checkbox 状态
            bmobData = new BmobData(this);
            bmobData.queryIsLike(urlId, checkBoxLikesDiscoverDetail,BmobUser.getCurrentUser(this).getUsername());
        }
    }

    //收到网络解析数据后 进行设置
    @Subscribe
    public void getDetailsRaiderData(DetailsRaiderBean bean) {
        this.raiderBean = bean;
        checkBoxLikesDiscoverDetail.setText(String.valueOf(bean.getData().getLikes_count()));
        tvShareDiscoverDetails.setText(String.valueOf(bean.getData().getShares_count()));
        tvCommentsDiscoverDetails.setText(String.valueOf(bean.getData().getComments_count()));
        title = bean.getData().getTitle();
        //当前webView 的url
        url = bean.getData().getUrl();
        //        Log.d("DiscoverListViewDetails", url);
        if (url != null) {
            loadWebData(url);
        }
        if (BmobUser.getCurrentUser(this) != null){
            getUserBmobBean = new UserBmobBean();
            getUserBmobBean.setKey("raider");
            getUserBmobBean.setUserName(BmobUser.getCurrentUser(this).getUsername());
            getUserBmobBean.setTitleName(bean.getData().getTitle());
            getUserBmobBean.setImgUrl(bean.getData().getCover_image_url());
            getUserBmobBean.setId(String.valueOf(bean.getData().getId()));
        }

    }

    //加载WebView网络页面
    private void loadWebData(String url) {
        //webView 添加 view
        View view = LayoutInflater.from(this).inflate(R.layout.view_webview_discover_details, null);
        tvTitleShow = (TextView) view.findViewById(R.id.tv_web_discover_details);
        tvTitleShow.setText(title);
        webViewDiscoverDetails.addView(view);
        //webView优先使用缓存
        webViewDiscoverDetails.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //网页中有js数据时
        webViewDiscoverDetails.getSettings().setJavaScriptEnabled(true);
        webViewDiscoverDetails.loadUrl(url);
        //调用setWebViewClient方法 使不启用系统浏览器
        webViewDiscoverDetails.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //true为不调用系统浏览器
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.back_title_discover_details:
                if (webViewDiscoverDetails.canGoBack()) {
                    webViewDiscoverDetails.goBack();
                } else {
                    finish();
                }
                break;
            //喜欢
            case R.id.checkBox_listview_discover_detail:
                //TODO checkBox
                if (BmobUser.getCurrentUser(this) != null){
                    bmobData.CheckBoxSelectedSetData(checkBoxLikesDiscoverDetail, urlId, getUserBmobBean, BmobUser.getCurrentUser(this).getUsername());
                }
                break;
            //评论
            case R.id.iv_comments_listview_discover_details:
                if (raiderBean != null) {
                    Intent commentsIntent = new Intent(this, CommentsActivity.class);
                    commentsIntent.putExtra("urlId", urlId);
                    startActivity(commentsIntent);
                }
                break;
            //分享
            case R.id.iv_share_listview_discover_details:
                if (raiderBean != null) {
                    popupWindow.showSharePopupWindow();
                }
                break;
        }

    }

    public void CheckBoxSelectedSetData(final CheckBox checkBox, String urlId) {
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if (bmobUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            checkBox.setChecked(false);
        } else if (checkBox.isChecked()) {
            userBmobBean = new UserBmobBean();
            userBmobBean = getUserBmobBean;
            userBmobBean.save(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(DetailsRaiderActivtiy.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(DetailsRaiderActivtiy.this, "收藏失败", Toast.LENGTH_SHORT).show();
                    checkBox.setChecked(false);
                }
            });
        } else {
            Toast.makeText(this, "取消喜欢成功", Toast.LENGTH_SHORT).show();
            BmobQuery<UserBmobBean> query = new BmobQuery<>();
            //条件查询
            query.addWhereEqualTo("id", urlId);
            //查询到对应id的数据库内容
            query.findObjects(this, new FindListener<UserBmobBean>() {
                @Override
                public void onSuccess(List<UserBmobBean> list) {

                    //遍历这个List
                    for (UserBmobBean bmobCollectBean : list) {
                        //进行删除
                        bmobCollectBean.delete(DetailsRaiderActivtiy.this, new DeleteListener() {
                            @Override
                            public void onSuccess() {
                                //删除成功
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(DetailsRaiderActivtiy.this, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消EventBus注册
        EventBus.getDefault().unregister(this);
    }
}
