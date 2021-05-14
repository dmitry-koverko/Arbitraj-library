package com.kawka.arbitrajlibrary;

import android.util.Log;

import com.kawka.arbitrajlibrary.app.App;

public class MyApp extends App {

    private final String TAG = MyApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }
}
