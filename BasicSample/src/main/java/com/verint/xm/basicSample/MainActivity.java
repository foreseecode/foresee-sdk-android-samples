package com.verint.xm.basicSample;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckEligibilityClicked(View view) {

        // Launch an invite as a demo
        SurveyManagement.checkIfEligibleForSurvey();
    }

    public void resetCounters(View view) {
        // Reset the SDK
        Core.resetState();
    }
}
