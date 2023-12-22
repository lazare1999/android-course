package ge.msda.myapplication.api.endpoints;

import java.util.List;

import ge.msda.myapplication.api.models.Data;
import ge.msda.myapplication.api.models.Posts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService2 {

    @GET("posts")
    Call<Data<List<Posts>>> getPosts(@Query("page") int page);

}

