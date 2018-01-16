package com.foresee.demo.custominvite;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.common.configuration.MeasureConfiguration;
import com.foresee.sdk.cxMeasure.tracker.listeners.CustomContactInviteListener;

public class CustomInvite1Activity extends AppCompatActivity {

    private static final String TAG = "CustomInvite1Activity";
    private ProgressDialog progressDialog;
    private EditText contactField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_invite_1);

        contactField = (EditText)findViewById(R.id.contactField);
        if (ForeSee.getContactDetails() != null) {
            contactField.setText(ForeSee.getContactDetails());
        }

        ForeSee.setInviteListener(new CustomContactInviteListener() {
            @Override
            public void showInvite(MeasureConfiguration measureConfiguration) {
                Log.d(TAG, "showInvite");

                showProgress();

                ForeSee.customInviteAccepted();

            }

            @Override
            public void onContactFormatError() {
                Log.d(TAG, "onContactFormatError");
                contactField.setError(getString(R.string.FORESEE_contactDetailsInvalidInputError));

                hideProgress();
            }

            @Override
            public void onContactMissing() {
                Log.d(TAG, "onContactMissing");
                contactField.setError(getString(R.string.FORESEE_contactDetailsEmptyInputError));

                hideProgress();
            }

            @Override
            public void onInviteCompleteWithAccept() {
                Log.d(TAG, "onCompleteWithAccept");
                // By this point the SDK is finished with the invite process, this is for information only
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();

                hideProgress();

                //Reset
                ForeSee.resetState();
            }

            @Override
            public void onInviteCompleteWithDecline() {
                Log.d(TAG, "onCompleteWithDecline");
                Toast.makeText(getApplicationContext(), "Invitation declined by user", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteCancelledWithNetworkError() {
                Log.d(TAG, "onCancelledWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation cancelled with network error", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithNetworkError(MeasureConfiguration measureConfiguration) {
                Log.d(TAG, "onInviteNotShownWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation not shown with network error", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithEligibilityFailed(MeasureConfiguration measureConfiguration) {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with eligibility failed", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithSamplingFailed(MeasureConfiguration measureConfiguration) {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with sampling failed", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

        });


    }

    public void launchCustomInvite1(View view)
    {

        showProgress();

        // For ON_EXIT notification, the contact details must be provided before the invite is accepted
        // Let's take them from the UI right now
        ForeSee.setContactDetails(contactField.getText().toString());

        // Launch an invite as a demo
        ForeSee.showInviteForSurveyID("app_test_1");

        // Hide keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void showProgress()
    {
        if (progressDialog == null || (!progressDialog.isShowing())) {
            progressDialog = ProgressDialog.show(this, "", "Please wait...", true, true);
        }
    }

    private void hideProgress()
    {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
