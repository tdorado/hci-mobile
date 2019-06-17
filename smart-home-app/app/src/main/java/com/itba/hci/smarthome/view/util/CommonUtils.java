package com.itba.hci.smarthome.view.util;

import android.content.Context;
import android.widget.Spinner;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.view.activity.SmartHomeActivity;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    private static List<String> deviceTypes = new ArrayList<>();

    public static List<String> getDeviceTypes(){
        if(deviceTypes.isEmpty()){
            deviceTypes.add("go46xmbqeomjrsjr"); // lamp
            deviceTypes.add("li6cbv5sdlatti0j"); // ac
            deviceTypes.add("eu0v2xgprrhhg41g"); // blinds
            deviceTypes.add("mxztsyjzsrq7iaqc"); // alarm
            deviceTypes.add("lsf78ly0eqrjbz91"); // door
            deviceTypes.add("im77xxyulpegfmv8"); // oven
            deviceTypes.add("rnizejqr2di0okho"); // refrigerator
            deviceTypes.add("ofglvd9gqX8yfl3l"); // timer
        }
        return deviceTypes;
    }

    public static int getItemForDeviceType(Device device){
        switch(device.getTypeId()){
            case "go46xmbqeomjrsjr": //lamp
                return 1;
            case "li6cbv5sdlatti0j": //ac
                return 2;
            case "eu0v2xgprrhhg41g": // blinds
                return 3;
            case "mxztsyjzsrq7iaqc": // alarm
                return 4;
            case "lsf78ly0eqrjbz91": // door
                return 5;
            case "im77xxyulpegfmv8": // oven
                return 6;
            case "rnizejqr2di0okho": // refrigerator
                return 7;
            case "ofglvd9gqX8yfl3l": // timer
                return 8;
        }
        return -1;
    }

    public static int getResourceId(String status) {
        switch(status){
            case "on":
                return R.string.on;
            case "off":
                return R.string.off;
            case "opened":
                return R.string.opened;
            case "closed":
                return R.string.closed;
            case "closing":
                return R.string.closing;
            case "opening":
                return R.string.opening;
            case "cool":
                return R.string.cool;
            case "heat":
                return R.string.heat;
            case "fan":
                return R.string.fan;
            case "conventional":
                return R.string.conventional;
            case "bottom":
                return R.string.bottom;
            case "top":
                return R.string.top;
            case "large":
                return R.string.large;
            case "eco":
                return R.string.eco;
            case "normal":
                return R.string.normal;
            case "armedStay":
                return R.string.armedStay;
            case "armedAway":
                return R.string.armedAway;
            case "disarmed":
                return R.string.disarmed;
            case "locked":
                return R.string.locked;
            case "unlocked":
                return R.string.unlocked;
        }
        return R.string.status_caps;
    }

    public static String getStringWithAuto(Context context, String status){
        if(status.equals("auto"))
            return context.getString(R.string.auto);
        else
            return status;
    }


    public static String getModeFromArray(int position) {
        switch(position){
            case 0:
                return "cool";
            case 1:
                return "heat";
            case 2:
                return "fan";
        }
        return "null";
    }

    public static String getHeatFromArray(int position) {
        switch(position){
            case 0:
                return "conventional";
            case 1:
                return "bottom";
            case 2:
                return "top";
        }
        return "null";
    }

    public static String getGrillFromArray(int position) {
        switch(position){
            case 0:
                return "large";
            case 1:
                return "eco";
            case 2:
                return "off";
        }
        return "null";
    }

    public static String getConvectionFromArray(int position) {
        switch(position){
            case 0:
                return "normal";
            case 1:
                return "eco";
            case 2:
                return "off";
        }
        return "null";
    }


    public static String getStringItemWithAuto(Spinner spinner) {
        if(spinner.getSelectedItemPosition() == 0){
            return "auto";
        }
        else{
            return String.valueOf(spinner.getSelectedItem());
        }
    }

    public static void verifyAndAskPermissions(SmartHomeActivity smartHomeActivity) {
    }
}
