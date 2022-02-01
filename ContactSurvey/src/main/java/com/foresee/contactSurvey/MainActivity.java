package com.foresee.contactSurvey;

import android.content.Intent;
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

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Launch an invite as a demo if we're eligible
        ForeSeeCxMeasure.checkIfEligibleForSurvey();
    }

    public void launchInvite(View view) {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSeeCxMeasure.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo if we're eligible
        ForeSeeCxMeasure.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view) {
    	// Reset the state of the ForeSee SDK
    	ForeSee.resetState();
    }

    public void setContactDetails(View view) {
        startActivity(new Intent(this, SetContactDetailsActivity.class));
    }
}
