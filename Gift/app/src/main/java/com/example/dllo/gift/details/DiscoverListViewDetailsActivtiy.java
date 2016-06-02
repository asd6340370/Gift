package com.example.dllo.gift.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dllo.gift.LoginActivity;
import com.example.dllo.gift.R;
import com.example.dllo.gift.comments.CommentsActivity;
import com.example.dllo.gift.discover.disbean.ListBean;
import com.example.dllo.gift.tools.MyPopupWindow;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/28.
 */
public class DiscoverListViewDetailsActivtiy extends AppCompatActivity implements View.OnClickListener {


    private WebView webViewDiscoverDetails;
    private String url, title;
    private TextView tvTitleShow;
    private ListBean listBean;
    private int position;
    private MyPopupWindow popupWindow;
    private ArrayList<ListBean.DataBean.ItemsBean> itemsBeens;
    private CheckBox checkBoxLikesDiscoverDetail;
    private TextView tvShareDiscoverDetails, tvCommentsDiscoverDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_listview_details);
        findViewById(R.id.back_title_discover_details).setOnClickListener(this);

        checkBoxLikesDiscoverDetail = (CheckBox) findViewById(R.id.checkBox_listview_discover_detail);
        checkBoxLikesDiscoverDetail.setOnClickListener(this);
        tvShareDiscoverDetails = (TextView) findViewById(R.id.iv_share_listview_discover_details);
        tvShareDiscoverDetails.setOnClickListener(this);
        tvCommentsDiscoverDetails = (TextView) findViewById(R.id.iv_comments_listview_discover_details);
        tvCommentsDiscoverDetails.setOnClickListener(this);
        webViewDiscoverDetails = (WebView) findViewById(R.id.webView_listview_discover_details);


        //接受数据
        Intent intent = getIntent();
        itemsBeens = intent.getParcelableArrayListExtra("bean");
        title = itemsBeens.get(position).getTitle();
        position = intent.getIntExtra("position", 0);

        checkBoxLikesDiscoverDetail.setText(String.valueOf(itemsBeens.get(position).getLikes_count()));




        url = itemsBeens.get(position).getUrl();
//        Log.d("DiscoverListViewDetails", url);
        if (url != null) {
            loadWebData(url);
        }
        //初始化popupWindow
        popupWindow = new MyPopupWindow(this, R.id.iv_share_listview_discover_details);
    }

    //加载WebView网络页面
    private void loadWebData(String url) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_webview_discover_details, null);
        tvTitleShow = (TextView) view.findViewById(R.id.tv_web_discover_details);
        if (title != null) {
            tvTitleShow.setText(title);
        }
        webViewDiscoverDetails.addView(view);
        webViewDiscoverDetails.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webViewDiscoverDetails.getSettings().setJavaScriptEnabled(true);
        webViewDiscoverDetails.loadUrl(url);

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
            case R.id.back_title_discover_details:
                if (webViewDiscoverDetails.canGoBack()) {
                    webViewDiscoverDetails.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.checkBox_listview_discover_detail:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_comments_listview_discover_details:
                Intent commentsIntent = new Intent(this, CommentsActivity.class);
                String id = String.valueOf(itemsBeens.get(position).getId());
                commentsIntent.putExtra("id",id);
                startActivity(commentsIntent);
                break;
            case R.id.iv_share_listview_discover_details:
                popupWindow.showSharePopupWindow();
                break;


        }
    }


}
