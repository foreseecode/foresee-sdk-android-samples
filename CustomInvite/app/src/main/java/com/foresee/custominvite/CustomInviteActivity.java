package com.foresee.custominvite;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.tracker.demo.custom.R;

public class CustomInviteActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CustomInvite snackbarInvite = new CustomInvite(this.findViewById(android.R.id.content));
        ForeSee.setCustomInvite(snackbarInvite);
    }
    
    public void resetCounters(View view)
    {
    	// Reset the state of the ForeSee SDK
    	ForeSee.resetState();
    }
    public void launchInvite(View view)
    {
        // For ON_EXIT notification, the contact details must be provided before the invite is accepted
        // Let's take them from the UI right now

        EditText contactField = (EditText)findViewById(R.id.contactField);
        ForeSee.setContactDetails(contactField.getText().toString());

    	// Launch an invite as a demo

        ForeSee.showInviteForSurveyID("app_test_1");

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
