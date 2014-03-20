package com.foresee.sdk.tracker.demo.custom;

import com.foresee.sdk.ForeSee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CustomInvite extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	// Notify ForeSee SDK of activity pause
    	ForeSee.activityPaused(this);
    }   
       
    public void resetCounters(View view)
    {
    	// Reset the state of the ForeSee SDK
    	ForeSee.resetState();
    }
    public void launchInvite(View view)
    {
    	// Launch an invite as a demo
    	ForeSee.showInviteForSurveyID("app_test_1");
    }
}
