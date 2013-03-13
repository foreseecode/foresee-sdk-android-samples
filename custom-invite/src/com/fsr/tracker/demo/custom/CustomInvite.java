package com.fsr.tracker.demo.custom;

import com.fsr.tracker.app.DefaultStringsProvider;
import com.fsr.tracker.app.TrackingContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CustomInvite extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Create a configuration to be used by the TrackingContext
        Configuration configuration = Configuration.defaultConfiguration("7PzwF4wMfCv/r3yXCc0GFw==")
        		.withCustomLogo("com/fsr/tracker/demo/custom/acme_logo.jpg")
        		.addMeasure(MeasureConfiguration.defaultConfig("DefaultMeasure", "mobile", 0)
        				.withMaxDaysSinceLaunch(30));
        TrackingContext.start(this.getApplication(), configuration);
        TrackingContext.Instance().applicationLaunched();
        TrackingContext.Instance().checkState();
    }
    
    @Override
    public void onDestroy()
    {
    	TrackingContext.stop();
    	super.onDestroy();
    }
    
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetAll();
    }
    public void launchInvite(View view)
    {

    	TrackingContext.Instance().triggerInvitation("DefaultMeasure");
    }
}
