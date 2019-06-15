package com.itba.hci.smarthome.service;

import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.model.request.EmptyRequest;
import com.itba.hci.smarthome.service.payload.AcStateResponse;
import com.itba.hci.smarthome.service.payload.AlarmStateResponse;
import com.itba.hci.smarthome.service.payload.BlindsStateResponse;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.DeviceResponse;
import com.itba.hci.smarthome.service.payload.DevicesResponse;
import com.itba.hci.smarthome.service.payload.DoorStateResponse;
import com.itba.hci.smarthome.service.payload.IntegerResultResponse;
import com.itba.hci.smarthome.service.payload.LampStateResponse;
import com.itba.hci.smarthome.service.payload.OvenStateResponse;
import com.itba.hci.smarthome.service.payload.StringResultResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @PUT("devices/{deviceId}")
    Call<BooleanResultResponse> updateDevice(@Path("deviceId") String deviceId, @Body DeviceRequest deviceRequest);

    @DELETE("devices/{deviceId}")
    Call<BooleanResultResponse> deleteDevice(@Path("deviceId") String deviceId);

    @PUT("devices/{deviceId}/{actionName}")
    Call<StringResultResponse> actionWithString(@Path("deviceId") String deviceId, @Path("actionName") String actionName, @Body List<String> params);

    @PUT("devices/{deviceId}/{actionName}")
    Call<BooleanResultResponse> actionWithStringAndBooleanResponse(@Path("deviceId") String deviceId, @Path("actionName") String actionName, @Body List<String> params);

    @PUT("devices/{deviceId}/{actionName}")
    Call<IntegerResultResponse> actionWithInteger(@Path("deviceId") String deviceId, @Path("actionName") String actionName, @Body List<Integer> params);

    @PUT("devices/{deviceId}/getState")
    Call<BlindsStateResponse> getBlindsState(@Path("deviceId") String deviceId, @Body List<String> emptyList);

    @PUT("devices/{deviceId}/getState")
    Call<LampStateResponse> getLampState(@Path("deviceId") String deviceId, @Body List<String> emptyList);

    @PUT("devices/{deviceId}/getState")
    Call<OvenStateResponse> getOvenState(@Path("deviceId") String deviceId, @Body List<String> emptyList);

    @PUT("devices/{deviceId}/getState")
    Call<AcStateResponse> getAcState(@Path("deviceId") String deviceId, @Body List<String> emptyList);

    @PUT("devices/{deviceId}/getState")
    Call<AlarmStateResponse> getAlarmState(@Path("deviceId") String deviceId, @Body List<String> emptyList);

    @PUT("devices/{deviceId}/getState")
    Call<DoorStateResponse> getDoorState(@Path("deviceId") String deviceId, @Body List<String> emptyList);
}
