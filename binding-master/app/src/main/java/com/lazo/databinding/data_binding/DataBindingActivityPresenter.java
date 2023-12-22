package com.lazo.databinding.data_binding;

import android.content.Context;

public class DataBindingActivityPresenter implements DataBindingActivityContract.Presenter {

    private DataBindingActivityContract.View view;
    private Context ctx;

    public DataBindingActivityPresenter(DataBindingActivityContract.View view, Context ctx) {
        this.view = view;
        this.ctx = ctx;
    }

    @Override
    public void onShowData(TemperatureData temperatureData) {
        view.showData(temperatureData);
    }

}
