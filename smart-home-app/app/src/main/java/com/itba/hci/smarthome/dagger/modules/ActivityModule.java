package com.itba.hci.smarthome.dagger.modules;

import com.itba.hci.smarthome.view.activity.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    /**
     @ContributesAndroidInjector(modules = FragmentModule.class)
     abstract MainActivity contributeMainActivity();
     **/

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoginActivity contributeLoginActivity();

}