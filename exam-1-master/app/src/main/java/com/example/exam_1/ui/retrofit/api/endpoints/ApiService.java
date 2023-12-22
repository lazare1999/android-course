package com.example.exam_1.ui.retrofit.api.endpoints;

import com.example.exam_1.ui.retrofit.api.models.Data;
import com.example.exam_1.ui.retrofit.api.models.PostUserResponseModel;
import com.example.exam_1.ui.retrofit.api.models.Resource;
import com.example.exam_1.ui.retrofit.api.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users")
    Call<Data<List<User>>> getUsers(@Query("page") int page);

    @POST("users")
    Call<PostUserResponseModel> postUser(@Body PostUserResponseModel modal);

    @GET("unknown")
    Call<Data<List<Resource>>> getResources(@Query("page") int page);

}