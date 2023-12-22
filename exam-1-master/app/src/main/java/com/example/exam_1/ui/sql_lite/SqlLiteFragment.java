package com.example.exam_1.ui.sql_lite;

import static com.example.exam_1.config.App.getGunDbHelper;

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
import com.example.exam_1.databinding.FragmentSqlLiteBinding;
import com.example.exam_1.ui.sql_lite.guns.GunModel;

import java.util.List;

public class SqlLiteFragment extends Fragment {


    private FragmentSqlLiteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSqlLiteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText editTextName = root.findViewById(R.id.editTextName);
        Button searchButton = root.findViewById(R.id.buttonSearch);
        TextView textViewResults = root.findViewById(R.id.textViewResults);


        searchButton.setOnClickListener(v -> {
            String name = editTextName.getText().toString();

            List<GunModel> select = getGunDbHelper().select(name);

            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < select.size(); i++) {
                ans.append(select.get(i).getNameAndCaliberAndYear()).append("\n");
            }
            textViewResults.setText(ans.toString());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
