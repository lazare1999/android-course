package com.example.test.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.todo.ToDoDbHelper;
import com.example.test.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button buttonAdd = binding.buttonAdd;

        buttonAdd.setOnClickListener(view -> {

            if (binding.editTextTitleHome.getText() ==null || binding.editTextTitleHome.getText().toString().isEmpty() ||
                    binding.editTextDescriptionHome.getText() ==null || binding.editTextDescriptionHome.getText().toString().isEmpty()
            ) {
                Toast.makeText(getActivity(), "შეავსეთ გრაფები", Toast.LENGTH_SHORT).show();
                return;
            }

            ToDoDbHelper bookDbHelper = new ToDoDbHelper(getContext());

            String title = binding.editTextTitleHome.getText().toString();
            String description = binding.editTextDescriptionHome.getText().toString();

            bookDbHelper.insert(title, description);


            Toast.makeText(getActivity(), "წარმატებით დაემატა", Toast.LENGTH_SHORT).show();


        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}