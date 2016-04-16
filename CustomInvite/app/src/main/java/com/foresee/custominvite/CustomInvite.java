package com.foresee.custominvite;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.cxMeasure.Instrumentation.CustomInviteBase;
import com.foresee.sdk.cxMeasure.Instrumentation.InviteCallback;


/**
 * Created by selwynleeke on 16-04-15.
 */
public class CustomInvite extends CustomInviteBase {

    View hostingView;
    Snackbar snackbarInvite;

    public CustomInvite(View hostingView) {
        super();
        this.hostingView = hostingView;
    }

    /**
     * This method is called when the ForeSee SDK decides to show an invite
     */
    @Override
    public void show() {
        snackbarInvite = Snackbar.make(hostingView, "Would you like to take a survey?", Snackbar.LENGTH_INDEFINITE);
        snackbarInvite.setAction("Sure", snackbarAction);
        snackbarInvite.show();
    }

    /**
     * This callback is defined to let the custom invite know if the invite has been processed correctly
     * This is particularly useful for invites where email/SMS information is collected and sent to ForeSee servers
     *
     * Use the different InviteResults to decide whether to dismiss your invite can be dismissed
     * or whether you need to recapture contact details
     */
    InviteCallback inviteCallback = new InviteCallback() {
        @Override
        public void inviteResponded(InviteResult inviteResult) {
            if (inviteResult == InviteResult.COMPLETE) {
                Toast.makeText(hostingView.getContext(), "A survey will be sent to " + ForeSee.getContactDetails(), Toast.LENGTH_SHORT).show();
            }
            else if (inviteResult == InviteResult.FAIL_CONTACT_FORMAT) {
                Toast.makeText(hostingView.getContext(), "Your contact details are incorrectly formatted", Toast.LENGTH_SHORT).show();
            }
            else if (inviteResult == InviteResult.FAIL_CONTACT_MISSING) {
                Toast.makeText(hostingView.getContext(), "You didn't enter any contact details", Toast.LENGTH_SHORT).show();
            }
            else if (inviteResult == InviteResult.FAIL_NETWORK) {
                Toast.makeText(hostingView.getContext(), "There was a problem contacting the server", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener snackbarAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // The onClose method lets the SDK know that the invite is finished and what the user's response was
            // If you are using the ON_EXIT notification method, you must have informed the ForeSee SDK of contact
            // details by calling 'ForeSee.setContactDetails()' before calling this method

            CustomInvite.this.onClose(true, inviteCallback);
        }
    };
}
