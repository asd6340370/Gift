package com.example.dllo.gift;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/5/24.
 */
public class MyApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Bmob.initialize(this,"46d07e2beb9f052a455583c33a51139e");
    }
}
