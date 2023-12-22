package com.example.exam_1.ui.retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.exam_1.R;
import com.example.exam_1.config.App;
import com.example.exam_1.databinding.FragmentRetrofitBinding;
import com.example.exam_1.ui.retrofit.api.endpoints.ApiService;
import com.example.exam_1.ui.retrofit.api.models.Data;
import com.example.exam_1.ui.retrofit.api.models.PostUserResponseModel;
import com.example.exam_1.ui.retrofit.api.models.Resource;
import com.example.exam_1.ui.retrofit.api.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitFragment extends Fragment {

    private FragmentRetrofitBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentRetrofitBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        TextView textViewResponses = root.findViewById(R.id.textViewResponses);

        Button getUsers = root.findViewById(R.id.getUsers);
        Button getResources = root.findViewById(R.id.getResources);
        Button postUser = root.findViewById(R.id.postUser);

        ApiService apiService = App.getInstance().getRetrofitClient().getApiService();

        getUsers.setOnClickListener(v -> {
            textViewResponses.setText("");
            apiService.getUsers(1).enqueue(new Callback<Data<List<User>>>() {

                @Override
                public void onResponse(Call<Data<List<User>>> call, Response<Data<List<User>>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<User> users = response.body().getData();
                        for (int i = 0; i < users.size(); i++) {

                            textViewResponses.setText(textViewResponses.getText() +" "+ users.get(i).toString());

                        }
                    }
                }

                @Override
                public void onFailure(Call<Data<List<User>>> call, Throwable t) {

                }

            });
        });

        getResources.setOnClickListener(v -> {
            textViewResponses.setText("");

            apiService.getResources(1).enqueue(new Callback<Data<List<Resource>>>() {

                @Override
                public void onResponse(Call<Data<List<Resource>>> call, Response<Data<List<Resource>>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Resource> resource = response.body().getData();
                        for (int i = 0; i < resource.size(); i++) {
                            textViewResponses.setText(textViewResponses.getText() +" "+ resource.get(i).toString());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Data<List<Resource>>> call, Throwable t) {

                }

            });
        });

        postUser.setOnClickListener(v -> {
            textViewResponses.setText("");

            PostUserResponseModel modal = new PostUserResponseModel("lazare", "mia");
            apiService.postUser(modal).enqueue(new Callback<PostUserResponseModel>() {

                @Override
                public void onResponse(Call<PostUserResponseModel> call, Response<PostUserResponseModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        textViewResponses.setText(textViewResponses.getText() +" "+ response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<PostUserResponseModel> call, Throwable t) {

                }

            });
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
