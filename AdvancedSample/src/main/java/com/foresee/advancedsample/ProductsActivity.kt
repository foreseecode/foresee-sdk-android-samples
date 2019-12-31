package com.foresee.advancedsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.foresee.advancedsample.customInvite.CardData
import com.foresee.advancedsample.customInvite.InviteCardData
import com.foresee.advancedsample.customInvite.ProductCardData
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.foresee.advancedsample.customInvite.Adapter
import com.foresee.sdk.ForeSee
import com.foresee.sdk.common.configuration.EligibleMeasureConfigurations
import com.foresee.sdk.cxMeasure.tracker.listeners.CustomInSessionInviteListener


class ProductsActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "ProductsActivity"
    }

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        this.recyclerView = findViewById(R.id.recyclerView)
        val gridLayoutManager = GridLayoutManager(this@ProductsActivity, 2)
        this.recyclerView.layoutManager = gridLayoutManager

        setupCards(false)

        setupCustomInvite()

        // action bar
        supportActionBar!!.title = "Custom Invite Sample"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        ForeSee.checkIfEligibleForSurvey()
    }

    override fun onDestroy() {
        super.onDestroy()

        ForeSee.setInviteListener(null)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupCustomInvite() {
        ForeSee.setInviteListener(object : CustomInSessionInviteListener {
            override fun showInvite(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "showInvite")

                setupCards(true)
            }

            override fun onSurveyPresented() {
                Log.d(TAG, "onSurveyPresented")
                setupCards(false)
            }

            override fun onSurveyCancelledByUser() {
                Log.d(TAG, "onSurveyCancelledByUser")
            }

            override fun onSurveyCompleted() {
                Log.d(TAG, "onSurveyCompleted")
            }

            override fun onSurveyCancelledWithNetworkError() {
                Log.d(TAG, "onSurveyCancelledWithNetworkError")
                setupCards(false)
            }

            override fun onInviteCompleteWithAccept(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "onCompleteWithAccept")
                // By this point the SDK is finished with the invite process, this is for information only

            }

            override fun onInviteCompleteWithDecline(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "onCompleteWithDecline")
            }

            override fun onInviteNotShownWithNetworkError(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "onInviteNotShownWithNetworkError")
            }

            override fun onInviteNotShownWithEligibilityFailed(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "onInviteNotShownWithEligibilityFailed")
            }

            override fun onInviteNotShownWithSamplingFailed(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "onInviteNotShownWithSamplingFailed")
            }

        })
    }

    private fun setupCards(withInvite: Boolean) {
        val cards = mutableListOf<CardData>()

        cards.add(ProductCardData("Striped tee", R.drawable.top_0000))
        cards.add(ProductCardData("Bow-Collar Short Sleeve Blouse", R.drawable.top_0001))
        cards.add(ProductCardData("Basic button-up", R.drawable.top_0002))
        cards.add(ProductCardData("Striped ruffle button blouse", R.drawable.top_0003))
        cards.add(ProductCardData("Bow-hem diamond print blouse", R.drawable.top_0004))
        cards.add(ProductCardData("Floral crop top", R.drawable.top_0005))
        cards.add(ProductCardData("Sequined crop top", R.drawable.top_0006))
        cards.add(ProductCardData("Chiffon tank", R.drawable.top_0007))
        cards.add(ProductCardData("Cashmere Sweater", R.drawable.sweater))

        if (withInvite) {
            cards.add(3, InviteCardData("You are invited!", "Can we send you a brief survey?"))
        }

        this.recyclerView.adapter = Adapter(this, cards)
    }

    fun onInviteAcceptedClicked(view: View) {
        ForeSee.customInviteAccepted()

    }
}
