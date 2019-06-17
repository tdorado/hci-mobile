package com.itba.hci.smarthome.view.activity;

import android.os.Bundle;
import android.view.View;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.Navigator;
import com.mikepenz.materialdrawer.Drawer;

import javax.inject.Inject;

public class DeleteRoutineActivity extends SmartHomeActivity {
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_blank);
        super.onCreate(savedInstanceState);
        navigator.showDeleteRoutineFragment(this, getIntent().getStringExtra("routineId"));
        getMenuDrawer().getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getMenuDrawer().setOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
            @Override
            public boolean onNavigationClickListener(View clickedView) {
                goBackFromMenu();
                return true;
            }
        });
    }


    @Override
    protected void injectDependencies(SmartHomeComponents smartHomeComponents) {
        smartHomeComponents.inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!this.getMenuDrawer().isDrawerOpen()) {
            navigator.showRoutinesActivity(this);
        }
    }

    private void goBackFromMenu(){
        navigator.showRoutinesActivity(this);
    }
}
