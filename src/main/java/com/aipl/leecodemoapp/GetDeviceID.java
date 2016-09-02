package com.aipl.leecodemoapp;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by HelixTech-Admin on 3/19/2016.
 */
public class GetDeviceID {
    Context context;
    String deviceId;
    GetDeviceID(Context conx){
        context=conx;
    }
    public String getDeviceId(){
        deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID).toString();
        return deviceId;
    }

}
