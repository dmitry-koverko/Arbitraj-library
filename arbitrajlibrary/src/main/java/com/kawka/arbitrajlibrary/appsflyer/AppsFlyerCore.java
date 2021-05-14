package com.kawka.arbitrajlibrary.appsflyer;

import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.kawka.arbitrajlibrary.UserData;

import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AppsFlyerCore {

    AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
        @Override
        public void onConversionDataSuccess(Map<String, Object> map) {

            String media_source = map.get("media_source").toString();
            String campaign = map.get("campaign").toString();
            String af_status = map.get("af_status").toString(); // Non-organic

            String params = "&media_source=" + media_source + "&campaign=" + campaign;

            if(af_status.equals("Non-organic")){
                new UserData(getApplicationContext()).setUserData(UserData.PREF_NAMING, params);
            }else {
                new UserData(getApplicationContext()).setUserData(UserData.PREF_NAMING, "null");
            }

            for (String attrName : map.keySet()) {
                Log.d("LOG_TAG", "attribute: " + attrName + " = " + map.get(attrName));
            }
        }

        @Override
        public void onConversionDataFail(String errorMessage) {
            Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);
            new UserData(getApplicationContext()).setUserData(UserData.PREF_NAMING, "null");
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> attributionData) {
            for (String attrName : attributionData.keySet()) {
                Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
            }
        }

        @Override
        public void onAttributionFailure(String errorMessage) {
            Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
        }
    };


    public AppsFlyerCore(){
        AppsFlyerLib.getInstance().init(new UserData(getApplicationContext()).getUserData(UserData.PREF_AF_DEV_KEY), conversionListener, getApplicationContext());
        AppsFlyerLib.getInstance().start(getApplicationContext());
    }

}
