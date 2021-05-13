package com.kawka.arbitrajlibrary.app;

import android.app.Application;
import android.util.Log;

import com.kawka.arbitrajlibrary.UserData;

public class App extends Application {

    private final String TAG = App.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate Application class");
    }
}
