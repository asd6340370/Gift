package com.example.dllo.gift.nettools;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.dllo.gift.MyApp;

/**
 * Created by dllo on 16/5/24.
 */
public class VolleySingleton {
    private RequestQueue requestQueue;

    private static VolleySingleton volleySingleton = new VolleySingleton();

    public static VolleySingleton instance() {
        return volleySingleton;
    }

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyApp.context);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
