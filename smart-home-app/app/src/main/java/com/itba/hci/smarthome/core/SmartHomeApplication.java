package com.itba.hci.smarthome.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.dagger.modules.ApplicationModule;
import com.itba.hci.smarthome.dagger.modules.DataModule;
import com.itba.hci.smarthome.dagger.modules.ServiceModule;
import com.itba.hci.smarthome.view.activity.LoginActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class SmartHomeApplication extends MultiDexApplication implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private SmartHomeComponents component;
    private static SmartHomeApplication sInstance;

    public static Context context;

    public static SmartHomeApplication getsInstance() {
        return sInstance;
    }

    public static void setsInstance(SmartHomeApplication instance) {
        SmartHomeApplication.setsInstance(instance);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = getApplicationContext();
        if (this.component == null) {
            this.component = SmartHomeComponents.Initializer.init(
                    new ApplicationModule(this),
                    new ServiceModule(),
                    new DataModule(this)
            );
            component.inject(this);
        }

        this.createShotcut();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public SmartHomeComponents getComponent() {
        return component;
    }

    public final void createShotcut() {
        Intent shortcutIntent = new Intent();
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, getIntentShortcut());
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        shortcutIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        this.sendBroadcast(shortcutIntent);
    }

    public Intent getIntentShortcut(){
        return new Intent()
                .setClass(getApplicationContext(), LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setAction(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER);
    }

}
