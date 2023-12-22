package com.lazo.fragments.ui.about_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lazo.fragments.databinding.FragmentAboutUsBinding;
import com.lazo.fragments.databinding.FragmentDashboardBinding;
import com.lazo.fragments.databinding.FragmentNotificationsBinding;
import com.lazo.fragments.ui.notifications.NotificationsViewModel;


public class AboutUsFragment extends Fragment {

    private FragmentAboutUsBinding binding;
    private AboutUsViewModel aboutUsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutUsViewModel =
                new ViewModelProvider(this).get(AboutUsViewModel.class);

        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAboutUs;
        aboutUsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
