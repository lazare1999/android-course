package com.lazo.databinding.data_binding;

public class DataBindingActivityContract {

    public interface Presenter {
        void onShowData(TemperatureData temperatureData);
    }

    public interface View {
        void showData(TemperatureData temperatureData);
    }

}
