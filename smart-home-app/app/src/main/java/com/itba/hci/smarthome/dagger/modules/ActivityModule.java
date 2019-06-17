package com.itba.hci.smarthome.dagger.modules;

import com.itba.hci.smarthome.view.activity.AcActivity;
import com.itba.hci.smarthome.view.activity.AlarmActivity;
import com.itba.hci.smarthome.view.activity.BlindsActivity;
import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.activity.EditDeviceActivity;
import com.itba.hci.smarthome.view.activity.NewDeviceActivity;
import com.itba.hci.smarthome.view.activity.OvenActivity;
import com.itba.hci.smarthome.view.activity.RoutinesActivity;
import com.itba.hci.smarthome.view.fragment.AcFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    /**
     @ContributesAndroidInjector(modules = FragmentModule.class)
     abstract MainActivity contributeMainActivity();
     **/

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DevicesActivity contributeDevicesActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract NewDeviceActivity contributeNewDeviceActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract RoutinesActivity contributeRoutinesActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract EditDeviceActivity contributeEditDeviceActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract BlindsActivity contributeBlindsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AcActivity contributeAcActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract OvenActivity contributeOvenActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AlarmActivity contributeAlarmActivity();

}