package com.itba.hci.smarthome.dagger.modules;

import com.itba.hci.smarthome.view.activity.DevicesActivity;
import com.itba.hci.smarthome.view.activity.NewDeviceActivity;
import com.itba.hci.smarthome.view.activity.RoutinesActivity;

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

}