package com.foresee.advancedsample

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.verint.xm.sdk.Core

class AdvancedSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Notify ForeSee SDK of application start
        Core.setDebugLogEnabled(true)
        Core.start(this)
    }
}

fun Activity.popToRootActivity() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}