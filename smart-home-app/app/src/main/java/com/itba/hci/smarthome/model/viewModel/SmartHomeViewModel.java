package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.ViewModel;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;

public abstract class SmartHomeViewModel extends ViewModel {
    public abstract void initialize(SmartHomeComponents component);
}
