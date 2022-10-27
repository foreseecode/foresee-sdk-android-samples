package com.foresee.kotlinsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.verint.xm.sdk.Core
import com.verint.xm.sdk.Predictive

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }


    fun checkEligibility(@Suppress("UNUSED_PARAMETER") view: View) {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        Predictive.incrementSignificantEventCountWithKey("instant_invite")

        // Launch an invite as a demo
        Predictive.checkIfEligibleForSurvey()
    }

    fun resetCounters(@Suppress("UNUSED_PARAMETER") view: View) {
        // Reset the state of the ForeSee SDK
        Core.resetState()
    }
}
