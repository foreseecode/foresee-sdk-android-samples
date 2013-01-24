package com.fsr.tracker.demo.custom;

import com.fsr.tracker.app.DefaultStringsProvider;
import com.fsr.tracker.app.TrackingContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TrackingContext.start(this);
        TrackingContext.Instance().applicationLaunched();
        TrackingContext.Instance().checkState();
    }
    
    @Override
    public void onDestroy()
    {
    	TrackingContext.stop();
    	super.onDestroy();
    }
    
    public void resetCounters(View view)
    {
    	TrackingContext.Instance().resetAll();
    }
    public void launchInvite(View view)
    {
//        is this right?
//    	TrackingContext.Instance().triggerInvitation("mobile");
    	TrackingContext.Instance().triggerInvitation("mobile");
    }
}
