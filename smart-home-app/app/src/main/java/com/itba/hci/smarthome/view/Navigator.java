package com.itba.hci.smarthome.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.activity.NewDeviceActivity;
import com.itba.hci.smarthome.view.activity.SmartHomeActivity;
import com.itba.hci.smarthome.view.fragment.DevicesFragment;
import com.itba.hci.smarthome.view.fragment.NewDeviceFragment;

/**
 * this class is use to navigate between activities and fragments
 */
public class Navigator {

    public Navigator() {

    }

    /**
     * Activities
     * public ..(Activity from, params)
     * Intent intent = new Intent(from.getApplicationContext(), MyClassActivity.class);
     * intent = MyClassActivity.putParams(*);
     * from.startActivity(intent);
     */

    public void showDevicesActivity(NewDeviceFragment from){
        Intent intent = new Intent(from.getContext(), DevicesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public void showDevicesActivity(NewDeviceActivity from){
        Intent intent = new Intent(from.getApplicationContext(), DevicesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public void showNewDeviceActivity(DevicesFragment from) {
        Intent intent = new Intent(from.getContext(), NewDeviceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    /**
     * Fragments
     * <p>
     * function declaration(params)
     * openFragment(ActivityFrom, fragment, fragment name, addToBackStack?
     */

    public void showDevicesFragment(DevicesActivity devicesActivity){
        openFragment(devicesActivity, new DevicesFragment(), "Dispositivos", false);
    }

    public void showNewDeviceFragment(NewDeviceActivity newDeviceActivity){
        openFragment(newDeviceActivity, new NewDeviceFragment(), "Nuevo Dispositivo", false);
    }


    private Fragment openFragment(SmartHomeActivity from, Fragment fragment, String name, boolean addToBackStack) {
        FragmentTransaction transaction = from.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment, name);
        if (addToBackStack)
            transaction.addToBackStack(name);
        from.setTitle(name);
        transaction.commit();
        from.invalidateOptionsMenu();
        return fragment;
    }

}

