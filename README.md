# Arbitraj-library

-- Add to manifest com.kawka.arbitrajlibrary.app.App

    public class App extends Application {
    
        private final String TAG = MyApp.class.getName();
        private UserData userData;
    
        @Override
        public void onCreate() {
            super.onCreate();
            Log.d(TAG, "- onCreate Application");
    
            FacebookSdk.sdkInitialize(getApplicationContext());
            AppEventsLogger.activateApp(this);
            FirebaseApp.initializeApp(getApplicationContext());
    
            FacebookSdk.setAutoInitEnabled(true);
            setAutoLogAppEventsEnabled(true);
            FacebookSdk.fullyInitialize();
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
    
            /*
                Проверяеи, чему равен хост по умолчанию, если он пустой, то выводим в логи ошибку
             */
    
            userData = new UserData(getApplicationContext());
            if(userData.getUserData(UserData.PREF_USER_KEY)  == "" ){
                userData.generateRandomUID();
            }
        }
    }
    
-- add MainActivity.java 

    public class MainActivity extends AppCompatActivity {
    
        private ArbitrajWebView arbitrajWebView;
        private final Handler handler = new Handler();
        private final String TAG = MainActivity.class.getName();
    
    
    
        private final Runnable runnable = new Runnable() {
            public void run() {
                check();
            }
        };
    
    //    AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
    //        @Override
    //        public void onConversionDataSuccess(Map<String, Object> map) {
    //
    //            String media_source = map.get("media_source").toString();
    //            String campaign = map.get("campaign").toString();
    //            String af_status = map.get("af_status").toString(); // Non-organic
    //
    //            String params = "&media_source=" + media_source + "&campaign=" + campaign;
    //
    //            if(af_status.equals("Non-organic")){
    //                new UserData(getApplicationContext()).setUserData(UserData.PREF_NAMING, params);
    //            }else {
    //                new UserData(getApplicationContext()).setUserData(UserData.PREF_NAMING, "null");
    //            }
    //
    //            for (String attrName : map.keySet()) {
    //                Log.d("LOG_TAG", "attribute: " + attrName + " = " + map.get(attrName));
    //            }
    //        }
    //
    //        @Override
    //        public void onConversionDataFail(String errorMessage) {
    //            Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);
    //            new UserData(getApplicationContext()).setUserData(UserData.PREF_NAMING, "null");
    //        }
    //
    //        @Override
    //        public void onAppOpenAttribution(Map<String, String> attributionData) {
    //            for (String attrName : attributionData.keySet()) {
    //                Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
    //            }
    //        }
    //
    //        @Override
    //        public void onAttributionFailure(String errorMessage) {
    //            Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
    //        }
    //    };
    
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
            }else {
                arbitrajWebView.start(getApplicationContext());
                arbitrajWebView.setVisibility(View.VISIBLE);
            }
    
    
            if(new UserData(getApplicationContext()).getUserData(UserData.PREF_YANDEX_KEY) == ""){
                new UserData(getApplicationContext()).setUserData(UserData.PREF_YANDEX_KEY, getApplicationContext().getResources().getString(R.string.yandex));
                YandexApp.start(getApplicationContext());
            }else YandexApp.start(getApplicationContext());
    
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
                        arbitrajWebView.setVisibility(View.VISIBLE);
                    }
                });
    
    
    
            }
        }
    
    }
    
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
   
    Project-level build.gradle (<project>/build.gradle):
    classpath 'gradle.plugin.com.onesignal:onesignal-gradle-plugin:[0.12.10, 0.99.99]'
    
    repositories {
            google()
            mavenCentral()
            gradlePluginPortal()
            jcenter()
        }
        
     allprojects {
         repositories {
             google()
             mavenCentral()
             jcenter()
         }
     }
   
    App-level build.gradle (<project>/<app-module>/build.gradle):
    plugins {
        id 'com.onesignal.androidsdk.onesignal-gradle-plugin'
    }
    implementation 'com.onesignal:OneSignal:[4.0.0, 4.99.99]'

   
   if(new UserData(getApplicationContext()).getUserData(UserData.PREF_OneSignal_KEY) == ""){
               new UserData(getApplicationContext()).setUserData(UserData.PREF_OneSignal_KEY, getApplicationContext().getResources().getString(R.string.one_signal));
               YandexApp.start(getApplicationContext());
           }else OSCore.start(getApplicationContext());
   add key to resources        



-- Firebase  

    Project-level build.gradle (<project>/build.gradle):
    classpath 'com.google.gms:google-services:4.3.8'
    
    App-level build.gradle (<project>/<app-module>/build.gradle):
    apply plugin: 'com.google.gms.google-services'
    implementation platform('com.google.firebase:firebase-bom:28.0.1')
    implementation 'com.google.firebase:firebase-analytics'
    implementation 'com.google.firebase:firebase-messaging:21.1.0'
    implementation 'com.google.firebase:firebase-database:19.4.0'

 <meta-data
            android:name="com.google.firebase.messaging.default_notification_icon"
            android:resource="@drawable/ic_launcher_foreground" />
        <!-- Set color used with incoming notification messages. This is used when no color is set for the incoming
             notification message. See README(https://goo.gl/6BKBk7) for more. -->
        <meta-data
            android:name="com.google.firebase.messaging.default_notification_color"
            android:resource="@color/colorAccent" />
