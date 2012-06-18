package com.fsr.tracker.demo.exit;

import com.fsr.tracker.app.TrackingContext;
import com.fsr.tracker.domain.Configuration;
import com.fsr.tracker.domain.Configuration.LocalNotificationSurvey;
import com.fsr.tracker.domain.MeasureConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private Configuration configuration;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create a configuration to be used by the TrackingContext
        configuration = Configuration.defaultConfiguration("ForeSee","7PzwF4wMfCv/r3yXCc0GFw==")
        		.withCustomLogo("acme_logo.jpg")
        		.shouldPresentOnExit(true)
        		.addMeasure(MeasureConfiguration.defaultConfig("DefaultMeasure", "mobile", 0)
        				.withMaxLaunchCount(2));
        TrackingContext.Instance().initialize(this, configuration);
        TrackingContext.Instance().applicationLaunched();
        setContentView(R.layout.main);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int arg2, long arg3) {
				String selected = (String)parent.getItemAtPosition(arg2);
				if("Email or SMS notification".equals(selected))
				{
					configuration.shouldPresentOnExit(true);
					
				}
				else if("Local notification".equals(selected)){
					configuration.shouldPresentOnExit(true)
					.useLocalNotification(R.layout.notification, R.drawable.ic_invitation, LocalNotificationSurvey.IN_BROWSER);
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
	protected void onDestroy() {
		super.onDestroy();
		TrackingContext.Instance().applicationExited();
	}

	public void incrementLaunchCount(View view){
    	TrackingContext.Instance().applicationLaunched();
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetState();
    }
    private void reInitializeContext()
    {
    	TrackingContext.Instance().initialize(this, configuration);
    }

}