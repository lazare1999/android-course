package ge.msda.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {

    private EditText editTextEmailAddress;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        init();

        registerListeners();

    }

    private void init() {

        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        sendButton = findViewById(R.id.sendButton);

    }

    private void registerListeners() {

        sendButton.setOnClickListener(view -> {

            String email = editTextEmailAddress.getText().toString();

            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth.getInstance()
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PasswordResetActivity.this, "Check email!", Toast.LENGTH_SHORT).show();
                            } else  {
                                Toast.makeText(PasswordResetActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });

    }

}