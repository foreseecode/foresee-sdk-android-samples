package com.fsr.tracker.demo;

import android.app.Activity;
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
    
    public void doSomething(View source)
    {
    	TrackingContext.Instance().incrementSignificantEventsCountWithKey("doSomething");
    }

	@Override
	protected Configuration getConfiguration() {
		Configuration config =  super.getConfiguration();
		//we can customize the configuration by overriding the getConfiguration() method
		//and adding our own settings
		return config
				.addMeasure(MeasureConfiguration.defaultConfig("Measure 2", "mobile", 1)
						.addSignificantEventThreshold("doSomething", 5));
	}
    
    
}