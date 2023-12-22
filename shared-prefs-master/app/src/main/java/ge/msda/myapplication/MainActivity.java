package ge.msda.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText noteEditText;
    private Button addButton;
    private Button clearButton;
    private TextView notesTextView;

    private SharedPreferences notesPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

        this.registerListeners();

        notesPref = getSharedPreferences("Notes Pref", MODE_PRIVATE);


//        String name = App.getInstance().getName();

        String note = notesPref.getString("NOTES", "");

        notesTextView.setText(note);

    }

    private void init() {
        noteEditText = findViewById(R.id.editTextNote);
        addButton = findViewById(R.id.buttonAdd);
        clearButton = findViewById(R.id.buttonClear);
        notesTextView = findViewById(R.id.textViewNotes);
    }

    private void registerListeners() {
        addButton.setOnClickListener(view -> {

            String note = noteEditText.getText().toString();
            String notes = notesTextView.getText().toString();

            String result = notes + "\n" + note;

            notesTextView.setText(result);

            noteEditText.setText("");

            notesPref.edit()
                    .putString("NOTES", result)
                    .apply();

        });

        clearButton.setOnClickListener(view -> {

            notesTextView.setText("");

            notesPref.edit()
                    .putString("NOTES", "")
                    .apply();

        });
    }

}