package com.itba.hci.smarthome.dagger.modules;

import com.google.gson.Gson;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.RoutineService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {
    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.136:8080/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    /**
     *  @Provides
     @Singleton
     public AlgoService provideAlgoService(Retrofit retrofit){
     return retrofit.create(AlgoService.class);
     }

     */

    @Provides
    @Singleton
    public DeviceService providesDeviceService(Retrofit retrofit){
        return retrofit.create(DeviceService.class);
    }

    @Provides
    @Singleton
    public RoutineService providesRoutineService(Retrofit retrofit){
        return retrofit.create(RoutineService.class);
    }


}
