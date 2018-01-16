package com.foresee.contactSurvey;

import com.foresee.sdk.ForeSee;

import android.app.Application;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		// Notify ForeSee SDK of application start
		ForeSee.setDebugLogEnabled(true);
		ForeSee.start(this);
	}
}
