package com.kawka.arbitrajlibrary.fb;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;

import java.math.BigDecimal;
import java.util.Currency;

import static com.facebook.FacebookSdk.setAutoLogAppEventsEnabled;

public class FBCore {

    private final String TAG = FBCore.class.getName();

    private Context mContext;
    private AppEventsLogger mLogger;

    public FBCore(Context context, AppEventsLogger logger){
        mContext = context;
        mLogger = logger;
    }

    public void initEventsReg(){
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_REGISTRATION_METHOD, "Register");
        mLogger.logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION, params);
    }

    private void logSpentCreditsEvent (String contentId, String contentType, double totalValue) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, contentId);
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, contentType);
        mLogger.logEvent(AppEventsConstants.EVENT_NAME_SPENT_CREDITS, totalValue, params);
    }

    private void logAchievedLevelEvent (String level) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_LEVEL, level);
        mLogger.logEvent(AppEventsConstants.EVENT_NAME_ACHIEVED_LEVEL, params);
    }

    private void logUnlockedAchievementEvent (String description) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_DESCRIPTION, description);
        mLogger.logEvent(AppEventsConstants.EVENT_NAME_UNLOCKED_ACHIEVEMENT, params);
    }

    private void logPurchasedEvent (int numItems, String contentType, String contentId, String currency, BigDecimal price) {
        Bundle params = new Bundle();
        params.putInt(AppEventsConstants.EVENT_PARAM_NUM_ITEMS, numItems);
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, contentType);
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, contentId);
        params.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency);
        mLogger.logPurchase(price, Currency.getInstance("USD"), params);
    }

    public void initEvents(){
        Log.d(TAG, "init events ---------------------------------");
        initEventsReg();
        logSpentCreditsEvent("content", "type", 2);
        logAchievedLevelEvent("5");
        logUnlockedAchievementEvent("desc");
        logPurchasedEvent(1, "contenttype", "43", "USD", BigDecimal.valueOf((double) 324));
    }

}
