package com.itba.hci.smarthome.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.view.activity.BlindsActivity;
import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.activity.EditDeviceActivity;
import com.itba.hci.smarthome.view.activity.NewDeviceActivity;
import com.itba.hci.smarthome.view.activity.RoutinesActivity;
import com.itba.hci.smarthome.view.activity.SmartHomeActivity;
import com.itba.hci.smarthome.view.fragment.BlindsFragment;
import com.itba.hci.smarthome.view.fragment.DevicesFragment;
import com.itba.hci.smarthome.view.fragment.EditDeviceFragment;
import com.itba.hci.smarthome.view.fragment.NewDeviceFragment;
import com.itba.hci.smarthome.view.fragment.RoutinesFragment;
import com.itba.hci.smarthome.view.fragment.SmartHomeFragment;
import com.mikepenz.materialdrawer.DrawerBuilder;

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

    public void showDevicesActivity(SmartHomeFragment from){
        Intent intent = new Intent(from.getContext(), DevicesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public void showDevicesActivity(SmartHomeActivity from){
        Intent intent = new Intent(from.getApplicationContext(), DevicesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public void showRoutinesActivity(SmartHomeFragment from) {
        Intent intent = new Intent(from.getContext(), RoutinesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public void showRoutinesActivity(SmartHomeActivity from) {
        Intent intent = new Intent(from.getApplicationContext(), RoutinesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public void showNewDeviceActivity(SmartHomeFragment from) {
        Intent intent = new Intent(from.getContext(), NewDeviceActivity.class);
        from.startActivity(intent);
    }

    public void showEditDeviceActivity(DevicesFragment devicesFragment, String idItemClicked) {
        Intent intent = new Intent(devicesFragment.getContext(), EditDeviceActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    public void showBlindsActivity(DevicesFragment devicesFragment, String idItemClicked){
        Intent intent = new Intent(devicesFragment.getContext(), BlindsActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    /**
     * Fragments
     * <p>
     * function declaration(params)
     * openFragment(ActivityFrom, fragment, fragment name, addToBackStack?
     */

    public void showDevicesFragment(DevicesActivity devicesActivity){
        openFragment(devicesActivity, new DevicesFragment(), devicesActivity.getResources().getString(R.string.devices), false);
    }

    public void showRoutinesFragment(RoutinesActivity routinesActivity) {
        openFragment(routinesActivity, new RoutinesFragment(), routinesActivity.getResources().getString(R.string.routines), false);
    }

    public void showNewDeviceFragment(NewDeviceActivity newDeviceActivity){
        openFragment(newDeviceActivity, new NewDeviceFragment(), newDeviceActivity.getResources().getString(R.string.add_device), false);
    }

    public void showEditDeviceFragment(EditDeviceActivity editDeviceActivity, String deviceId) {
        openFragment(editDeviceActivity, EditDeviceFragment.newInstance(deviceId), editDeviceActivity.getResources().getString(R.string.edit_device), false);
    }

    public void showBlindsFragment(BlindsActivity blindsActivity, String deviceId) {
        openFragment(blindsActivity, BlindsFragment.newInstance(deviceId), blindsActivity.getResources().getString(R.string.blinds_status), false);
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

