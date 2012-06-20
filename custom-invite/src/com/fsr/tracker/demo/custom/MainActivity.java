package com.fsr.tracker.demo.custom;

import com.fsr.tracker.app.DefaultStringsProvider;
import com.fsr.tracker.app.TrackingContext;
import com.fsr.tracker.domain.Configuration;
import com.fsr.tracker.domain.MeasureConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Create a configuration to be used by the TrackingContext
        Configuration configuration = Configuration.defaultConfiguration("ForeSee","7PzwF4wMfCv/r3yXCc0GFw==")
        		.withCustomLogo("com/fsr/tracker/demo/custom/acme_logo.jpg")
        		.addMeasure(MeasureConfiguration.defaultConfig("DefaultMeasure", "mobile", 0)
        				.withMaxDaysSinceLaunch(30));
        TrackingContext.Instance().initialize(this, configuration);
        TrackingContext.Instance().applicationLaunched();
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetState();
    }
    public void launchInvite(View view)
    {
    	TrackingContext.Instance().triggerInvitation("DefaultMeasure");
    }
    private class MyStringsProvider extends DefaultStringsProvider
    {
    	

		@Override
		public String getInviteTitle() {
			return "How are we doing?";
		}

		@Override
		public String getInviteText() {
			return "We'd like to invite you to participate in a brief customer satisfaction survey";
		}

		@Override
		public String getAcceptButtonText() {
			return "Yes";
		}

		@Override
		public String getDeclineButtonText() {
			return "No";
		}
		
    }
}