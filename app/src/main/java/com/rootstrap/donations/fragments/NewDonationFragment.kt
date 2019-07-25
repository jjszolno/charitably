package com.rootstrap.donations.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.rootstrap.donations.R
import com.rootstrap.donations.activities.BaseActivity
import com.rootstrap.donations.controllers.DonationsController
import com.rootstrap.donations.models.Donation
import com.rootstrap.donations.utils.FailureEvent
import com.squareup.otto.Subscribe
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
            val selected = (type_radio.getChildAt(type_radio.checkedRadioButtonId)) as RadioButton
            val donation = Donation(
                    title = title_edit_text.text!!.toString(),
                    description = description_edit_text.text!!.toString(),
                    type = selected.text.toString())
            DonationsController().sendDonation(donation)
        }
    }

    @Subscribe
    fun error(event: FailureEvent) {
        (activity as BaseActivity).dismissLoader()
        (activity as BaseActivity).showError(getString(R.string.default_error))
    }

}
