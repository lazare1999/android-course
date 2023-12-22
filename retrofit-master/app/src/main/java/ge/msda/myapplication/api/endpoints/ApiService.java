package ge.msda.myapplication.api.endpoints;

import java.util.List;

import ge.msda.myapplication.api.models.Data;
import ge.msda.myapplication.api.models.PostUserResponseModel;
import ge.msda.myapplication.api.models.Resource;
import ge.msda.myapplication.api.models.User;
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