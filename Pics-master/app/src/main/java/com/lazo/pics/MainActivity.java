package com.lazo.pics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    protected ImageView imageView;

    ActivityResultLauncher<Uri> uriActivityResultLauncher;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        uriActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    imageView.setImageURI(uri);
                } else {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @OnClick(R.id.button)
    protected void buttonClick() {

        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "My Application Directory");

        if(!path.exists()) {
            path.mkdir();
        }

        File imageFile = null;

        try {
            imageFile = File.createTempFile("MyFile", ".jpg", path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", imageFile);

        uriActivityResultLauncher.launch(uri);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}