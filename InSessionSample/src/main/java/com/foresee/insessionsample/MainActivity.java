package com.foresee.insessionsample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.foresee.sdk.ForeSee;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckEligibilityClicked(View view) {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSee.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        ForeSee.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view) {
        // Reset the SDK
        ForeSee.resetState();
    }
}
