package com.fsr.tracker.demo.base;


import android.os.Bundle;
import android.view.View;

import com.fsr.tracker.app.*;
import com.fsr.tracker.domain.Configuration;
import com.fsr.tracker.domain.MeasureConfiguration;

public class MainActivity extends TrackerActivityBase {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected String getCustomerId() {
        return "7PzwF4wMfCv/r3yXCc0GFw==";
    }
    @Override
    protected  String getDefaultSurveyId() {
        return "mobile";
    }
	public void incrementLaunchCount(View view){
		TrackingContext.Instance().applicationLaunched();
		TrackingContext.Instance().checkState();
    }
    public void incrementSigEvent(View source)
    {
    	TrackingContext.Instance().incrementSignificantEventsCountWithKey("doSomething");
    	TrackingContext.Instance().checkState();
    }

	@Override
	protected Configuration getConfiguration() {
		Configuration config =  super.getConfiguration();
		//we can customize the configuration by overriding the getConfiguration() method
		//and adding our own settings
		return config
				.debug(true)
				.shouldRepeatSurveyAfterMinutes(3);
	}
    
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetState();
    }

	@Override
	protected String getCompanyName() {
		return "ForeSee";
	}
}