package com.verint.xm.advancedsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.verint.xm.sdk.Core

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    fun resetCounters(@Suppress("UNUSED_PARAMETER") view: View) {
        // Reset the state of the ForeSee SDK
        Core.resetState()
    }

    fun onPageViewsClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        PageActivity.pageId = 1
        // Invite will be triggered after 3 page views
        val intent = Intent(this, PageActivity::class.java)
        startActivity(intent)
    }

    fun onLaunchCountClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        // Invite will be triggered after 5 launch count
        val intent = Intent(this, LaunchCountActivity::class.java)
        startActivity(intent)
    }

    fun onSignificantEventClicked(@Suppress("UNUSED_PARAMETER") view: View) {


        // Invite will be triggered after 5 launch count
        val intent = Intent(this, SignificantEventActivity::class.java)
        startActivity(intent)
    }

    fun onCustomInviteClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(this, ProductsActivity::class.java)
        startActivity(intent)
    }
}
