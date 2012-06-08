package com.fsr.tracker.demo;

import android.app.Activity;
import android.os.Bundle;
import com.fsr.tracker.app.*;

public class MainActivity extends TrackerActivityBase {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected String getCustomerId() {
        return "7PzwF4wMfCv/r3yXCc0GFw==";
    }
    @Override
    protected  String getDefaultSurveyId() {
        return "mobile";
    }
}