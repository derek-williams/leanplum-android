package com.derek.tictactoe;
import android.app.Application;
import android.content.res.Configuration;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by derekwilliams on 2/1/18.
 */

public class TicTacToe extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!

        Leanplum.setApplicationContext(this);

        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        // Insert your API keys here.
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_H91bpadV0q6DpBHmH9cshCdJsxVU3LQsQhBQTPAKAYk", "dev_DTkh2KbUEJdTxUGUTq1MsVhYnv9xzWs4jHK9etDNHFg");
        } else {
            Leanplum.setAppIdForProductionMode("app_H91bpadV0q6DpBHmH9cshCdJsxVU3LQsQhBQTPAKAYk", "prod_dC9VMUkMResITEboR4Y5iuHVOYn5s50UuQhi6Pgwgqo");
        }

        // Optional: Tracks all screens in your app as states in Leanplum.
        // Leanplum.trackAllAppScreens();

        // Enable push notifications.

        // Option 2: Firebase Cloud Messaging
        // Be sure to upload your Server API key to our dashboard.
        LeanplumPushService.enableFirebase();

        // This will only run once per session, even if the activity is restarted.
        //Leanplum.start(this);

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Leanplum.track("Launch");
            }
        });

        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("gender", "Male");
        attributes.put("age", 24);
        Leanplum.start(this, attributes);
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}