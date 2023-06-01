package com.verint.xm.localizationSample;

import android.app.Application;

import com.verint.xm.sdk.Core;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		// Notify ForeSee SDK of application start
		Core.setDebugLogEnabled(true);
		Core.start(this);
	}
}
