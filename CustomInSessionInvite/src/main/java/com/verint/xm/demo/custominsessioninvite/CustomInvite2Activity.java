package com.verint.xm.demo.custominsessioninvite;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.SurveyManagement;
import com.verint.xm.sdk.common.configuration.ContactType;
import com.verint.xm.sdk.common.configuration.EligibleMeasureConfigurations;
import com.verint.xm.sdk.common.storyEngine.listeners.CustomContactInviteListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class CustomInvite2Activity extends AppCompatActivity {

    private static final String TAG = "CustomInvite2Activity";

    private Snackbar snackbarInvite;
    private Timer snackBarTimer;
    private Timer snackBarUpdateTimer;
    private int snackBarLifetime;
    private ProgressDialog progressDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_invite_2);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void launchCustomInvite2(View view) {

        SurveyManagement.setInviteListener(new CustomContactInviteListener() {
            @Override
            public void showInvite(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "showInvite");

                snackbarInvite = Snackbar.make(findViewById(R.id.coordinator_layout),
                        getSnackbarMessage(), Snackbar.LENGTH_INDEFINITE);
                snackbarInvite.setAction("OK, sure", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProgress();

                        SurveyManagement.customInviteAccepted();
                    }
                });

                setSnackBarUpdateTimers();

                // Every way of exiting the invite should result in a call to iContactInviteResultListener.contactInviteAccepted() or iContactInviteResultListener.contactInviteDeclined();
                // having accurate numbers on accepts and declines helps us track the success rate of your invite strategy

                // In this case, the Snackbar will dismiss after 15 seconds so we need to call iContactInviteResultListener.contactInviteDeclined() when it does
                snackbarInvite.setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT
                                || event == Snackbar.Callback.DISMISS_EVENT_MANUAL
                                || event == Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE
                                || event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {

                            // Call the iContactInviteResultListener.contactInviteDeclined() method whenever the custom invite is dismissed
                            SurveyManagement.customInviteDeclined();
                        }
                    }
                });

                View view = snackbarInvite.getView();
                TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snackbarInvite.show();
            }

            @Override
            public void onContactFormatError() {
                Log.d(TAG, "onContactFormatError");

                hideProgress();

                showInputDialog("Please ensure your contact details", getApplicationContext().getString(R.string.FORESEE_contactDetailsInvalidInputError));
            }

            @Override
            public void onContactMissing() {
                Log.d(TAG, "onContactMissing");

                hideProgress();

                showInputDialog("Please enter some contact details", null);
            }

            @Override
            public void onInviteCompleteWithAccept(EligibleMeasureConfigurations eligibleMeasureConfigurations) {
                Log.d(TAG, "onCompleteWithAccept");
                // By this point the SDK is finished with the invite process, this is for information only
                Toast.makeText(getApplicationContext(), "A survey will be sent to " + SurveyManagement.getContactDetails(SurveyManagement.getPreferredContactType()), Toast.LENGTH_SHORT).show();

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


        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        SurveyManagement.incrementSignificantEventCountWithKey("instant_invite");

        // Launch an invite as a demo
        SurveyManagement.checkIfEligibleForSurvey();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (snackBarTimer != null) {
            snackBarTimer.cancel();
        }
        if (snackBarUpdateTimer != null) {
            snackBarUpdateTimer.cancel();
        }
        if (snackbarInvite != null && snackbarInvite.isShown()) {
            snackbarInvite.dismiss();
        }

    }

    private void setSnackBarUpdateTimers()
    {
        if (snackBarTimer != null) {
            snackBarTimer.cancel();
        }
        if (snackBarUpdateTimer != null) {
            snackBarUpdateTimer.cancel();
        }

        snackBarLifetime = 15;

        snackBarTimer = new Timer();
        snackBarTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                snackBarUpdateTimer.cancel();
                snackbarInvite.dismiss();
            }
        }, snackBarLifetime * 1000);

        snackBarUpdateTimer = new Timer();
        snackBarUpdateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        snackBarLifetime--;
                        snackbarInvite.setText(getSnackbarMessage());
                    }
                });
            }
        }, 0, 1000);
    }


    private String getSnackbarMessage()
    {
        String snackbarMessage = "Would you like to take a survey?\nThis popup will close in %d second%s";
        return String.format(Locale.US, snackbarMessage, snackBarLifetime, snackBarLifetime > 1 ? "s" : "");
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

    private void showInputDialog(String messageText, String errorMessage)
    {
        AlertDialog.Builder builder  = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomTheme_Dialog));
        View                rootView = getLayoutInflater().inflate(R.layout.dialog_contact, null);
        builder.setView(rootView);
        builder.setTitle("Contact details");

        final TextView messageView = (TextView)rootView.findViewById(R.id.message);
        final TextInputLayout inputLayout = (TextInputLayout)rootView.findViewById(R.id.contactInputLayout);
        final EditText contactInput = (EditText)rootView.findViewById(R.id.contactInput);
        final RadioGroup preferredContactType = (RadioGroup)rootView.findViewById(R.id.preferredContactType);

        messageView.setText(messageText);

        // Setup UI components
        ContactType type = SurveyManagement.getPreferredContactType();
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
            contactInput.setText(SurveyManagement.getContactDetails(type));
        }

        if (errorMessage != null) {
            inputLayout.setError(errorMessage);
        } else {
            inputLayout.setErrorEnabled(false);
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactType type = ContactType.PhoneNumber;
                switch (preferredContactType.getCheckedRadioButtonId()) {
                    case R.id.preferredContactTypeEmail:
                        type = ContactType.Email;
                        SurveyManagement.setPreferredContactType(ContactType.Email);
                        break;
                    case R.id.preferredContactTypePhoneNumber:
                        type = ContactType.PhoneNumber;
                        SurveyManagement.setPreferredContactType(ContactType.PhoneNumber);
                        break;
                }
                SurveyManagement.setPreferredContactType(type);
                SurveyManagement.setContactDetails(type, contactInput.getText().toString());
                showProgress();
                SurveyManagement.customInviteAccepted();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showProgress();
                SurveyManagement.customInviteDeclined();
            }
        });

        builder.show();

    }

    public void resetState(View view)
    {
        // Reset the state of the ForeSee SDK. So that we may be eligible for a new invite
        // based on the criteria in foresee_configuration.json
        Core.resetState();
    }

}
