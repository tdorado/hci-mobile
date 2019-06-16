package com.itba.hci.smarthome.view.util;

import android.content.Context;
import android.widget.Spinner;

import com.itba.hci.smarthome.R;

public class CommonUtils {

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
            case 3:
                return "fan";
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
}
