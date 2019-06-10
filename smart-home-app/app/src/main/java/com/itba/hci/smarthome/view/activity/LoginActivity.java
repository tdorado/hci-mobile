package com.itba.hci.smarthome.view.activity;

import android.os.Bundle;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.Navigator;

import javax.inject.Inject;

public class LoginActivity extends SmartHomeActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_blank);
        super.onCreate(savedInstanceState);
        navigator.showLoginFragment(this);
    }

    @Override
    protected void injectDependencies(SmartHomeComponents ingeComponents) {
        ingeComponents.inject(this);
    }

    public void onBackPressed() {
        finish();
    }
}
