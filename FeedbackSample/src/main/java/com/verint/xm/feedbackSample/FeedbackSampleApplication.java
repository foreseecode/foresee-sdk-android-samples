package com.verint.xm.feedbackSample;

import android.app.Application;

import com.verint.xm.sdk.Core;

public class FeedbackSampleApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		// Start the ForeSee SDK
		Core.start(this);
	}
}
