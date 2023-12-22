package ge.msda.myapplication;

import android.app.Application;

import ge.msda.myapplication.api.RetrofitClient;
import ge.msda.myapplication.api.RetrofitClient2;

public class App extends Application {

    private static App instance;

    private RetrofitClient retrofitClient;
    private RetrofitClient2 retrofitClient2;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        retrofitClient = new RetrofitClient();
        retrofitClient2 = new RetrofitClient2();

    }

    public static App getInstance() {
        return instance;
    }

    public RetrofitClient getRetrofitClient() {
        return retrofitClient;
    }

    public RetrofitClient2 getRetrofitClient2() {
        return retrofitClient2;
    }


}