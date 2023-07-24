package com.verint.xm.advancedsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.verint.xm.sdk.SurveyManagement

class PageActivity : AppCompatActivity() {

    companion object {
        var pageId = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        if (pageId >= 3) {
            val button: Button = findViewById(R.id.nextButton)
            button.text = "Done"
            button.setOnClickListener {
                this.popToRootActivity()
            }
        }

        val textView: TextView = findViewById(R.id.pageViewCountTextView)
        textView.text = "Page #: " + pageId++

        // action bar
        supportActionBar!!.title = "Page Views Sample"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        SurveyManagement.checkIfEligibleForSurvey()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @Suppress("UNUSED_VARIABLE")
    fun onNextClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        // Invite will be triggered after 3 page views
        val intent = Intent(this, PageActivity::class.java)
        startActivity(intent)
    }
}
