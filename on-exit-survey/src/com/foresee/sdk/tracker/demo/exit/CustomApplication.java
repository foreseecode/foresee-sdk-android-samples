package com.foresee.sdk.tracker.demo.exit;

import com.foresee.sdk.ForeSee;

import android.app.Application;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		// Notify ForeSee SDK of application start
		ForeSee.start(this);
	}
}
