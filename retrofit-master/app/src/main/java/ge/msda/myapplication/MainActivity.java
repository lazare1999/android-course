package ge.msda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import ge.msda.myapplication.api.endpoints.ApiService;
import ge.msda.myapplication.api.endpoints.ApiService2;
import ge.msda.myapplication.api.models.Data;
import ge.msda.myapplication.api.models.PostUserResponseModel;
import ge.msda.myapplication.api.models.Posts;
import ge.msda.myapplication.api.models.Resource;
import ge.msda.myapplication.api.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = App.getInstance().getRetrofitClient().getApiService();
        ApiService2 apiService2 = App.getInstance().getRetrofitClient2().getApiService();


        apiService2.getPosts(1).enqueue(new Callback<Data<List<Posts>>>() {

            @Override
            public void onResponse(Call<Data<List<Posts>>> call, Response<Data<List<Posts>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Posts> users = response.body().getData();
                    for (int i = 0; i < users.size(); i++) {
                        Log.d("Posts", users.get(i).toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Data<List<Posts>>> call, Throwable t) {

            }

        });

        apiService.getUsers(1).enqueue(new Callback<Data<List<User>>>() {

            @Override
            public void onResponse(Call<Data<List<User>>> call, Response<Data<List<User>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body().getData();
                    for (int i = 0; i < users.size(); i++) {
                        Log.d("MyData", users.get(i).toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Data<List<User>>> call, Throwable t) {

            }

        });

        PostUserResponseModel modal = new PostUserResponseModel("lazare", "mia");
        apiService.postUser(modal).enqueue(new Callback<PostUserResponseModel>() {

            @Override
            public void onResponse(Call<PostUserResponseModel> call, Response<PostUserResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("PostUserResponseModel", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<PostUserResponseModel> call, Throwable t) {

            }

        });


        apiService.getResources(1).enqueue(new Callback<Data<List<Resource>>>() {

            @Override
            public void onResponse(Call<Data<List<Resource>>> call, Response<Data<List<Resource>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Resource> resource = response.body().getData();
                    for (int i = 0; i < resource.size(); i++) {
                        Log.d("MyData", resource.get(i).toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Data<List<Resource>>> call, Throwable t) {

            }

        });


    }

}