package com.itba.hci.smarthome.view.activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.util.CommonUtils;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

import static android.widget.Toast.LENGTH_SHORT;

public abstract class SmartHomeActivity extends DaggerAppCompatActivity {

    private Toolbar toolbar;
    private Drawer menuDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        CommonUtils.verificoYPidoPermisos(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .build();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected SmartHomeComponents getComponent() {
        return ((SmartHomeApplication) getApplication()).getComponent();
    }

    @Override
    public void onBackPressed() {
        if (menuDrawer != null && this.menuDrawer.isDrawerOpen()) {
            this.menuDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Drawer getMenuDrawer() {
        return menuDrawer;
    }

    public SmartHomeActivity getActivity() {
        return this;
    }

    protected abstract void injectDependencies(SmartHomeComponents smartHomeComponents);

    public void showToastError(String error) {
        if (getApplicationContext() != null) {
            Toast toast = Toast.makeText(getApplicationContext(), error, LENGTH_SHORT);
            toast.show();
        }
    }

}
