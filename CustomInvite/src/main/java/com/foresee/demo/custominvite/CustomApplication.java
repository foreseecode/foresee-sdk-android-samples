package com.foresee.demo.custominvite;

import android.app.Application;
import android.util.Log;

import com.foresee.sdk.ForeSee;

public class CustomApplication extends Application {
	private static final String TAG = "ForeSeeSDK-SampleApp";
	@Override
	public void onCreate() {
		super.onCreate();
		// Notify ForeSee SDK of application start
		ForeSee.setDebugLogEnabled(true);
		ForeSee.start(this, new ForeSee.ForeSeeSDKConfigurationListener() {
			@Override
			public void onSDKStarted() {
				Log.d(TAG, "onSDKReady");
			}

			@Override
			public void onSDKFailedToStart(ForeSee.ForeSeeError error) {
				Log.d(TAG, "onSDKFailedToStart. Reason: " + error.name());
			}
		});
	}
}
