package com.fsr.tracker.demo.nobase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.fsr.tracker.app.TrackingContext;
import com.fsr.tracker.demo.nobase.R;
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
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetState();
    }
}