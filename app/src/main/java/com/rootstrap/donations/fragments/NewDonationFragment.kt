package com.rootstrap.donations.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rootstrap.donations.R
import com.rootstrap.donations.activities.BaseActivity
import com.rootstrap.donations.controllers.DonationsController
import com.rootstrap.donations.models.Donation
import kotlinx.android.synthetic.main.fragment_new_donation.*

class NewDonationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_donation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_submit.setOnClickListener { sendDonation() }
    }

    private fun sendDonation() {
        if (title_edit_text.text?.isNotEmpty()!! && description_edit_text.text?.isNotEmpty()!!) {
            (activity as BaseActivity).showLoader()
            val donation = Donation(title = title_edit_text.text!!.toString(), description = description_edit_text.text!!.toString())
            DonationsController().sendDonation(donation)
        }
    }
}
