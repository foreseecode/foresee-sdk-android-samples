package com.foresee.demo.custominvite;

import android.app.Application;
import android.util.Log;

import com.verint.xm.sdk.Core;

public class CustomApplication extends Application {
	private static final String TAG = "ForeSeeSDK-SampleApp";
	@Override
	public void onCreate() {
		super.onCreate();
		// Notify ForeSee SDK of application start
		Core.setDebugLogEnabled(true);
		Core.start(this);
		Core.setSDKListener(new Core.VerintSDKListener() {

			@Override
			public void onSDKStarted() {
				Log.d(TAG, "onSDKReady");
			}

			@Override
			public void onSDKStarted(Core.VerintError error, String s) {
				Log.d(TAG, "onSDKStarted with errors. Reason: " + error.name());
			}

			@Override
			public void onSDKFailedToStart(Core.VerintError error, String reason) {
				Log.d(TAG, "onSDKFailedToStart. Reason: " + error.name());
			}
		});
	}
}
