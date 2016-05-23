package com.example.dllo.volleydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.example.dllo.volleydemo.net.NetListenter;
import com.example.dllo.volleydemo.net.NetTool;
import com.example.dllo.volleydemo.net.VolleySingleton;

/**
 * Created by dllo on 16/5/23.
 */
public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        NetTool tool = new NetTool();
        tool.getMainBanners(new NetListenter() {
            @Override
            public void onSuccessed(String result) {

            }

            @Override
            public void onFailed(VolleyError error) {
            }
        });

    }
}
