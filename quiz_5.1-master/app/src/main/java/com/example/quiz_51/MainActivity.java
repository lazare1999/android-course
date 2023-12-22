package com.example.quiz_51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quiz_51.guns.GunDbHelper;
import com.example.quiz_51.guns.GunModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button searchButton;
    private TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

        this.registerListeners();


        GunDbHelper gunDbHelper = new GunDbHelper(this);

        gunDbHelper.deleteAll();
        gunDbHelper.insert("Walther M13", 2021, 12.0F);
        gunDbHelper.insert("Gatling gun", 1861, 0.308F);

    }

    private void init() {
        editTextName = findViewById(R.id.editTextName);
        searchButton = findViewById(R.id.buttonSearch);
        textViewResults = findViewById(R.id.textViewResults);
    }

    private void registerListeners() {
        searchButton.setOnClickListener(view -> {

            GunDbHelper gunDbHelper = new GunDbHelper(this);
            String name = editTextName.getText().toString();

            List<GunModel> select = gunDbHelper.select(name);

            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < select.size(); i++) {
                ans.append(select.get(i).getNameAndCaliberAndYear()).append("\n");
            }
            textViewResults.setText(ans.toString());

        });


    }
}