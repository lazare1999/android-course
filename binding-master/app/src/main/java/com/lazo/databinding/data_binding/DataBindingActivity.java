package com.lazo.databinding.data_binding;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.lazo.databinding.R;
import com.lazo.databinding.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends Activity implements DataBindingActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        DataBindingActivityPresenter dataBindingActivityPresenter = new DataBindingActivityPresenter(this, getApplicationContext());
        TemperatureData temperatureData = new TemperatureData("Hamburg", "10");
        binding.setTemp(temperatureData);
        binding.setPresenter(dataBindingActivityPresenter);
    }

    @Override
    public void showData(TemperatureData temperatureData) {
        String celsius = temperatureData.getCelsius();
        Toast.makeText(this, celsius, Toast.LENGTH_SHORT).show();
    }

}