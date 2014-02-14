package com.example.sessionreplay;

import android.app.Application;
import com.foresee.sdk.ForeSee;

/**
 * Created by mhan on 2014-02-13.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ForeSee.start(this);
    }
}
