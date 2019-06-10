package com.itba.hci.smarthome.dagger.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.db.SmartHomeDb;
import com.itba.hci.smarthome.view.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final SmartHomeApplication smartHomeApplication;

    public ApplicationModule(SmartHomeApplication smartHomeApplication) {
        this.smartHomeApplication = smartHomeApplication;
    }

    @Provides
    @Singleton
    public SmartHomeApplication providIngeApplication() {
        return this.smartHomeApplication;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    SmartHomeDb provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                SmartHomeDb.class, "SmartHome.db")
                .build();
    }

    @Provides
    Context provideContext() {
        return smartHomeApplication.getApplicationContext();
    }


}
