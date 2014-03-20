package com.foresee.sdk.tracker.demo.exit;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.configuration.Configuration;
import com.foresee.sdk.tracker.demo.exit.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


public class OnExit extends Activity {
	
	Spinner spinner;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);
     }
    
    @Override
    public void onStart() {
    	super.onStart();
    	// Notify ForeSee SDK of activity start
    	ForeSee.activityStarted(this);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	// Notify ForeSee SDK of activity resume
    	ForeSee.activityResumed(this);
    	ForeSee.checkIfEligibleForSurvey();
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	// Notify ForeSee SDK of activity pause
    	ForeSee.activityPaused(this);
    }   
       
    public void launchInvite(View view)
    {
    	// Launch an invite as a demo
    	ForeSee.showInviteForSurveyID("app_test_1");
    }

    public void resetCounters(View view)
    {
    	// Reset the state of the ForeSee SDK
    	ForeSee.resetState();
    }
}
