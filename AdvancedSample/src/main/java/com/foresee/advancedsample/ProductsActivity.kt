package com.foresee.advancedsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.foresee.advancedsample.customInvite.CardData
import com.foresee.advancedsample.customInvite.InviteCardData
import com.foresee.advancedsample.customInvite.ProductCardData
import androidx.recyclerview.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.foresee.advancedsample.customInvite.Adapter
import com.verint.xm.sdk.Predictive
import com.verint.xm.sdk.common.configuration.EligibleMeasureConfigurations
import com.verint.xm.sdk.predictive.tracker.listeners.CustomInSessionInviteListener


class ProductsActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "ProductsActivity"
    }

    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        this.recyclerView = findViewById(R.id.recyclerView)
        val gridLayoutManager = androidx.recyclerview.widget.GridLayoutManager(this@ProductsActivity, 2)
        this.recyclerView.layoutManager = gridLayoutManager

        reloadCards(hasInvite = false)

        setupCustomInvite()

        // action bar
        supportActionBar!!.title = "Custom Invite Sample"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        Predictive.checkIfEligibleForSurvey()
    }

    override fun onDestroy() {
        super.onDestroy()

        Predictive.setInviteListener(null)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupCustomInvite() {
        Predictive.setInviteListener(object : CustomInSessionInviteListener {
            override fun showInvite(eligibleMeasureConfigurations: EligibleMeasureConfigurations?) {
                Log.d(TAG, "showInvite")
                reloadCards(hasInvite = true)
            }

            override fun onSurveyPresented() {
                Log.d(TAG, "onSurveyPresented")
                reloadCards(hasInvite = false)
            }

            override fun onSurveyCancelledByUser() {
                Log.d(TAG, "onSurveyCancelledByUser")
            }

            override fun onSurveyCompleted() {
                Log.d(TAG, "onSurveyCompleted")
            }

            override fun onSurveyCancelledWithNetworkError() {
                Log.d(TAG, "onSurveyCancelledWithNetworkError")
                reloadCards(hasInvite = false)
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

    private fun reloadCards(hasInvite: Boolean = false) {
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

        if (hasInvite) {
            cards.add(3, InviteCardData("You are invited!", "Can we send you a brief survey?"))
        }

        this.recyclerView.adapter = Adapter(cards)
    }

    fun onInviteAcceptedClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        Predictive.customInviteAccepted()

    }
}
