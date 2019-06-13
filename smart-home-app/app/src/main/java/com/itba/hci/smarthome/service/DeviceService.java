package com.itba.hci.smarthome.service;

import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.service.payload.DeviceResponse;
import com.itba.hci.smarthome.service.payload.DevicesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DeviceService {
    @POST("devices")
    Call<DeviceResponse> createDevice(@Body DeviceRequest deviceRequest);

    @GET("devices/{deviceId}")
    Call<DeviceResponse> getDevice(@Path("deviceId") String deviceId);

    @GET("devices")
    Call<DevicesResponse> getAllDevices();

    @GET("devices/devicetypes/{deviceTypeId}")
    Call<DevicesResponse> getDevicesOfTypeId(@Path("deviceTypeId") String deviceTypeId);
}
