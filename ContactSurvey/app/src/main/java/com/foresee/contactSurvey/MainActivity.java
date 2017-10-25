package com.foresee.contactSurvey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import com.foresee.sdk.ForeSee;


public class MainActivity extends Activity {
	
	Spinner spinner;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);
     }

    public void launchInvite(View view)
    {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSee.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        ForeSee.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view)
    {
    	// Reset the state of the ForeSee SDK
    	ForeSee.resetState();
    }
}
