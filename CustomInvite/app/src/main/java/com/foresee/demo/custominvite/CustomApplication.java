package com.foresee.demo.custominvite;

import android.app.Application;

import com.foresee.sdk.ForeSee;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		// Notify ForeSee SDK of application start
		ForeSee.setDebugLogEnabled(true);
		ForeSee.start(this);
	}
}
