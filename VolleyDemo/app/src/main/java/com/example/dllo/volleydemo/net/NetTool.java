package com.example.dllo.volleydemo.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by dllo on 16/5/23.
 * 所有网络请求的工具
 */
public class NetTool {
    private RequestQueue requestQueue ;
    private ImageLoader imageLoader;

    //获取首页轮播图

    public NetTool() {
        requestQueue = VolleySingleton.getInstance().getRequestQueue();
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    public void getMainBanners(final NetListenter netListenter){
        StringRequest request = new StringRequest(URLValues.MAIN_BANNERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            netListenter.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            netListenter.onFailed(error);
            }
        });
        requestQueue.add(request);
    }

}
