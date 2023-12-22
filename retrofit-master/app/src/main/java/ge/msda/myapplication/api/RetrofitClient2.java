package ge.msda.myapplication.api;

import java.util.concurrent.TimeUnit;

import ge.msda.myapplication.api.endpoints.ApiService;
import ge.msda.myapplication.api.endpoints.ApiService2;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private ApiService2 apiService;

    public RetrofitClient2() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService2.class);

    }

    public ApiService2 getApiService() {
        return apiService;
    }

}
