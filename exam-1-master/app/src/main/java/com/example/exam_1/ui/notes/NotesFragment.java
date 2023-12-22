package com.example.exam_1.ui.notes;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.exam_1.R;
import com.example.exam_1.databinding.FragmentNotesBinding;

public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotesBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        Button addButton = root.findViewById(R.id.buttonAdd);
        Button clearButton = root.findViewById(R.id.buttonClear);

        EditText noteEditText = root.findViewById(R.id.editTextNote);
        TextView notesTextView = root.findViewById(R.id.textViewNotes);

        SharedPreferences notesPref = this.getActivity().getSharedPreferences("Notes Pref", MODE_PRIVATE);
        notesTextView.setText(notesPref.getString("NOTES", ""));


        addButton.setOnClickListener(v -> {
            String note = noteEditText.getText().toString();
            String notes = notesTextView.getText().toString();

            String result = notes + "\n" + note;

            notesTextView.setText(result);

            noteEditText.setText("");

            notesPref.edit()
                    .putString("NOTES", result)
                    .apply();
        });

        clearButton.setOnClickListener(v -> {
            notesTextView.setText("");

            notesPref.edit()
                    .putString("NOTES", "")
                    .apply();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}