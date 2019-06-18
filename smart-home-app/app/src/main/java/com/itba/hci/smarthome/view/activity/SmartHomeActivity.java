package com.itba.hci.smarthome.view.activity;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.util.CommonUtils;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

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

        CommonUtils.verifyAndAskPermissions(this);

        buildMenuDrawerWithSelectedIdentifier(-1);
        if(getResources().getBoolean(R.bool.is_tablet)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
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

    public void buildMenuDrawerWithSelectedIdentifier(int identifier){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuDrawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName(R.string.devices),
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.routines),
                        new DividerDrawerItem(),
                        new SecondarySwitchDrawerItem().withChecked(CommonUtils.isGetNotifications()).withName(getString(R.string.notifications)).withSelectable(false).withOnCheckedChangeListener(new OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                CommonUtils.setGetNotifications(isChecked);
                            }
                        })
                )
                .withSelectedItem(identifier)
                .build();
    }

}
