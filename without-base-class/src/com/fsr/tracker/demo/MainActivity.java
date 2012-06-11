package com.fsr.tracker.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.fsr.tracker.app.TrackingContext;
import com.fsr.tracker.demo.R;
import com.fsr.tracker.domain.*;
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Create a configuration to be used by the TrackingContext
        Configuration configuration = Configuration.defaultConfiguration("7PzwF4wMfCv/r3yXCc0GFw==")
        		.addMeasure(MeasureConfiguration.defaultConfig("DefaultMeasure", "mobile", 0)
        				.withMaxLaunchCount(2));
        TrackingContext.Instance().initialize(this, configuration);
        TrackingContext.Instance().applicationLaunched();
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetState();
    }
}