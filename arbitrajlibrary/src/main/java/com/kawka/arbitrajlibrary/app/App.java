package com.kawka.arbitrajlibrary.app;

import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.kawka.arbitrajlibrary.UserData;
import com.kawka.arbitrajlibrary.firebase.FEvents;

public class App extends Application {

    private final String TAG = App.class.getName();
    private UserData userData;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "- onCreate Application");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        /*
            Проверяеи, чему равен хост по умолчанию, если он пустой, то выводим в логи ошибку
         */

        userData = new UserData(getApplicationContext());
        if(userData.getUserData(UserData.PREF_USER_KEY)  == "" ){
           userData.generateRandomUID();
        }

        new FEvents().checkEvents(getApplicationContext());

    }
}
