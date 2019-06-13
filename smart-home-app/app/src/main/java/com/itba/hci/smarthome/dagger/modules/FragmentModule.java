package com.itba.hci.smarthome.dagger.modules;

import com.itba.hci.smarthome.view.fragment.DevicesFragment;
import com.itba.hci.smarthome.view.fragment.NewDeviceFragment;

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
}