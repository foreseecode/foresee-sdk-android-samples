package com.foresee.advancedsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.foresee.sdk.ForeSeeCxMeasure

class LaunchCountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_count)

        // action bar
        supportActionBar!!.title = "Launch Count Sample"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        ForeSeeCxMeasure.checkIfEligibleForSurvey()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
