package com.kawka.arbitrajlibrary.wv;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;

import com.kawka.arbitrajlibrary.UserData;

public class ArbitrajWebView extends WebView implements IWV{

    private final String TAG = ArbitrajWebView.class.getName();

    private UserData uData;

    public ArbitrajWebView(Context context) {
        super(context);
        uData = new UserData(context.getApplicationContext());
        initView(context);
    }

    public ArbitrajWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        uData = new UserData(context.getApplicationContext());
        initView(context);
    }

    private void initView(Context context){
        // i am not sure with these inflater lines
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.getSettings().setJavaScriptEnabled(true) ;
        this.getSettings().setUseWideViewPort(true);
        this.getSettings().setLoadWithOverviewMode(true);
        this.getSettings().setDomStorageEnabled(true);
        this.setWebViewClient(new ArbitrajWebViewClient());
        this.setVisibility(GONE);
    }

    @Override
    public void start(Context context) {
        /*
            Делаем проверку на пустоту сохраненной ссылки.
            Если приложение запускается впервые, то обращаемся к серверу для получения новой ссылки, передавая параметры (app_name, naming)
         */

        Log.d(TAG, "PREF_NAMING = " + uData.getUserData(UserData.PREF_NAMING) + " ||||| " +
                "PREF_LINK = " + uData.getUserData(UserData.PREF_LINK) + " ||||| " +
                "PREF_HOST = " + uData.getUserData(UserData.PREF_HOST) + " ||||| ");

        if(new UserData(context).getUserData(UserData.PREF_HOST) == ""){
            Log.d(TAG, "PREF_HOST = " + uData.getUserData(UserData.PREF_HOST));
        }else if(uData.getUserData(UserData.PREF_NAMING)  != ""){

            if(uData.getUserData(UserData.PREF_LINK)  == ""){

                String app_name = context.getPackageName().replace(".", "");
                loadUrl(uData.getUserData(UserData.PREF_HOST) + "app_name=" +app_name + "&naming=" + uData.getUserData(UserData.PREF_NAMING) + "&key=" + uData.getUserData(UserData.PREF_USER_KEY));

            }else {
                loadUrl(uData.getUserData(UserData.PREF_LINK));
            }
        }

    }
}
