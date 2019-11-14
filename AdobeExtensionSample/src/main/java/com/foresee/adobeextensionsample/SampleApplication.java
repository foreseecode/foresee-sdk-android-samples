package com.foresee.adobeextensionsample;

import android.app.Application;
import android.util.Log;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.Lifecycle;
import com.adobe.marketing.mobile.LoggingMode;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Signal;
import com.adobe.marketing.mobile.UserProfile;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.ForeSeeAdobeExtension;

public class SampleApplication extends Application {

    static final String TAG = "ForeSeeSample";

    @Override
    public void onCreate() {
        super.onCreate();

        registerAdobeExtension();
    }

    ForeSee.ForeSeeSDKConfigurationListener configurationListener = new ForeSee.ForeSeeSDKConfigurationListener() {
        @Override
        public void onSDKReady() {
            Log.d(TAG, "ForeSee SDK is ready.");
        }

        @Override
        public void onFailedInitializingSDK() {
            Log.e(TAG, "Failed to start the ForeSee SDK.");
        }
    };

    private void registerAdobeExtension() {
        MobileCore.setApplication(this);
        MobileCore.setLogLevel(LoggingMode.DEBUG);
        try {
            ForeSeeAdobeExtension.setForeSeeSDKConfigurationListener(configurationListener);
            ForeSeeAdobeExtension.registerExtension();

            Identity.registerExtension();
            Lifecycle.registerExtension();
            Signal.registerExtension();
            UserProfile.registerExtension();
            MobileCore.start(new AdobeCallback() {
                @Override
                public void call(Object o) {
                    MobileCore.configureWithAppID("launch-your-appID");
                }
            });
        } catch (Exception ex) {
            Log.d(TAG, "Failed to register the Adobe extension: " + ex.getMessage());
        }
    }
}
