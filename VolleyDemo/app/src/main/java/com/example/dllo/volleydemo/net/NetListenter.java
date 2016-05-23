package com.example.dllo.volleydemo.net;

import com.android.volley.VolleyError;

/**
 * Created by dllo on 16/5/23.
 */
public interface NetListenter {
    void onSuccessed(String result);
    void onFailed(VolleyError error);

}
