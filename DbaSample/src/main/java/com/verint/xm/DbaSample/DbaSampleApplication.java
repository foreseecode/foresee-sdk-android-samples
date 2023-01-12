package com.verint.xm.DbaSample;

import android.app.Application;
import android.util.Log;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.DBA;


public class DbaSampleApplication extends Application {

    private final Core.VerintSDKListener verintSDKListener = new Core.VerintSDKListener() {
        @Override
        public void onSDKStarted() {
            Log.i("VerintSDKListener", "onSDKStarted");
        }

        @Override
        public void onSDKStarted(Core.VerintError verintError, String errorDescription) {
            Log.i("VerintSDKListener", "onSDKStarted with error: " + verintError.name() + ": " + errorDescription);
        }

        @Override
        public void onSDKFailedToStart(Core.VerintError verintError, String errorDescription) {
            Log.i("VerintSDKListener", "onSDKFailedToStart with error: " + verintError.name() + ": " + errorDescription);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        // Regular startup
        Core.setDebugLogEnabled(true);
        Core.setSDKListener(verintSDKListener);
        Core.startWithAppId(this, Constants.APP_ID, Constants.CONFIG_VERSION);
        DBA.setMaskingDebugEnabled(true);
    }
}
