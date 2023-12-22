package ge.msda.myapplication.config;

import android.app.Application;

public class App extends Application {

    private static App instance;

    private String name;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        name = "lazoo";

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