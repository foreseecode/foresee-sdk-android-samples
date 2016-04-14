package com.foresee.onexitsurvey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import com.foresee.sdk.ForeSee;


public class OnExit extends Activity {
	
	Spinner spinner;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);
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
