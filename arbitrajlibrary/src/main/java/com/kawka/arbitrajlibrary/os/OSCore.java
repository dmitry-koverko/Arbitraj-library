package com.kawka.arbitrajlibrary.os;

import android.content.Context;

import com.kawka.arbitrajlibrary.UserData;
import com.onesignal.OneSignal;

public class OSCore {

    public static void start(Context context){
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(context);
        OneSignal.setAppId(new UserData(context).getUserData(UserData.PREF_OneSignal_KEY));
    }

}
