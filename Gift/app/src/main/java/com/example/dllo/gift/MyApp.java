package com.example.dllo.gift;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/5/24.
 */
public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
