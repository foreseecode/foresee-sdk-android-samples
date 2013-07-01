package com.fsr.tracker.demo.custom;

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
    	ForeSee.activityStarted(this);
    }
    @Override
    public void onPause() {
    	ForeSee.activityPaused(this);
    }
    @Override
    public void onResume() {
    	ForeSee.activityResumed(this);
    }
    
    public void resetCounters(View view)
    {
    	ForeSee.resetState();
    }
    public void launchInvite(View view)
    {
    	ForeSee.showInviteForSurveyID("app_test_1");
    }
}
