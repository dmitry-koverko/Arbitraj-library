package com.kawka.arbitrajlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.kawka.arbitrajlibrary.appsflyer.AppsFlyerCore;
import com.kawka.arbitrajlibrary.fb.FBCore;
import com.kawka.arbitrajlibrary.wv.ArbitrajWebView;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {

    private ArbitrajWebView arbitrajWebView;
    private final Handler handler = new Handler();
    private final String TAG = MainActivity.class.getName();

    private final Runnable runnable = new Runnable() {
        public void run() {
            check();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(new UserData(getApplicationContext()).getUserData(UserData.PREF_AF_DEV_KEY) == "")
            new UserData(getApplicationContext()).setUserData(UserData.PREF_AF_DEV_KEY,
                    getApplicationContext().getResources().getString(R.string.af_dev_key));

        new FBCore(getApplicationContext());
        new AppsFlyerCore();

        arbitrajWebView = (ArbitrajWebView) findViewById(R.id.wv);

        if(new UserData(getApplicationContext()).getUserData(UserData.PREF_HOST) == "") {
            new UserData(getApplicationContext()).setUserData(UserData.PREF_HOST, getApplicationContext().getResources().getString(R.string.host));
        }

        if(new UserData(getApplicationContext()).getUserData(UserData.PREF_NAMING) == "") {
            task();
        }else arbitrajWebView.start(getApplicationContext());


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