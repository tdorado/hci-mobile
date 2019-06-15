package com.itba.hci.smarthome.view.activity;

import android.os.Bundle;
import android.view.View;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.Navigator;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

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
        buildMenuDrawerWithSelectedIdentifier(1);
        getMenuDrawer().setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch(position){
                    case 0:
                        onDevicesItemClick();
                        return true;
                    case 1:
                        onRoutinesItemClick();
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    protected void injectDependencies(SmartHomeComponents smartHomeComponents) {
        smartHomeComponents.inject(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        if(!this.getMenuDrawer().isDrawerOpen()) {
            if (!close) {
                showToastError(getResources().getString(R.string.double_press));
                close = true;
            } else {
                finishAffinity();
            }
        }
    }

    private void onDevicesItemClick(){
        navigator.showDevicesActivity(this);
    }

    private void onRoutinesItemClick(){
        navigator.showRoutinesActivity(this);
    }
}
