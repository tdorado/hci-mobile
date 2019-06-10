package com.itba.hci.smarthome.util;

import android.arch.persistence.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itba.hci.smarthome.model.entities.Usuario;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Converter {
    public static Gson gson = new GsonBuilder()
            .setLenient().serializeSpecialFloatingPointValues()
            .create();
    /**
     * todos los metodos aca van con la annotation @TypeConverter
     */

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date value) {
        return value == null ? null : value.getTime();
    }
//
//    @TypeConverter
//    public static List<Compra> stringToProductList(String data) {
//        if (data == null) {
//            return Collections.emptyList();
//        }
//        Type listType = new TypeToken<List<Producto>>() {}.getType();
//        return gson.fromJson(data, listType);
//    }
//
//    @TypeConverter
//    public static String producListToString(List<Compra> someObjects) {
//        return gson.toJson(someObjects);
//    }
//
//    @TypeConverter
//    public static List<Tamanio> stringToTamanioList(String data) {
//        if (data == null) {
//            return Collections.emptyList();
//        }
//        Type listType = new TypeToken<List<Tamanio>>() {}.getType();
//        return gson.fromJson(data, listType);
//    }
//
//    @TypeConverter
//    public static String tamanioListToString(List<Tamanio> someObjects) {
//        return gson.toJson(someObjects);
//    }
//
    @TypeConverter
    public static Usuario stringToUsuario(String data) {
        if (data != null){
            Type listType = new TypeToken<Usuario>() {}.getType();
            return gson.fromJson(data, listType);
        }
        return null;

    }
//
//    @TypeConverter
//    public static String usuarioToString(Usuario usuario) {
//        return gson.toJson(usuario);
//    }
//
//
//    @TypeConverter
//    public static Rol stringToRol(String data) {
//        if (data != null){
//            Type listType = new TypeToken<Rol>() {}.getType();
//            return gson.fromJson(data, listType);
//        }
//        return null;
//
//    }
//
//    @TypeConverter
//    public static String rolToString(Rol rol) {
//        return gson.toJson(rol);
//    }
//
//    @TypeConverter
//    public static Estado stringToEstado(String data) {
//        if (data != null){
//            Type listType = new TypeToken<Estado>() {}.getType();
//            return gson.fromJson(data, listType);
//        }
//        return null;
//
//    }
//
//    @TypeConverter
//    public static String EstadoToString(Estado estado) {
//        return gson.toJson(estado);
//    }
//
//    @TypeConverter
//    public static Tamanio stringToTamanio(String data) {
//        if (data != null){
//            Type listType = new TypeToken<Tamanio>() {}.getType();
//            return gson.fromJson(data, listType);
//        }
//        return null;
//
//    }
//
//    @TypeConverter
//    public static String tamanioToString(Tamanio tamanio) {
//        return gson.toJson(tamanio);
//    }
//
//    @TypeConverter
//    public static Calificacion stringToCalificacion(String data) {
//        if (data != null){
//            Type listType = new TypeToken<Calificacion>() {}.getType();
//            return gson.fromJson(data, listType);
//        }
//        return null;
//
//    }
//
//    @TypeConverter
//    public static Map<String, Boolean> stringToTamaniosMap(String data) {
//        if (data == null) {
//            return Collections.emptyMap();
//        }
//        Type listType = new TypeToken<Map<String, Boolean>>() {}.getType();
//        return gson.fromJson(data, listType);
//    }
//
//    @TypeConverter
//    public static String tamaniosMapToString(Map<String, Boolean> someObjects) {
//        return gson.toJson(someObjects);
//    }
//
//
//    @TypeConverter
//    public static String calificacionToString(Calificacion calificacion) {
//        return gson.toJson(calificacion);
//    }



}
