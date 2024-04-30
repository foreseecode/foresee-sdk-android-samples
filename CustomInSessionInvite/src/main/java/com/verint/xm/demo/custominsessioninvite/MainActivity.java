package com.verint.xm.demo.custominsessioninvite;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;
import com.verint.xm.sdk.common.configuration.EligibleMeasureConfigurations;
import com.verint.xm.sdk.common.storyEngine.listeners.CustomInSessionInviteListener;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        SurveyManagement.setInviteListener(new CustomInSessionInviteListener() {
            @Override
            public void showInvite(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                showProgress();

                SurveyManagement.customInviteAccepted();
            }

            @Override
            public void onSurveyPresented() {
                Log.d(TAG, "onSurveyPresented");
                hideProgress();
            }

            @Override
            public void onSurveyCompleted() {
                Log.d(TAG, "onSurveyCompleted");
                hideProgress();
            }

            @Override
            public void onSurveyCancelledByUser() {
                Log.d(TAG, "onSurveyCancelledByUser");
                hideProgress();
            }

            @Override
            public void onSurveyCancelledWithNetworkError() {
                Log.d(TAG, "onSurveyNotShownWithNetworkError");
                Toast.makeText(getApplicationContext(), "Survey not shown with network error", Toast.LENGTH_SHORT).show();

                hideProgress();
            }

            @Override
            public void onInviteCompleteWithAccept(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onCompleteWithAccept");
                // By this point the SDK is finished with the invite process, this is for information only
                hideProgress();

                //Reset
                Core.resetState();
            }

            @Override
            public void onInviteCompleteWithDecline(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onCompleteWithDecline");
                Toast.makeText(getApplicationContext(), "Invitation declined by user", Toast.LENGTH_SHORT).show();

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

    public void launchCustomInvite(View view)
    {
        showProgress();

        // Launch an invite as a demo
        SurveyManagement.checkIfEligibleForSurvey();
    }

    public void fulfilCriteria(View view) {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        SurveyManagement.incrementSignificantEventCountWithKey("instant_invite");
    }

    public void resetState(View view) {
        // Reset the state of the ForeSee SDK. So that we may be eligible for a new invite
        // based on the criteria in foresee_configuration.json
        Core.resetState();
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
