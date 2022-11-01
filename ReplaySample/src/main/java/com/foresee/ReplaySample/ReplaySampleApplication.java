package com.foresee.ReplaySample;

import android.app.Application;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.DBA;
import com.verint.xm.sdk.internal.CoreProxy;


public class ReplaySampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Regular startup
        Core.setDebugLogEnabled(true);
        Core.start(this);
        DBA.setMaskingDebugEnabled(true);
    }
}
