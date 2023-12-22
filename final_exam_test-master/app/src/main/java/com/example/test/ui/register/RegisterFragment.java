package com.example.test.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.ProfileActivity;
import com.example.test.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button buttonRegister = binding.buttonRegister;

        buttonRegister.setOnClickListener(view -> {

            String email = binding.editTextEmail.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            String password2 = binding.editTextPassword2.getText().toString();

            if (TextUtils.isEmpty(email) ||
                    TextUtils.isEmpty(password) ||
                    TextUtils.isEmpty(password2)
            ) {
                return;
            }

            if (!Objects.equals(password, password2)) {
                Toast.makeText(getContext(),"passwords aren't same",Toast.LENGTH_SHORT).show();
                return;
            }

            if (!email.matches(emailPattern)) {
                Toast.makeText(getContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getActivity(), ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(),"ERROR!",Toast.LENGTH_SHORT).show();
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