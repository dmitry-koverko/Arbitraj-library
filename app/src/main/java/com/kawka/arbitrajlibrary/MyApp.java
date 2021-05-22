package com.kawka.arbitrajlibrary;

import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.FirebaseApp;
import com.kawka.arbitrajlibrary.firebase.FEvents;

import static com.facebook.FacebookSdk.setAutoLogAppEventsEnabled;

public class MyApp extends Application {

    private final String TAG = MyApp.class.getName();
    private UserData userData;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "- onCreate Application");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        FirebaseApp.initializeApp(getApplicationContext());

        FacebookSdk.setAutoInitEnabled(true);
        setAutoLogAppEventsEnabled(true);
        FacebookSdk.fullyInitialize();
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);

        /*
            Проверяеи, чему равен хост по умолчанию, если он пустой, то выводим в логи ошибку
         */

        userData = new UserData(getApplicationContext());
        if(userData.getUserData(UserData.PREF_USER_KEY)  == "" ){
            userData.generateRandomUID();
        }
    }
}
