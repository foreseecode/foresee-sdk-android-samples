package com.foresee.localizationSample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.ForeSeeCxMeasure;


public class MainActivity extends AppCompatActivity {
	
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
        ForeSeeCxMeasure.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        ForeSeeCxMeasure.checkIfEligibleForSurvey();
    }

    public void resetState(View view) {
        // Reset the state of the ForeSee SDK
        ForeSee.resetState();
    }
}
