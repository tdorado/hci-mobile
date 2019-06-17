package com.itba.hci.smarthome.util;

import android.arch.persistence.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itba.hci.smarthome.model.entities.Device;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converter {
    public static Gson gson = new GsonBuilder()
            .setLenient().serializeSpecialFloatingPointValues()
            .create();
    /**
     * todos los metodos aca van con la annotation @TypeConverter
     */


    @TypeConverter
    public static List<Device> stringToDeviceList(String data){
        if(data == null){
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Device>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String deviceListToString(List<Device> someObjects){
        return gson.toJson(someObjects);
    }

}
