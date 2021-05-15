package com.kawka.arbitrajlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class UserData implements IUserData{

    private static String TAG = UserData.class.getName();

    private SharedPreferences settings;
    private SharedPreferences.Editor prefEditor;
    private Context mContext;
    private static final String PREFS_FILE = "User";
    public static final String PREF_HOST = "host";
    public static final String PREF_LINK = "Link";
    public static final String PREF_NAMING= "Naming";
    public static final String PREF_USER_KEY= "User_key";
    public static final String PREF_AF_DEV_KEY= "AF_DEV_KEY";

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

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

        Log.d(TAG, "setUserData: " + key + " : " + data);
    }

    @Override
    public void generateRandomUID() {
        setUserData(PREF_USER_KEY, getRandomString(25));

    }

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }


}
