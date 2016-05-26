package com.example.dllo.gift.nettools;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.discover.disbean.BannerBean;
import com.example.dllo.gift.discover.disbean.SpecialListBean;
import com.example.dllo.gift.discover.disbean.SpecialListHeaderBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/5/24.
 */
public class NetTools {
    private Gson gson;
    private RequestQueue queue;
    private EventBus eventBus;

    public NetTools() {
        queue = VolleySingleton.instance().getRequestQueue();
        gson = new Gson();
        eventBus = EventBus.getDefault();
    }

    public void getHotData (final NetListener listener){
        StringRequest request = new StringRequest(URLValues.HOT_URL, new Response.Listener<String>() {
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

    public void getDiscoverBanner(){
        StringRequest request = new StringRequest(URLValues.DISCOVER_SPECIAL_BANNER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BannerBean bannerBean =  gson.fromJson(response, BannerBean.class);
                eventBus.post(bannerBean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    public void getDiscoverSpecialListHeader(){
        StringRequest request = new StringRequest(URLValues.DISCOVER_SPECIAL_LIST_Header, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                SpecialListHeaderBean listBean = gson.fromJson(response,SpecialListHeaderBean.class);
                eventBus.post(listBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    public void getDiscoverSpecialList(){
        StringRequest request = new StringRequest(URLValues.DISCOVER_SPECIAL_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                SpecialListBean specialListBean = gson.fromJson(response,SpecialListBean.class);
                eventBus.post(specialListBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);

    }

}
