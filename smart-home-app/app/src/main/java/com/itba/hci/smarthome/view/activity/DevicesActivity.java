package com.itba.hci.smarthome.view.activity;

import android.os.Bundle;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.Navigator;

import javax.inject.Inject;

public class DevicesActivity extends SmartHomeActivity {
    @Inject
    Navigator navigator;

    private boolean close = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_blank);
        super.onCreate(savedInstanceState);
        navigator.showDevicesFragment(this);
    }

    @Override
    protected void injectDependencies(SmartHomeComponents smartHomeComponents) {
        smartHomeComponents.inject(this);
    }

    public void onBackPressed() {
        if (!close) {
            showToastError("Presione nuevamente para salir de la aplicación");
            close = true;
        } else {
            finishAffinity();
        }
    }
}
