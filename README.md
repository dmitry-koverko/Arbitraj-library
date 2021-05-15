# Arbitraj-library

-- Add to manifest com.kawka.arbitrajlibrary.app.App
-- add host to resourses String

-- add to manifest

    android:usesCleartextTraffic="true"

    <uses-permission android:name="android.permission.INTERNET"/>
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
        <uses-permission android:name="android.permission.VIBRATE" />
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />


-- add FACEBOOK SDK

implementation 'com.facebook.android:facebook-android-sdk:[5,6)'
implementation 'com.android.installreferrer:installreferrer:1.1'


<meta-data
            android:name="com.facebook.sdk.AutoLogAppEventsEnabled"
            android:value="true" />
        <meta-data
            android:name="com.facebook.sdk.AdvertiserIDCollectionEnabled"
            android:value="true" />

        <provider
            android:name="com.facebook.FacebookContentProvider"
            android:authorities="com.facebook.app.FacebookContentProvidercom.kawka.mixgenderapp"
            android:exported="true" />

        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id" />



 <string name="facebook_app_id">304923447884758</string>
 <string name="fb_login_protocol_scheme">fb304923447884758</string>


 new FBCore(getApplicationContext());



 -- AppsFlyerLib

 if(new UserData(getApplicationContext()).getUserData(UserData.PREF_AF_DEV_KEY).equals(""))
             new UserData(getApplicationContext()).setUserData(UserData.PREF_AF_DEV_KEY,
                     getApplicationContext().getResources().getString(R.string.af_dev_key));


         new AppsFlyerCore();


   add key_dev to resourse file
   
   
   -- add YandexMetric
   
   if(new UserData(getApplicationContext()).getUserData(UserData.PREF_YANDEX_KEY) == ""){
               new UserData(getApplicationContext()).setUserData(UserData.PREF_YANDEX_KEY, getApplicationContext().getResources().getString(R.string.yandex));
           }else YandexApp.start(getApplicationContext());
           
   add key to resources
   
   -- add OneSigal
   
   if(new UserData(getApplicationContext()).getUserData(UserData.PREF_OneSignal_KEY) == ""){
               new UserData(getApplicationContext()).setUserData(UserData.PREF_OneSignal_KEY, getApplicationContext().getResources().getString(R.string.one_signal));
               YandexApp.start(getApplicationContext());
           }else OSCore.start(getApplicationContext());
   add key to resources        



-- Firebase  

 <meta-data
            android:name="com.google.firebase.messaging.default_notification_icon"
            android:resource="@drawable/ic_launcher_foreground" />
        <!-- Set color used with incoming notification messages. This is used when no color is set for the incoming
             notification message. See README(https://goo.gl/6BKBk7) for more. -->
        <meta-data
            android:name="com.google.firebase.messaging.default_notification_color"
            android:resource="@color/colorAccent" />
