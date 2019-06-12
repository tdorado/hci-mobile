package com.itba.hci.smarthome.dagger.modules;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.db.DeviceRepository;
import com.itba.hci.smarthome.db.RoutineRepository;
import com.itba.hci.smarthome.db.SmartHomeDb;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.RoutineService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private SmartHomeDb smartHomeDb;

    public DataModule(SmartHomeApplication mApplication) {
        //smartHomeDb = Room.databaseBuilder(mApplication, SmartHomeDb.class, "smartHome-db").fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    SmartHomeDb providesRoomDatabase() {
        return smartHomeDb;
    }
    /*
        @Singleton
        @Provides
        public MissionDao missionDao(BoostDb boostDb){
            return boostDb.missionDao();
        }
    */

    @Singleton
    @Provides
    public DeviceRepository providesDeviceRepository(DeviceService deviceService) {
        return new DeviceRepository(deviceService);
    }

    @Singleton
    @Provides
    public RoutineRepository providesRoutineRepository(RoutineService routineService) {
        return new RoutineRepository(routineService);
    }


    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

}
