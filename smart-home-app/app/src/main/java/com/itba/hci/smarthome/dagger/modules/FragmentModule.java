package com.itba.hci.smarthome.dagger.modules;

import com.itba.hci.smarthome.view.fragment.AcFragment;
import com.itba.hci.smarthome.view.fragment.BlindsFragment;
import com.itba.hci.smarthome.view.fragment.DevicesFragment;
import com.itba.hci.smarthome.view.fragment.EditDeviceFragment;
import com.itba.hci.smarthome.view.fragment.NewDeviceFragment;
import com.itba.hci.smarthome.view.fragment.RoutinesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    /**
     *
     *
     @ContributesAndroidInjector
     abstract UserProfileFragment contributeUserProfileFragment();
     */


    @ContributesAndroidInjector
    abstract DevicesFragment contributeDevicesFragment();

    @ContributesAndroidInjector
    abstract NewDeviceFragment contributeNewDeviceFragment();

    @ContributesAndroidInjector
    abstract RoutinesFragment contributeRoutinesFragment();

    @ContributesAndroidInjector
    abstract EditDeviceFragment contributeEditDeviceFragment();

    @ContributesAndroidInjector
    abstract BlindsFragment contributeBlindsFragment();

    @ContributesAndroidInjector
    abstract AcFragment contributeAcFragment();
}