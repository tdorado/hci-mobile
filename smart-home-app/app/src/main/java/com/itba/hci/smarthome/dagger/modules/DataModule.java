package com.itba.hci.smarthome.dagger.modules;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itba.hci.smarthome.core.SmartHomeApplication;
import com.itba.hci.smarthome.db.LoginRepository;
import com.itba.hci.smarthome.db.SmartHomeDb;
import com.itba.hci.smarthome.service.LoginService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private SmartHomeDb smartHomeDb;

    public DataModule(SmartHomeApplication mApplication) {
        smartHomeDb = Room.databaseBuilder(mApplication, SmartHomeDb.class, "smartHome-db").fallbackToDestructiveMigration().build();
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
    public LoginRepository providesLoginRepository(LoginService loginService, Context context) {
        return new LoginRepository(loginService, context);
    }



    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

}
