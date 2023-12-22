package ge.msda.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private String name = "";
    private String age = "";

    private EditText LastNameEditText;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            this.name = extras.getString("NAME", "");
            this.age = String.valueOf(extras.getInt("AGE", 0));
        }

        LastNameEditText = findViewById(R.id.editTextLastName);
        finishButton = findViewById(R.id.buttonFinish);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lastName = LastNameEditText.getText().toString();

                Intent intent = new Intent(ThirdActivity.this, FinishActivity.class);
                intent.putExtra("AGE", age);
                intent.putExtra("NAME", name);
                intent.putExtra("LASTNAME", lastName);
                startActivity(intent);

                finish();

            }
        });

    }

}
