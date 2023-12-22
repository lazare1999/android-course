package com.example.exam_1.config;

import android.app.Application;

import com.example.exam_1.ui.retrofit.api.RetrofitClient;
import com.example.exam_1.ui.sql_lite.guns.GunDbHelper;

public class App extends Application {

    private static App instance;
    private static GunDbHelper gunDbHelper;
    private RetrofitClient retrofitClient;

    private String name;

    public static GunDbHelper getGunDbHelper() {
        return gunDbHelper;
    }

    public RetrofitClient getRetrofitClient() {
        return retrofitClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        gunDbHelper = new GunDbHelper(this);
        retrofitClient = new RetrofitClient();
    }

    public static App getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}