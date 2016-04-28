package com.foresee.demo.custominvite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.cxMeasure.tracker.listeners.IDefaultInviteListener;

public class DefaultInviteActivity extends AppCompatActivity {

    private static final String TAG = "DefaultInviteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_invite);

    }


    public void launchDefaultInvite(View view)
    {
        //Set Listeners (optional)
        ForeSee.setInviteListener(new IDefaultInviteListener() {
            @Override
            public void onInviteNotShownWithNetworkError() {
                Log.d(TAG, "onInviteNotShownWithNetworkError");
            }

            @Override
            public void onInviteNotShownWithEligibilityFailed() {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed");
            }

            @Override
            public void onInviteNotShownWithSamplingFailed() {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed");
            }

            @Override
            public void onInvitePresented() {
                Log.d(TAG, "onInvitePresented");
            }

            @Override
            public void onInviteAccepted() {
                Log.d(TAG, "onInviteAccepted");
            }

            @Override
            public void onInviteDeclined() {
                Log.d(TAG, "onInviteDeclined");
            }

            @Override
            public void onSurveyPresented() {
                Log.d(TAG, "onSurveyPresented");
            }

            @Override
            public void onSurveyCompleted() {
                Log.d(TAG, "onSurveyCompleted");
            }

            @Override
            public void onSurveyCancelled() {
                Log.d(TAG, "onSurveyCancelled");
            }

            @Override
            public void onInviteCancelledWithNetworkError() {
                Log.d(TAG, "onInviteCancelledWithNetworkError");
            }
        });

        // Launch an invite as a demo
        ForeSee.checkIfEligibleForSurvey();
        ForeSee.showInviteForSurveyID("app_test_1");
    }
}
