package com.foresee.sdk.tracker.demo.nobase;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.foresee.sdk.tracker.TrackerEventListener;
import com.foresee.sdk.tracker.app.TrackingContext;
import com.foresee.sdk.configuration.Configuration;
import com.foresee.sdk.configuration.MeasureConfiguration;
public class NoBaseClass extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Create a configuration to be used by the TrackingContext
        Configuration configuration 
        	= Configuration
        		.defaultConfiguration("7PzwF4wMfCv/r3yXCc0GFw==")
        		.addCpp("myCustomCpp", "myCppValue")
        		.addMeasure(MeasureConfiguration.defaultConfig("DefaultMeasure", "mobile", 0)
        				.withMaxLaunchCount(2)
        				.withMaxDaysSinceLaunch(0));
        TrackingContext.start(this.getApplication(), configuration);
        TrackingContext.Instance().applicationLaunched();
        TrackingContext.Instance().checkState();
        
		//log some events
		TrackingContext.Instance().setTrackerEventListener(new TrackerEventListener(){
	
		@Override
		public void onInviteAccepted(MeasureConfiguration measure) {
			Log.d("MainActivity", String.format("onInviteAccepted: name=%s, sid=%s", measure.getName(),measure.getSurveyId()));
		}
	
		@Override
		public void onInviteDeclined(MeasureConfiguration measure) {
			Log.d("MainActivity", String.format("onInviteDeclined: name=%s, sid=%s", measure.getName(),measure.getSurveyId()));
			
		}
	
		@Override
		public void onInvitePresented(MeasureConfiguration measure) {
			Log.d("MainActivity", String.format("onInvitePresented: name=%s, sid=%s", measure.getName(),measure.getSurveyId()));
			
		}
	
		@Override
		public void onSurveyAborted(MeasureConfiguration measure) {
			Log.d("MainActivity", String.format("onSurveyAborted: name=%s, sid=%s", measure.getName(),measure.getSurveyId()));
			
		}
	
		@Override
		public void onSurveyCompleted(MeasureConfiguration measure) {
			Log.d("MainActivity", String.format("onSurveyCompleted: name=%s, sid=%s", measure.getName(),measure.getSurveyId()));
			
		}

		@Override
		public void onSamplingCheckCompleted(MeasureConfiguration measure, boolean result) {
			Log.d("MainActivity", String.format("onSamplingCheckCompleted: name=%s, sid=%s, result=%s", measure.getName(),measure.getSurveyId(), new Boolean(result).toString()));
			
		}
		
	});
    }
    
    @Override
    public void onDestroy()
    {
		TrackingContext.Instance().applicationExited();
    	super.onDestroy();
    }
    
    /**
     * Since we are handling configuration changes
     *(android:configChanges="orientation|screenSize|keyboard|keyboardHidden"), we need 
     * to deal with orientation changes ourselves if we want to use a different layout
     * for each orientation. The code below will load the appropriate XML layout (either 
     * layout/main.xml or layout-land/main.xml, depending on the orientation).
     */
@Override
public void onConfigurationChanged(android.content.res.Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    switch(newConfig.orientation)
    {
        case android.content.res.Configuration.ORIENTATION_LANDSCAPE:
        case android.content.res.Configuration.ORIENTATION_PORTRAIT:
            setContentView(R.layout.main);
            break;
    }
}
	public void incrementLaunchCount(View view){
    	TrackingContext.Instance().applicationLaunched();
    	TrackingContext.Instance().checkState();
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetAll();
    }
}