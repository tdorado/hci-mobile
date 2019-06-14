package com.itba.hci.smarthome.view.activity;

import android.os.Bundle;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.Navigator;

import javax.inject.Inject;

public class EditDeviceActivity extends SmartHomeActivity {
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_blank);
        super.onCreate(savedInstanceState);
        navigator.showEditDeviceFragment(this, getIntent().getStringExtra("deviceId"));
    }


    @Override
    protected void injectDependencies(SmartHomeComponents smartHomeComponents) {
        smartHomeComponents.inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!this.getMenuDrawer().isDrawerOpen()) {
            navigator.showDevicesActivity(this);
        }
    }
}
