package com.foresee.feedbackSample;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.verint.xm.sdk.Digital;
import com.verint.xm.sdk.ExpDigitalListener;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);

        // Setup a ForeSee Feedback listener
        Digital.setDigitalListener(expDigitalListener);
    }

    public void launchFeedback1(View view) {
        // Launch a feedback survey directly
        Digital.showDigitalSurveyForName("Sample 1");
    }

    public void launchFeedback2(View view) {
        // Check if a feedback survey is enabled, and only launch it when we know it is enabled
        Digital.checkIfDigitalSurveyEnabledForName("Sample 2");
    }

    private ExpDigitalListener expDigitalListener = new ExpDigitalListener() {
        @Override
        public void onDigitalSurveyPresented(String s) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveyPresented(%s)", s));
        }

        @Override
        public void onDigitalSurveyNotPresentedWithNetworkError(String s) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveyNotPresentedWithNetworkError(%s)", s));
        }

        @Override
        public void onDigitalSurveyNotPresentedWithDisabled(String s) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveyNotPresentedWithDisabled(%s)", s));
        }

        @Override
        public void onDigitalSurveySubmitted(String s) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveySubmitted(%s)", s));
        }

        @Override
        public void onDigitalSurveyNotSubmittedWithAbort(String s) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveyNotSubmittedWithAbort(%s)", s));
        }

        @Override
        public void onDigitalSurveyNotSubmittedWithNetworkError(String s) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveyNotSubmittedWithNetworkError(%s)", s));
        }

        @Override
        public void onDigitalSurveyStatusRetrieved(String s, boolean enabled) {
            Log.d("EXPDigitalTest", String.format("onDigitalSurveyStatusRetrieved(%s)", s));

            if (enabled) {
                Digital.showDigitalSurveyForName(s);
            }
        }
    };
}
