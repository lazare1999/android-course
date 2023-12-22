package com.example.exam_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.exam_1.databinding.ActivityMainBinding;
import com.example.exam_1.ui.sql_lite.guns.GunDbHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "შუალედური გამოცა", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_calc, R.id.nav_notes, R.id.nav_sql_lite, R.id.nav_retrofit)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        resultTextView = findViewById(R.id.resultTextView);

        GunDbHelper gunDbHelper = new GunDbHelper(this);

        gunDbHelper.deleteAll();
        gunDbHelper.insert("Walther M13", 2021, 12.0F);
        gunDbHelper.insert("Gatling gun", 1861, 0.308F);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.logoutButton) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        if (item.getItemId() == R.id.passwordChangeButton) {
            startActivity(new Intent(MainActivity.this, PasswordChangeActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    //კალკულატორი
    private TextView resultTextView;
    private double operand = 0;
    private String operation = "";

    public void numberClick(View view) {

        if (!(view instanceof TextView)) {
            return;
        }

        TextView clicked = (TextView) view;

        String number = clicked.getText().toString();
        String res = resultTextView.getText().toString();

        if (Objects.equals(res, "0")) {
            res = "";
        }

        String result = res + number;

        resultTextView.setText(result);

    }

    public void operationClick(View view) {

        if (!(view instanceof TextView)) {
            return;
        }

        if (!TextUtils.isEmpty(resultTextView.getText())) {
            operand = Double.parseDouble(resultTextView.getText().toString());
        }

        resultTextView.setText("");

        operation = ((TextView) view).getText().toString();

    }

    public void equalsClick(View view) {

        String secOperandText = resultTextView.getText().toString();
        double secOperand = 0.0;

        if (!TextUtils.isEmpty(secOperandText)) {
            secOperand = Double.parseDouble(secOperandText);
        }

        switch (this.operation) {
            case "+":
                resultTextView.setText(String.valueOf(operand + secOperand));
                break;
            case "-":
                resultTextView.setText(String.valueOf(operand - secOperand));
                break;
            case "*":
                resultTextView.setText(String.valueOf(operand * secOperand));
                break;
            case "/":
                resultTextView.setText(String.valueOf(operand / secOperand));
                break;
            case "pow":
                resultTextView.setText(String.valueOf(Math.pow(operand, secOperand)));
                break;
        }

    }

    public void clear(View view) {
        resultTextView.setText("");
        operand = 0;
        operation = "";
    }
    //კალკულატორი

}