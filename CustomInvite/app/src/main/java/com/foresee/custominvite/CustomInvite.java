package com.foresee.custominvite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.tracker.demo.custom.R;

public class CustomInvite extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
