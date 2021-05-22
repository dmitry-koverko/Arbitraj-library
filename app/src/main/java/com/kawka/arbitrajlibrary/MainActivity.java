package com.kawka.arbitrajlibrary;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kawka.arbitrajlibrary.appsflyer.AppsFlyerCore;
import com.kawka.arbitrajlibrary.fb.FBCore;
import com.kawka.arbitrajlibrary.firebase.FEvents;
import com.kawka.arbitrajlibrary.os.OSCore;
import com.kawka.arbitrajlibrary.wv.ArbitrajWebView;
import com.kawka.arbitrajlibrary.ynx.YandexApp;

public class MainActivity extends AppCompatActivity {

    private ArbitrajWebView arbitrajWebView;
    private final Handler handler = new Handler();
    private final String TAG = MainActivity.class.getName();
    private AppEventsLogger mLogger;
    private DatabaseReference mDatabase;

    private UserData userData;

    private final Runnable runnable = new Runnable() {
        public void run() {
            check();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userData = new UserData(getApplicationContext());
        mLogger = AppEventsLogger.newLogger(getApplicationContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(userData.getUserData(UserData.PREF_AF_DEV_KEY) == "")
            userData.setUserData(UserData.PREF_AF_DEV_KEY, getApplicationContext().getResources().getString(R.string.af_dev_key));

        new FEvents().checkEvents(getApplicationContext(), mDatabase, mLogger);

        new FBCore(getApplicationContext(), mLogger);
        // TODO create new events()

        new AppsFlyerCore();
        arbitrajWebView = (ArbitrajWebView) findViewById(R.id.wv);

        if(userData.getUserData(UserData.PREF_HOST) == "") {
            userData.setUserData(UserData.PREF_HOST, getApplicationContext().getResources().getString(R.string.host));
        }

        if(new UserData(getApplicationContext()).getUserData(UserData.PREF_NAMING) == "") {
            task();
        }else arbitrajWebView.start(getApplicationContext());


        if(userData.getUserData(UserData.PREF_YANDEX_KEY) == ""){
            userData.setUserData(UserData.PREF_YANDEX_KEY, getApplicationContext().getResources().getString(R.string.yandex));
        }
        YandexApp.start(getApplicationContext());


        if(userData.getUserData(UserData.PREF_OneSignal_KEY) == ""){
            userData.setUserData(UserData.PREF_OneSignal_KEY, getApplicationContext().getResources().getString(R.string.one_signal));
        }
        OSCore.start(getApplicationContext());
    }

    private void task(){
        runnable.run();
    }

    private void check(){
        if(new UserData(getApplicationContext()).getUserData(UserData.PREF_NAMING) == ""){
            Log.d(TAG, "ждем нейминг ......" );
            handler.postDelayed(runnable, 1000);
        }
        else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    arbitrajWebView.start(getApplicationContext());
                }
            });



        }
    }
}