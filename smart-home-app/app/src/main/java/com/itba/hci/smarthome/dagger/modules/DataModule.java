package com.itba.hci.smarthome.dagger.modules;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.db.AcActionsRepository;
import com.itba.hci.smarthome.db.AlarmActionsRepository;
import com.itba.hci.smarthome.db.BlindsActionsRepository;
import com.itba.hci.smarthome.db.DeviceRepository;
import com.itba.hci.smarthome.db.DoorActionsRepository;
import com.itba.hci.smarthome.db.LampActionsRepository;
import com.itba.hci.smarthome.db.OvenActionsRepository;
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
        //smartHomeDb = Room.databaseBuilder(mApplication, SmartHomeDb.class, "smart-home-db").fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    SmartHomeDb providesRoomDatabase() {
        return smartHomeDb;
    }

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

    @Singleton
    @Provides
    public AcActionsRepository providesAcActionsRepository(DeviceService deviceService){
        return new AcActionsRepository(deviceService);
    }

    @Singleton
    @Provides
    public AlarmActionsRepository providesAlarmActionsRepository(DeviceService deviceService){
        return new AlarmActionsRepository(deviceService);
    }

    @Singleton
    @Provides
    public BlindsActionsRepository providesBlindsActionsRepository(DeviceService deviceService){
        return new BlindsActionsRepository(deviceService);
    }

    @Singleton
    @Provides
    public DoorActionsRepository providesDoorActionsRepository(DeviceService deviceService){
        return new DoorActionsRepository(deviceService);
    }

    @Singleton
    @Provides
    public LampActionsRepository providesLampActionsRepository(DeviceService deviceService){
        return new LampActionsRepository(deviceService);
    }

    @Singleton
    @Provides
    public OvenActionsRepository providesOvenActionsRepository(DeviceService deviceService){
        return new OvenActionsRepository(deviceService);
    }


    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

}
