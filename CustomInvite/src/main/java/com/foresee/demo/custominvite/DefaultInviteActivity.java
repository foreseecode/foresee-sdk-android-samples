package com.foresee.demo.custominvite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.common.configuration.EligibleMeasureConfigurations;
import com.foresee.sdk.cxMeasure.tracker.listeners.DefaultInviteListener;

public class DefaultInviteActivity extends AppCompatActivity {

    private static final String TAG = "DefaultInviteActivity";

    private EditText contactField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_invite);

        contactField = (EditText)findViewById(R.id.contactField);
        if (ForeSee.getContactDetails() != null) {
            contactField.setText(ForeSee.getContactDetails());
        }

        //Set Listeners (optional)
        ForeSee.setInviteListener(new DefaultInviteListener() {
            @Override
            public void onInvitePresented(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInvitePresented");
                Toast.makeText(getApplicationContext(), "Invite presented", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteCompleteWithAccept(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteAccepted");
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();

                //Reset
                ForeSee.resetState();
            }

            @Override
            public void onInviteCompleteWithDecline(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteDeclined");
                Toast.makeText(getApplicationContext(), "Invitation declined by user", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyPresented(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onSurveyPresented");
                Toast.makeText(getApplicationContext(), "Survey presented", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyCompleted(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onSurveyCompleted");
                Toast.makeText(getApplicationContext(), "Survey completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyCancelledByUser(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onSurveyCancelled");
                Toast.makeText(getApplicationContext(), "Survey cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSurveyCancelledWithNetworkError(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onSurveyCancelledWithNetworkError");
                Toast.makeText(getApplicationContext(), "Survey cancelled with network error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteCancelledWithNetworkError(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteCancelledWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation cancelled with network error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteNotShownWithNetworkError(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteNotShownWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation not shown with network error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteNotShownWithEligibilityFailed(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with eligibility failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInviteNotShownWithSamplingFailed(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with sampling failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void launchDefaultInvite(View view)
    {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSee.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        ForeSee.checkIfEligibleForSurvey();
    }

    public void launchDefaultInviteWithContactDetails(View view)
    {
        // Set contact details
        // TODO: We should make use of the new setContactDetails API (MOBILSDK-2187)
        ForeSee.setContactDetails(contactField.getText().toString());

        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSee.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        ForeSee.checkIfEligibleForSurvey();
    }

    public void resetState(View view)
    {
        // Reset the state of the ForeSee SDK. So that we may be eligible for a new invite
        // based on the criteria in foresee_configuration.json
        ForeSee.resetState();
    }
}
