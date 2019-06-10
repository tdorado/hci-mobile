package com.itba.hci.smarthome.util;

import com.itba.hci.smarthome.view.activity.SmartHomeActivity;

import java.util.Date;

/**
 * here goes the commonUtils methods that many clases may use
 */

public class CommonUtils {
    //I should ask for needed permissions here
    public static void verificoYPidoPermisos(SmartHomeActivity smartHomeActivity) {
    }

    public static String getFechaValida(Date date) {
        String dateString = date.getDay() + "/" + date.getMonth() + "/2018" + " " +
                date.getHours()+":" +date.getMinutes();
        return dateString;
    }
}
