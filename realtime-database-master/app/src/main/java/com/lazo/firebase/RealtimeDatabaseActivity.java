package com.lazo.firebase;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lazo.firebase.models.UserModel;


public class RealtimeDatabaseActivity extends AppCompatActivity {

    private EditText username;
    private EditText imageUrl;
    private Button renewImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);

        init();
        changeImageUrlListeners();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null)
            return;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(currentUser.getUid());


        myRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful())
                return;

            if (task.getResult() ==null)
                return;

            UserModel m = task.getResult().getValue(UserModel.class);

            if (m == null)
                return;

            username.setText(m.username);
            imageUrl.setText(m.imageUrl);

            ImageView imageView = findViewById(R.id.my_image_view);

            String url = String.valueOf(imageUrl.getText());

            Glide.with(this).load(url).into(imageView);

        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel value = dataSnapshot.getValue(UserModel.class);

                if (value == null)
                    return;

                username.setText(value.username);
                imageUrl.setText(value.imageUrl);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("aaaa", "Failed to read value.", error.toException());
            }
        });


    }


    private void init() {
        username = findViewById(R.id.username);
        imageUrl = findViewById(R.id.imageUrl);
        renewImageButton = findViewById(R.id.renewImageButton);
    }

    private void changeImageUrlListeners() {


        renewImageButton.setOnClickListener(view -> {

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null)
                return;

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users").child(currentUser.getUid());

            UserModel m = new UserModel();
            m.imageUrl = String.valueOf(imageUrl.getText());
            m.username = String.valueOf(username.getText());
            myRef.setValue(m);

            ImageView imageView = findViewById(R.id.my_image_view);

            String url = String.valueOf(imageUrl.getText());

            Glide.with(this).load(url).into(imageView);

        });
    }


}
