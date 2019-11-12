package com.foresee.advancedsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.foresee.sdk.ForeSee

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    fun resetCounters(@Suppress("UNUSED_PARAMETER") view: View) {
        // Reset the state of the ForeSee SDK
        ForeSee.resetState()
    }

    fun onPageViewsClicked(view: View) {
        ForeSee.resetState()

        PageActivity.pageId = 1
        // Invite will be triggered after 3 page views
        val intent = Intent(this, PageActivity::class.java)
        startActivity(intent)
    }

    fun onLaunchCountClicked(view: View) {
        ForeSee.resetState()

        // Invite will be triggered after 5 launch count
        val intent = Intent(this, LaunchCountActivity::class.java)
        startActivity(intent)
    }

    fun onSignificantEventClicked(view: View) {
        ForeSee.resetState()

        // Invite will be triggered after 5 launch count
        val intent = Intent(this, SignificantEventActivity::class.java)
        startActivity(intent)
    }
}
