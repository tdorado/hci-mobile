package com.itba.hci.smarthome.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.view.activity.AcActivity;
import com.itba.hci.smarthome.view.activity.AlarmActivity;
import com.itba.hci.smarthome.view.activity.BlindsActivity;
import com.itba.hci.smarthome.view.activity.DeleteDeviceActivity;
import com.itba.hci.smarthome.view.activity.DeleteRoutineActivity;
import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.activity.DoorActivity;
import com.itba.hci.smarthome.view.activity.EditDeviceActivity;
import com.itba.hci.smarthome.view.activity.LampActivity;
import com.itba.hci.smarthome.view.activity.NewDeviceActivity;
import com.itba.hci.smarthome.view.activity.OvenActivity;
import com.itba.hci.smarthome.view.activity.RoutinesActivity;
import com.itba.hci.smarthome.view.activity.SmartHomeActivity;
import com.itba.hci.smarthome.view.fragment.AcFragment;
import com.itba.hci.smarthome.view.fragment.AlarmFragment;
import com.itba.hci.smarthome.view.fragment.BlindsFragment;
import com.itba.hci.smarthome.view.fragment.DeleteDeviceFragment;
import com.itba.hci.smarthome.view.fragment.DeleteRoutineFragment;
import com.itba.hci.smarthome.view.fragment.DevicesFragment;
import com.itba.hci.smarthome.view.fragment.DoorFragment;
import com.itba.hci.smarthome.view.fragment.EditDeviceFragment;
import com.itba.hci.smarthome.view.fragment.LampFragment;
import com.itba.hci.smarthome.view.fragment.NewDeviceFragment;
import com.itba.hci.smarthome.view.fragment.OvenFragment;
import com.itba.hci.smarthome.view.fragment.RoutinesFragment;
import com.itba.hci.smarthome.view.fragment.SmartHomeFragment;
import com.mikepenz.materialdrawer.DrawerBuilder;

import javax.inject.Inject;

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

    public void showAcActivity(DevicesFragment devicesFragment, String idItemClicked){
        Intent intent = new Intent(devicesFragment.getContext(), AcActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    public void showOvenActivity(DevicesFragment devicesFragment, String idItemClicked) {
        Intent intent = new Intent(devicesFragment.getContext(), OvenActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    public void showAlarmActivity(DevicesFragment devicesFragment, String idItemClicked) {
        Intent intent = new Intent(devicesFragment.getContext(), AlarmActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    public void showDoorActivity(DevicesFragment devicesFragment, String idItemClicked) {
        Intent intent = new Intent(devicesFragment.getContext(), DoorActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    public void showLampActivity(DevicesFragment devicesFragment, String idItemClicked) {
        Intent intent = new Intent(devicesFragment.getContext(), LampActivity.class);
        intent.putExtra("deviceId", idItemClicked);
        devicesFragment.startActivity(intent);
    }

    public void showDeleteRoutineActivity(RoutinesFragment routinesFragment, String idItemClicked) {
        Intent intent = new Intent(routinesFragment.getContext(), DeleteRoutineActivity.class);
        intent.putExtra("routineId", idItemClicked);
        routinesFragment.startActivity(intent);
    }

    public void showDeleteDeviceActivity(DevicesFragment devicesFragment, String idItemClicked) {
        Intent intent = new Intent(devicesFragment.getContext(), DeleteDeviceActivity.class);
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

    public void showAcFragment(AcActivity acActivity, String deviceId) {
        openFragment(acActivity, AcFragment.newInstance(deviceId), acActivity.getResources().getString(R.string.ac_status), false);
    }

    public void showOvenFragment(OvenActivity ovenActivity, String deviceId) {
        openFragment(ovenActivity, OvenFragment.newInstance(deviceId), ovenActivity.getString(R.string.oven_status), false);
    }

    public void showAlarmFragment(AlarmActivity alarmActivity, String deviceId) {
        openFragment(alarmActivity, AlarmFragment.newInstance(deviceId), alarmActivity.getString(R.string.alarm_status), false);
    }

    public void showDoorFragment(DoorActivity doorActivity, String deviceId) {
        openFragment(doorActivity, DoorFragment.newInstance(deviceId), doorActivity.getString(R.string.door_status), false);
    }

    public void showLampFragment(LampActivity lampActivity, String deviceId) {
        openFragment(lampActivity, LampFragment.newInstance(deviceId), lampActivity.getString(R.string.lamp_status), false);
    }

    public void showDeleteDeviceFragment(DeleteDeviceActivity deleteDeviceActivity, String deviceId) {
        openFragment(deleteDeviceActivity, DeleteDeviceFragment.newInstance(deviceId), deleteDeviceActivity.getString(R.string.delete_device), false);
    }

    public void showDeleteRoutineFragment(DeleteRoutineActivity deleteRoutineActivity, String routineId) {
        openFragment(deleteRoutineActivity, DeleteRoutineFragment.newInstance(routineId), deleteRoutineActivity.getString(R.string.delete_routine), false);
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

