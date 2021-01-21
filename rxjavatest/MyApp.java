package com.sindabad.rxjavatest;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Repository.getRetrofitInstance();
    }
}
