package com.example.dllo.gift.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.MyApp;
import com.example.dllo.gift.discover.BannerBean;
import com.example.dllo.gift.discover.SpecialListBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/5/24.
 */
public class NetTools {
    private RequestQueue queue;

    public NetTools() {
        queue = VolleySingleton.instance().getRequestQueue();
    }

    public void getHotData (final NetListener listener){
        StringRequest request = new StringRequest(URLValues.HOT_URL_NEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            listener.onSuccessed(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            listener.onFailed(error);
            }
        });
        queue.add(request);
    }

    public void getBanner(){
        StringRequest request = new StringRequest(URLValues.DISCOVER_BANNER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BannerBean bannerBean =  gson.fromJson(response, BannerBean.class);
                EventBus.getDefault().post(bannerBean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    public void getSpecialList(){
        StringRequest request = new StringRequest(URLValues.DISCOVER_SPECIAL_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SpecialListBean listBean = gson.fromJson(response,SpecialListBean.class);
                EventBus.getDefault().post(listBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

}
