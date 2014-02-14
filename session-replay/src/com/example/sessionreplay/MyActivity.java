package com.example.sessionreplay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.foresee.sdk.ForeSee;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ForeSee.activityStarted(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ForeSee.activityPaused(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ForeSee.activityResumed(this);
    }

    public void showInvite(View view) {
        ForeSee.showInviteForSurveyID("app_test_1");
    }

}
