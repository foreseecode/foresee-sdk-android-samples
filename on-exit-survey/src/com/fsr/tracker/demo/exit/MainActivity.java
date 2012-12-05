package com.fsr.tracker.demo.exit;

import com.fsr.tracker.app.TrackingContext;
import com.fsr.tracker.domain.Configuration;

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
        
        // Load the configuration from configuration.json
    	try
    	{
	        if (TrackingContext.Instance().start(this))
	        {
	                configuration = TrackingContext.Instance().getConfiguration();        		
	        }
	        else
	        {
	        	// Configuration not loaded - handle error here
	        }
    	}
    	catch (RuntimeException e)
    	{
    		Log.e("FORESEE", e.toString());
    	}
                
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
	protected void onDestroy() {
		super.onDestroy();
		TrackingContext.Instance().applicationExited();
	}

	public void incrementLaunchCount(View view){
    	TrackingContext.Instance().applicationLaunched();
    	TrackingContext.Instance().checkState();
    }
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetAll();
    }
    private void reInitializeContext()
    {
    	TrackingContext.Instance().initialize(this, configuration);
    }

}