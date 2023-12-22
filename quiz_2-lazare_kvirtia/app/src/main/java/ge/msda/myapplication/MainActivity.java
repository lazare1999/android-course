package ge.msda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";

    private Button nextButton;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        nextButton = findViewById(R.id.buttonNext);
        nameEditText = findViewById(R.id.editTextPersonName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("NAME", name);
                startActivity(intent);

                finish();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");

    }

}