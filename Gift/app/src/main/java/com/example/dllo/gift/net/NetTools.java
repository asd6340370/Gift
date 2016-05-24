package com.example.dllo.gift.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by dllo on 16/5/24.
 */
public class NetTools {
    private RequestQueue queue;

    public NetTools() {
        queue = VolleySingleton.instance().getRequestQueue();
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

}
