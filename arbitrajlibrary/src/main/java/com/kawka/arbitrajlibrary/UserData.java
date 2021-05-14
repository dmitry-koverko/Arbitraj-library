package com.kawka.arbitrajlibrary;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class UserData implements IUserData{

    private SharedPreferences settings;
    private SharedPreferences.Editor prefEditor;
    private Context mContext;
    private static final String PREFS_FILE = "User";
    public static final String PREF_HOST = "host";
    public static final String PREF_LINK = "Link";
    public static final String PREF_NAMING= "Naming";
    public static final String PREF_AF_DEV_KEY= "AF_DEV_KEY";

    public UserData(Context context){
        mContext = context;
        settings = context.getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        prefEditor = settings.edit();
    }


    @Override
    public String getUserData(String key) {
        return settings.getString(key,"");
    }

    @Override
    public void setUserData(String key, String data) {
        prefEditor.putString(key, data);
        prefEditor.apply();
    }
}
