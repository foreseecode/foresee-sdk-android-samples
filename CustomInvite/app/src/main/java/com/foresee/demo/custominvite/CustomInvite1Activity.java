package com.foresee.demo.custominvite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.cxMeasure.tracker.listeners.IContactInviteResultListener;
import com.foresee.sdk.cxMeasure.tracker.listeners.ICustomContactInviteListener;

public class CustomInvite1Activity extends AppCompatActivity {

    private static final String TAG = "CustomInvite1Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_invite_1);
    }

    public void launchCustomInvite1(View view)
    {
        // For ON_EXIT notification, the contact details must be provided before the invite is accepted
        // Let's take them from the UI right now
        final EditText contactField = (EditText)findViewById(R.id.contactField);
        ForeSee.setContactDetails(contactField.getText().toString());

        ForeSee.setInviteListener(new ICustomContactInviteListener() {
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
            public void showInvite(IContactInviteResultListener iContactInviteResultListener) {
                Log.d(TAG, "showInvite");

                iContactInviteResultListener.contactInviteAccepted();
            }

            @Override
            public void onContactFormatError(IContactInviteResultListener iContactInviteResultListener) {
                Log.d(TAG, "onContactFormatError");
                contactField.setError(getString(R.string.FORESEE_invalidFormat));
            }

            @Override
            public void onContactMissing(IContactInviteResultListener iContactInviteResultListener) {
                Log.d(TAG, "onContactMissing");
                contactField.setError(getString(R.string.FORESEE_requiredField));
            }

            @Override
            public void onCompleteWithAccept() {
                Log.d(TAG, "onCompleteWithAccept");
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleteWithDecline() {
                Log.d(TAG, "onCompleteWithDecline");
            }

            @Override
            public void onCancelledWithNetworkError() {
                Log.d(TAG, "onCancelledWithNetworkError");
            }
        });


        // Launch an invite as a demo
        ForeSee.checkIfEligibleForSurvey();
        ForeSee.showInviteForSurveyID("app_test_2");

        // Hide keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
