package com.foresee.feedbackSample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.foresee.sdk.ForeSee;
import com.foresee.sdk.ForeSeeCxMeasure;
import com.foresee.sdk.ForeSeeFeedback;
import com.foresee.sdk.ForeSeeFeedbackListener;


public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Do normal UI setup
        setContentView(R.layout.main);
    }

    public void launchFeedback(View view)
    {
        ForeSeeFeedback.setFeedbackListener(new ForeSeeFeedbackListener() {
            @Override
            public void onFeedbackPresented(String s) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackPresented");
            }

            @Override
            public void onFeedbackNotPresentedWithNetworkError(String s) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackNotPresentedWithNetworkError");
            }

            @Override
            public void onFeedbackNotPresentedWithDisabled(String s) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackNotPresentedWithDisabled");
            }

            @Override
            public void onFeedbackSubmitted(String s) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackSubmitted");
            }

            @Override
            public void onFeedbackNotSubmittedWithAbort(String s) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackNotSubmittedWithAbort");
            }

            @Override
            public void onFeedbackNotSubmittedWithNetworkError(String s) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackNotSubmittedWithNetworkError");
            }

            @Override
            public void onFeedbackStatusRetrieved(String s, boolean b) {
                Log.d("ForeSeeFeedbackTest", "onFeedbackStatusRetrieved");
            }
        });
        ForeSeeFeedback.showFeedbackForName("prod");
    }
}
