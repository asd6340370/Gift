package com.example.dllo.gift;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.gift.hot.HotBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dllo on 16/5/24.
 */
public class DetailsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private WebView webViewDetails;
    private String url;
    private CheckBox checkBoxTitleDetails;
    private ImageView backTitleDetails;
    private TextView tvTitleDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webViewDetails = (WebView) findViewById(R.id.webView_details_activity);
        checkBoxTitleDetails = (CheckBox) findViewById(R.id.checkBox_title_detail);
        backTitleDetails = (ImageView) findViewById(R.id.back_title_details);
        tvTitleDetails = (TextView) findViewById(R.id.tv_title_hot_details);
        checkBoxTitleDetails.setOnCheckedChangeListener(this);
        backTitleDetails.setOnClickListener(this);
        loadWeb();


    }

    //加载web数据
    private void loadWeb() {
        //接收HotFragment传来的数据
        Intent intent = getIntent();
        HotBean.DataBean.ItemsBean.DataItem dataItem = intent.getParcelableExtra("buy");
        url = dataItem.getPurchase_url();
        tvTitleDetails.setText(dataItem.getName());
//        Log.d("DetailsActivity", url);
        webViewDetails.getSettings().setJavaScriptEnabled(true);
        //优先使用缓存
        webViewDetails.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webViewDetails.loadUrl(url);
        webViewDetails.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webViewDetails.loadUrl(url);
                return true;

//                if( url.startsWith("http:") || url.startsWith("https:") ) {
//                    return false;
//                }
//                // Otherwise allow the OS to handle things like tel, mailto, etc.
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity( intent );
//                return true;
            }
        });
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
