package com.fsr.tracker.demo.custom;

import com.foresee.sdk.ForeSee;

import android.app.Application;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		ForeSee.start(this);
	}
}
