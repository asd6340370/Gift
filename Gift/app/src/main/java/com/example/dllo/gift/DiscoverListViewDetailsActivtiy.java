package com.example.dllo.gift;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dllo on 16/5/28.
 */
public class DiscoverListViewDetailsActivtiy extends AppCompatActivity implements View.OnClickListener {


    private WebView webViewDiscoverDetails;
    private String url,title;
    private TextView tvTitleShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_listview_details);
        findViewById(R.id.back_title_discover_details).setOnClickListener(this);
        findViewById(R.id.checkBox_listview_discover_detail).setOnClickListener(this);
        findViewById(R.id.iv_share_listview_discover_details).setOnClickListener(this);
        findViewById(R.id.iv_comments_listview_discover_details).setOnClickListener(this);
        webViewDiscoverDetails = (WebView) findViewById(R.id.webView_listview_discover_details);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        loadWebData(url);




    }

    //加载WebView网络页面
    private void loadWebData(String url) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_webview_discover_details,null);
        tvTitleShow = (TextView) view.findViewById(R.id.tv_web_discover_details);
        if(title != null){
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
                if(webViewDiscoverDetails.canGoBack()){
                        webViewDiscoverDetails.goBack();
                }else {
                    finish();
                }
                break;
            case R.id.checkBox_listview_discover_detail:
                Toast.makeText(this, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_comments_listview_discover_details:
                Toast.makeText(this, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_share_listview_discover_details:
                Toast.makeText(this, "tu一下", Toast.LENGTH_SHORT).show();
                break;
            case R.id.webView_listview_discover_details:
                Toast.makeText(this, "tu一下", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
