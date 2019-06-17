package com.itba.hci.smarthome.view.activity;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.view.util.notification.NotificationHelper;
import com.itba.hci.smarthome.view.Navigator;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import javax.inject.Inject;

public class DevicesActivity extends SmartHomeActivity {
    @Inject
    Navigator navigator;

    private boolean close = false;

    /*
     * A view model for interacting with the UI elements.
     */
    private MainUi mUIModel;

    /*
     * A helper class for initializing notification channels and sending notifications.
     */
    private NotificationHelper mNotificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_blank);
        super.onCreate(savedInstanceState);
        navigator.showDevicesFragment(this);
        buildMenuDrawerWithSelectedIdentifier(1);

        mNotificationHelper = new NotificationHelper(this);
        mUIModel = new MainUi(findViewById(R.id.content_frame));
        this.sendNotification(12);

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

    /**
     * Send activity notifications.
     *
     * @param id The ID of the notification to create
     */
    private void sendNotification(int id) {
        Notification.Builder notificationBuilder;
        notificationBuilder = mNotificationHelper.getNotificationDM("titulo",
                                        mNotificationHelper.getRandomName());
        mNotificationHelper.notify(id, notificationBuilder);
    }

    /**
     * View model for interacting with Activity UI elements. (Keeps core logic for sample separate.)
     */
    class MainUi implements View.OnClickListener {

        private MainUi(View root) {
            (root.findViewById(R.id.content_frame)).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("Llego la notificacion","Llegó vieja");
        }
    }
}
