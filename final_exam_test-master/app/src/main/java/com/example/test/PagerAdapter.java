package com.example.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.test.ui.login.LoginFragment;
import com.example.test.ui.register.RegisterFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.tabs = NumberOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new RegisterFragment();
        }
        return new LoginFragment();
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
