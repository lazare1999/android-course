package com.lazo.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button logoutButton;
    private Button realtimeDatabase;
    private Button passwordChangeButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        registerListeners();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            textView.setText(currentUser.getEmail());
        }

    }

    private void init() {

        logoutButton = findViewById(R.id.logoutButton);
        passwordChangeButton = findViewById(R.id.passwordChangeButton);
        realtimeDatabase = findViewById(R.id.realtimeDatabase);
        textView = findViewById(R.id.textView);

    }

    private void registerListeners() {

        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        passwordChangeButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PasswordChangeActivity.class));
            finish();
        });

        realtimeDatabase.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RealtimeDatabaseActivity.class));
            finish();
        });


    }
}