package ge.msda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private String name = "";

    private EditText ageEditText;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            this.name = extras.getString("NAME", "");
        }

        ageEditText = findViewById(R.id.editTextAge);
        finishButton = findViewById(R.id.buttonNext);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int age = Integer.parseInt(ageEditText.getText().toString());

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("AGE", age);
                intent.putExtra("NAME", name);
                startActivity(intent);

                finish();

            }
        });

    }
}