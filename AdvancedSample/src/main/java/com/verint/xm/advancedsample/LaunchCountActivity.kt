package com.verint.xm.advancedsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.verint.xm.sdk.Predictive

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

        Predictive.checkIfEligibleForSurvey()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
