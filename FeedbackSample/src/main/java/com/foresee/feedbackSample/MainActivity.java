package com.foresee.feedbackSample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.foresee.sdk.ForeSeeFeedback;
import com.foresee.sdk.ForeSeeFeedbackListener;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);

        // Setup a ForeSee Feedback listener
        ForeSeeFeedback.setFeedbackListener(foreSeeFeedbackListener);
    }

    public void launchFeedback1(View view) {
        // Launch a feedback survey directly
        ForeSeeFeedback.showFeedbackForName("Sample 1");
    }

    public void launchFeedback2(View view) {
        // Check if a feedback survey is enabled, and only launch it when we know it is enabled
        ForeSeeFeedback.checkIfFeedbackEnabledForName("Sample 2");
    }

    private ForeSeeFeedbackListener foreSeeFeedbackListener = new ForeSeeFeedbackListener() {
        @Override
        public void onFeedbackPresented(String feedbackName) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackPresented(%s)", feedbackName));
        }

        @Override
        public void onFeedbackNotPresentedWithNetworkError(String feedbackName) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackNotPresentedWithNetworkError(%s)", feedbackName));
        }

        @Override
        public void onFeedbackNotPresentedWithDisabled(String feedbackName) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackNotPresentedWithDisabled(%s)", feedbackName));
        }

        @Override
        public void onFeedbackSubmitted(String feedbackName) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackSubmitted(%s)", feedbackName));
        }

        @Override
        public void onFeedbackNotSubmittedWithAbort(String feedbackName) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackNotSubmittedWithAbort(%s)", feedbackName));
        }

        @Override
        public void onFeedbackNotSubmittedWithNetworkError(String feedbackName) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackNotSubmittedWithNetworkError(%s)", feedbackName));
        }

        @Override
        public void onFeedbackStatusRetrieved(String feedbackName, boolean enabled) {
            Log.d("ForeSeeFeedbackTest", String.format("onFeedbackStatusRetrieved(%s, %b)", feedbackName, enabled));

            if (enabled) {
                ForeSeeFeedback.showFeedbackForName(feedbackName);
            }
        }
    };
}
