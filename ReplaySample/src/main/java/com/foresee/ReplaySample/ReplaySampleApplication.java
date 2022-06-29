package com.foresee.ReplaySample;

import android.app.Application;

import com.verint.xm.sdk.Core;
import com.verint.xm.sdk.Replay;


public class ReplaySampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Regular startup
        Core.setDebugLogEnabled(true);
        Core.setQALogsEnabled(true);
        Core.start(this);
        Replay.setMaskingDebugEnabled(true);
    }
}
