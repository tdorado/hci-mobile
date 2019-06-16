package com.itba.hci.smarthome.view.util;

import com.itba.hci.smarthome.R;

public class CommonUtils {

    public static int getIdResourceBlinds(String status) {
        switch(status){
            case "opened":
                return R.string.opened;
            case "closed":
                return R.string.closed;
            case "closing":
                return R.string.closing;
            case "opening":
                return R.string.opening;
        }
        return R.string.status_caps;
    }

    public static String getBlindsActionItemArray(int item){
        switch(item){
            case 1:
                return "open";
            case 2:
                return "close";
        }
        return "";
    }
}
