package com.foresee.kotlinsample

import android.app.Application
import com.foresee.sdk.ForeSee

class AdvancedSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Notify ForeSee SDK of application start
        ForeSee.setDebugLogEnabled(true)
        ForeSee.start(this)
    }
}