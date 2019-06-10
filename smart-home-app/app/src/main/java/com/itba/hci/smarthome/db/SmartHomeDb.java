package com.itba.hci.smarthome.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.itba.hci.smarthome.model.entities.Usuario;
import com.itba.hci.smarthome.util.Converter;

@Database(entities = {Usuario.class}, version = 4)
@TypeConverters({Converter.class})
public abstract class SmartHomeDb extends RoomDatabase {

    /**
     * public abstract AlgoDao algoDao();
     */
}
