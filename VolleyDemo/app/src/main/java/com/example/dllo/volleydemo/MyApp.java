package com.example.dllo.volleydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/5/23.
 * 全局初始化的 一些
 */
public class MyApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
