package com.example.dllo.gift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by dllo on 16/5/24.
 */
public class DetailsActivity extends AppCompatActivity {

    private WebView webViewDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webViewDetails = (WebView) findViewById(R.id.webView_details_activity);

    }
}
