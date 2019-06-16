package com.itba.hci.smarthome.model.entities;

import java.util.ArrayList;
import java.util.List;

public class DeviceTypes {
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
}
