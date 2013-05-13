package com.fsr.tracker.demo.exit;

import com.fsr.tracker.app.TrackingContext;
import com.foresee.sdk.configuration.Configuration;
import com.foresee.sdk.configuration.MeasureConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class OnExit extends Activity {
	private Configuration configuration;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create a configuration to be used by the TrackingContext
        configuration = Configuration.defaultConfiguration("7PzwF4wMfCv/r3yXCc0GFw==")
        		.withCustomLogo("acme_logo.jpg")
        		.shouldPresentOnExit()
        		.addMeasure(MeasureConfiguration.defaultConfig("DefaultMeasure", "mobile", 0)
        				.withMaxLaunchCount(2)
        				.withMaxDaysSinceLaunch(0));
        TrackingContext.start(this.getApplication(), configuration);
        TrackingContext.Instance().applicationLaunched();
        TrackingContext.Instance().checkState();
        setContentView(R.layout.main);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int arg2, long arg3) {
				String selected = (String)parent.getItemAtPosition(arg2);
				if("Email or SMS notification".equals(selected))
				{
					configuration.shouldPresentOnExit();
				}
				else if("Local notification".equals(selected)){
					configuration.shouldPresentOnExitLocal();
				}
				reInitializeContext();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}

        });

    }
    
    @Override
    public void onDestroy()
    {
		TrackingContext.Instance().applicationExited();
    	super.onDestroy();
    }
    
	public void incrementLaunchCount(View view){
    	TrackingContext.Instance().applicationLaunched();
    	TrackingContext.Instance().checkState();
    	TrackingContext.Instance().triggerInvitation("mobile");
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetAll();
    }
    private void reInitializeContext()
    {
<<<<<<< HEAD:samples/on-exit-survey/src/com/fsr/tracker/demo/exit/MainActivity.java
    	TrackingContext.start(this);
=======
        TrackingContext.start(this.getApplication(), configuration);
>>>>>>> Adjusted samples and docs to match latest version:samples/on-exit-survey/src/com/fsr/tracker/demo/exit/OnExit.java
    }

}
