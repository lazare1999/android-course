package com.lazo.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordChangeActivity extends AppCompatActivity {


    private EditText newPassword;
    private EditText oldPassword;
    private Button changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);


        init();

        changePasswordListeners();

    }


    private void init() {

        newPassword = findViewById(R.id.newPassword);
        oldPassword = findViewById(R.id.oldPassword);
        changePasswordButton = findViewById(R.id.changePasswordButton);

    }

    private void changePasswordListeners() {

        changePasswordButton.setOnClickListener(view -> {

            String newP = newPassword.getText().toString();
            String oldP = oldPassword.getText().toString();

            if (TextUtils.isEmpty(newP)) {
                Toast.makeText(PasswordChangeActivity.this, "Empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user ==null) {
                Toast.makeText(PasswordChangeActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                return;
            }

            String email = user.getEmail();

            if (email == null || TextUtils.isEmpty(email)) {
                Toast.makeText(PasswordChangeActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                return;
            }

            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, oldP);

            user.reauthenticate(credential)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            user.updatePassword(newP).addOnCompleteListener(task1 -> {
                                                if (task1.isSuccessful()) {
                                                    Toast.makeText(PasswordChangeActivity.this, "Password updated!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(PasswordChangeActivity.this, MainActivity.class));
                                                } else {
                                                    Toast.makeText(PasswordChangeActivity.this, "Error password not updated", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        } else {
                                            Toast.makeText(PasswordChangeActivity.this, "Error auth failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });


        });

    }

}