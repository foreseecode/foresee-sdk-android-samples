package com.foresee.kotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.foresee.sdk.ForeSee
import com.foresee.sdk.ForeSeeCxMeasure

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }


    fun checkEligibility(@Suppress("UNUSED_PARAMETER") view: View) {
        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in foresee_configuration.json
        ForeSeeCxMeasure.incrementSignificantEventCountWithKey("instant_invite")

        // Launch an invite as a demo
        ForeSeeCxMeasure.checkIfEligibleForSurvey()
    }

    fun resetCounters(@Suppress("UNUSED_PARAMETER") view: View) {
        // Reset the state of the ForeSee SDK
        ForeSee.resetState()
    }
}
