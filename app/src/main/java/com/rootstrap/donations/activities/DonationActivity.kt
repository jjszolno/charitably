package com.rootstrap.donations.activities

import android.os.Bundle
import com.rootstrap.donations.R
import com.rootstrap.donations.controllers.DonationsController
import com.rootstrap.donations.fragments.NewDonationFragment
import com.rootstrap.donations.utils.replace
import com.squareup.otto.Subscribe

class DonationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        replace(NewDonationFragment(), R.id.fragment_container)
    }

    @Subscribe
    fun onDonationSent(event: DonationsController.DonationCallbackEvent){
        dismissLoader()
        showError(getString(R.string.donation_sent))
        finish()
    }
}
