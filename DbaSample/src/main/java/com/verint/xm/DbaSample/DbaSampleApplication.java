package com.verint.xm.DbaSample;

import android.app.Application;
import android.util.Log;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.DBA;
import com.verint.xm.sdk.common.Logging;
import com.verint.xm.sdk.internal.CoreProxy;


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
        String appId = getApplicationContext().getPackageName();
        Core.startWithAppId(this, appId, Constants.FCP_VERSION);
        DBA.setMaskingDebugEnabled(true);
    }
}
