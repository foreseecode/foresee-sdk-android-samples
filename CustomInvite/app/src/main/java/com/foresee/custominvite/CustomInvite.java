package com.foresee.custominvite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.cxMeasure.instrumentation.CustomInviteBase;
import com.foresee.sdk.tracker.demo.custom.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class CustomInvite extends CustomInviteBase {

    /*****************************************************************************************************************************
     *                                                                                                                           *
     *  The basic requirements for a custom invite are:                                                                          *
     *  - The showInvite() method                                                                                                *
     *  - The call to onInviteClosed() whenever the custom invite is complete, whether successful or not                         *
     *                                                                                                                           *
     *  And for ON_EXIT invites:                                                                                                 *
     *  - The onContactError() method                                                                                            *
     *  - The call to ForeSee.setContactDetails() to let the SDK know which the contact details to use for the email/SMS         *
     *                                                                                                                           *
     *  The rest of the custom invite is entirely up to you                                                                      *
     *                                                                                                                           *
     ****************************************************************************************************************************/

    /**
     * This method is called when the ForeSee SDK decides to show an invite - you should use it to show your invite UI
     */

    @Override
    public void showInvite() {
        setSnackBarUpdateTimers();

        snackbarInvite = Snackbar.make(hostingView, getSnackbarMessage(), Snackbar.LENGTH_INDEFINITE);
        snackbarInvite.setAction("OK, sure", snackbarAction);

        // Every way of exiting the invite should result in a call to onClose; having accurate numbers on
        // accepts and declines helps us track the success rate of your invite strategy

        // In this case, the snackbar will dismiss after 15 seconds so we need to call onClose when it does

        snackbarInvite.setCallback(snackbarCallback);

        snackbarInvite.show();
    }

    /**
     * This method is called whenever there is an issue with the contact details supplied to the ForeSee SDK, which
     * only happens in the ON_EXIT invitation mode. In these cases, the user should be prompted to
     * re-enter their contact details
     */
    @Override
    public void onContactError(ContactError contactError) {
        if (contactError == ContactError.CONTACT_FORMAT) {
            showInputDialog("Please ensure your contact details are of the form you@example.com or 555-123-1234");
        }
        else if (contactError == ContactError.CONTACT_MISSING) {
            showInputDialog("Please enter some contact details");
        }

    }

    /**
     * As mentioned in the documentation, this method is optional. Sometimes, however, it may be desirable to let the
     * user know if they will receive a survey or not
     */
    @Override
    public void onInviteProcessComplete(InviteSubmissionResult submissionResult) {
        // By this point the SDK is finished with the invite process, this is for information only
        if (submissionResult == InviteSubmissionResult.COMPLETE) {
            Toast.makeText(hostingView.getContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();
        }
        else if (submissionResult == InviteSubmissionResult.FAIL_NETWORK) {
            // The user will be placed back into the pool to be re-sampled next time the eligibility check is requested
            Toast.makeText(hostingView.getContext(), "There was a problem contacting the server; no survey will be sent", Toast.LENGTH_SHORT).show();
        }
    }


    /*********************************************** FORESEE-REQUIRED CODE ENDS **************************************************
     *                                                                                                                           *
     * The remaining code in this class is only here to show how you might achieve a custom invite. You are free to invent your  *
     * own custom invite to fit with the interface we have provided by stripping out this code and replacing it with your own    *
     *                                                                                                                           *
     ****************************************************************************************************************************/


    View hostingView;
    Snackbar snackbarInvite;
    Timer snackBarTimer;
    Timer snackBarUpdateTimer;
    int snackBarLifetime;

    public CustomInvite(View hostingView) {
        super();
        this.hostingView = hostingView;
    }

    View.OnClickListener snackbarAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // The onInviteClosed method lets the SDK know that the invite is finished and what the user's response was
            // If you are using the ON_EXIT notification method, you should have informed the ForeSee SDK of contact
            // details by calling 'ForeSee.setContactDetails()' before calling this method

            // In this case, we are using this method to complete the process if the contact details have been added
            // to the EditText on the main page, but prompt the user via onContactError() if not

            CustomInvite.this.onInviteClosed(true);
        }
    };

    Snackbar.Callback snackbarCallback = new Snackbar.Callback() {
        @Override
        public void onDismissed(Snackbar snackbar, int event) {
            super.onDismissed(snackbar, event);
            if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT
                    || event == Snackbar.Callback.DISMISS_EVENT_MANUAL
                    || event == Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE
                    || event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {

                // Call the onClose method whenever the custom invite is dismissed

                onInviteClosed(false);
            }
        }

        @Override
        public void onShown(Snackbar snackbar) {
            super.onShown(snackbar);
        }
    };

    private void setSnackBarUpdateTimers() {
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
                hostingView.post(new Runnable() {
                    @Override
                    public void run() {
                        snackBarLifetime--;
                        snackbarInvite.setText(getSnackbarMessage());
                    }
                });
            }
        }, 0, 1000);
    }

    private String getSnackbarMessage() {
        String snackbarMessage = "Would you like to take a survey?\nThis popup will close in %d second%s";
        return String.format(Locale.US, snackbarMessage, snackBarLifetime, snackBarLifetime > 1 ? "s" : "");
    }

    /**
     * This is more optional code which adds a custom contact collection dialog
     */

    private void showInputDialog(String messageText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this.hostingView.getContext(), R.style.CustomTheme_Dialog));
        LayoutInflater inflater = (LayoutInflater)this.hostingView.getContext().getApplicationContext().getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.dialog_contact, (ViewGroup) this.hostingView, false);
        builder.setView(rootView);
        builder.setTitle("Contact details");

        final TextView messageView = (TextView)rootView.findViewById(R.id.message);
        final EditText input = (EditText)rootView.findViewById(R.id.contactInput);

        messageView.setText(messageText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ForeSee.setContactDetails(input.getText().toString());
                onInviteClosed(true);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                onInviteClosed(false);
            }
        });

        builder.show();
    }
}

