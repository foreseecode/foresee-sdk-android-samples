package com.foresee.demo.custominvite;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.ForeSeeCxMeasure;
import com.foresee.sdk.common.configuration.ContactType;
import com.foresee.sdk.common.configuration.EligibleMeasureConfigurations;
import com.foresee.sdk.cxMeasure.tracker.listeners.CustomContactInviteListener;

public class CustomInvite1Activity extends AppCompatActivity {

    private static final String TAG = "CustomInvite1Activity";

    // Variables
    private ProgressDialog progressDialog;
    private EditText contactInput;
    private RadioGroup preferredContactType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_invite_1);

        // Find UI components
        contactInput = (EditText)findViewById(R.id.contactInput);
        preferredContactType = (RadioGroup)findViewById(R.id.preferredContactType);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ForeSeeCxMeasure.setInviteListener(new CustomContactInviteListener() {

            @Override
            public void showInvite(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "showInvite");

                showProgress();

                ForeSeeCxMeasure.customInviteAccepted();

            }

            @Override
            public void onContactFormatError() {
                Log.d(TAG, "onContactFormatError");
                contactInput.setError(getString(R.string.FORESEE_contactDetailsInvalidInputError));

                hideProgress();
            }

            @Override
            public void onContactMissing() {
                Log.d(TAG, "onContactMissing");
                contactInput.setError(getString(R.string.FORESEE_contactDetailsEmptyInputError));

                hideProgress();
            }

            @Override
            public void onInviteCompleteWithAccept(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onCompleteWithAccept");
                // By this point the SDK is finished with the invite process, this is for information only
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + ForeSeeCxMeasure.getContactDetails(), Toast.LENGTH_SHORT).show();

                hideProgress();

                //Reset
                ForeSee.resetState();
            }

            @Override
            public void onInviteCompleteWithDecline(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
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
            public void onInviteNotShownWithNetworkError(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteNotShownWithNetworkError");
                Toast.makeText(getApplicationContext(), "Invitation not shown with network error", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithEligibilityFailed(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with eligibility failed", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteNotShownWithSamplingFailed(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed");
                Toast.makeText(getApplicationContext(), "Invitation not shown with sampling failed", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

        });
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Setup UI components
        ContactType type = ForeSeeCxMeasure.getPreferredContactType();
        if (type != null) {
            switch (type) {
                case Email:
                    preferredContactType.check(R.id.preferredContactTypeEmail);
                    break;
                case PhoneNumber:
                    preferredContactType.check(R.id.preferredContactTypePhoneNumber);
                    break;
                default:
                    break;
            }
            contactInput.setText(ForeSeeCxMeasure.getContactDetails(type));
        }
    }

    public void launchCustomInvite1(View view)
    {

        showProgress();

        // For Contact Invite mode, the contact details must be provided before the invite is accepted
        // Let's take them from the UI right now
        ContactType type = ContactType.PhoneNumber;
        switch (preferredContactType.getCheckedRadioButtonId()) {
            case R.id.preferredContactTypeEmail:
                type = ContactType.Email;
                ForeSeeCxMeasure.setPreferredContactType(ContactType.Email);
                break;
            case R.id.preferredContactTypePhoneNumber:
                type = ContactType.PhoneNumber;
                ForeSeeCxMeasure.setPreferredContactType(ContactType.PhoneNumber);
                break;
        }
        ForeSeeCxMeasure.setPreferredContactType(type);
        ForeSeeCxMeasure.setContactDetails(type, contactInput.getText().toString());

        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSeeCxMeasure.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        ForeSeeCxMeasure.checkIfEligibleForSurvey();

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

    public void resetState(View view)
    {
        // Reset the state of the ForeSee SDK. So that we may be eligible for a new invite
        // based on the criteria in foresee_configuration.json
        ForeSee.resetState();
    }
}
