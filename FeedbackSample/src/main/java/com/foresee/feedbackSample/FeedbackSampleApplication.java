package com.foresee.feedbackSample;

import com.foresee.sdk.ForeSee;
import android.app.Application;

public class FeedbackSampleApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		// Start the ForeSee SDK
		ForeSee.start(this);
	}
}
