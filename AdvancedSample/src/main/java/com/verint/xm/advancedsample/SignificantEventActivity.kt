package com.verint.xm.advancedsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.verint.xm.sdk.SurveyManagement

class SignificantEventActivity : AppCompatActivity() {

    private var customEvent: Int = 0
        set(value) {
            field = value
            val textView: TextView = findViewById(R.id.significantEventTextView)
            textView.text = "Custom event value = " + value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_significant_event)

        customEvent = 0


        // action bar
        supportActionBar!!.title = "Significant Event Sample"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun onIncrementClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        customEvent++

        // Increment the significant event count so that we're eligible for an invite
        // based on the criteria in exp_configuration.json
        SurveyManagement.incrementSignificantEventCountWithKey("custom_event")

        // Launch an invite as a demo
        SurveyManagement.checkIfEligibleForSurvey()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
