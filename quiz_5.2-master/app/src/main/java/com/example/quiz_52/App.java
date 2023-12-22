package com.example.quiz_52;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    private static App instance;

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "APP_DB"
        ).allowMainThreadQueries().build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}