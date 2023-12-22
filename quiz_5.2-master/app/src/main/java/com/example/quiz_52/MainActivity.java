package com.example.quiz_52;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quiz_52.guns.Gun;
import com.example.quiz_52.guns.GunDao;

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

        GunDao dap = App.getInstance().getAppDatabase().getGunDao();

        dap.deleteAll();
        Gun gun = new Gun(1L, 12.0F, "Walther M13", 2021);
        Gun gun1 = new Gun(2L, 0.308F, "Gatling gun", 1861);
        dap.insert(gun, gun1);
    }

    private void init() {
        editTextName = findViewById(R.id.editTextName);
        searchButton = findViewById(R.id.buttonSearch);
        textViewResults = findViewById(R.id.textViewResults);
    }

    private void registerListeners() {
        searchButton.setOnClickListener(view -> {

            String name = editTextName.getText().toString();

            GunDao dap = App.getInstance().getAppDatabase().getGunDao();
            List<Gun> select = dap.getByName("%"+name+"%");

            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < select.size(); i++) {
                ans.append(select.get(i).getNameAndCaliberAndYear()).append("\n");
            }
            textViewResults.setText(ans.toString());

        });


    }

}