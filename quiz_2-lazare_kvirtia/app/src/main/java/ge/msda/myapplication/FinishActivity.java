package ge.msda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            textView.setText(extras.getString("NAME", ""));
            textView2.setText(extras.getString("AGE", ""));
            textView3.setText(extras.getString("LASTNAME", ""));

        }

    }
}