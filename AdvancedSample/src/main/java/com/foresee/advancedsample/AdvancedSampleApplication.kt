package com.foresee.advancedsample

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.foresee.sdk.ForeSee

class AdvancedSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Notify ForeSee SDK of application start
        ForeSee.setDebugLogEnabled(true)
        ForeSee.start(this)
    }
}

fun Activity.popToRootActivity() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}