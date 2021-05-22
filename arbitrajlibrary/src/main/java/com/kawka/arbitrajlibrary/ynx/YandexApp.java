package com.kawka.arbitrajlibrary.ynx;

import android.app.Application;
import android.content.Context;

import com.kawka.arbitrajlibrary.UserData;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class YandexApp {

    public static void start(Context context){
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(new UserData(context).getUserData(UserData.PREF_YANDEX_KEY)).build();
        YandexMetrica.activate(context, config);
        YandexMetrica.enableActivityAutoTracking((Application) context);
    }

}
