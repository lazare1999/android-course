package com.example.exam_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        init();

        changePasswordListeners();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(PasswordChangeActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void init() {

        newPassword = findViewById(R.id.newPassword);
        oldPassword = findViewById(R.id.oldPassword);
        changePasswordButton = findViewById(R.id.changePasswordButton);

    }

    private void changePasswordListeners() {

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    user.updatePassword(newP).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(PasswordChangeActivity.this, "Password updated!", Toast.LENGTH_SHORT).show();
                                                                startActivity(new Intent(PasswordChangeActivity.this, MainActivity.class));
                                                            } else {
                                                                Toast.makeText(PasswordChangeActivity.this, "Error password not updated", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                } else {
                                                    Toast.makeText(PasswordChangeActivity.this, "Error auth failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


            }
        });

    }

}