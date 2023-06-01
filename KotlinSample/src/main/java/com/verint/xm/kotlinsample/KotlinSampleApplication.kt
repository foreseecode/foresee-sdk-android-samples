package com.verint.xm.kotlinsample

import android.app.Application
import com.verint.xm.sdk.Core

class KotlinSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Notify ForeSee SDK of application start
        Core.setDebugLogEnabled(true)
        Core.start(this)
    }
}