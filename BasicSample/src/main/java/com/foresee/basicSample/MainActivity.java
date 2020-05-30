package com.foresee.basicSample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.ForeSeeCxMeasure;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckEligibilityClicked(View view) {

        // Launch an invite as a demo
        ForeSeeCxMeasure.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view) {
        // Reset the SDK
        ForeSee.resetState();
    }
}
