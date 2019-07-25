package com.rootstrap.donations.fragments

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rootstrap.donations.R
import com.rootstrap.donations.activities.BaseActivity
import com.rootstrap.donations.activities.DonationActivity
import com.rootstrap.donations.activities.MainActivity
import com.rootstrap.donations.adapters.DonationAdapter
import com.rootstrap.donations.bus
import com.rootstrap.donations.controllers.DonationsController
import com.rootstrap.donations.databinding.FragmentDonationsBinding
import com.rootstrap.donations.utils.FailureEvent
import com.squareup.otto.Subscribe

class FragmentDonations(
    var fragmentDonationBinding: FragmentDonationsBinding? = null,
    var donationsController: DonationsController? = DonationsController()) : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_donations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDonationBinding = DataBindingUtil.bind<FragmentDonationsBinding>(view)
        setupView()
        getDonations()
    }

    private fun getDonations() {
        (activity as BaseActivity).showLoader()
        donationsController!!.getDonations()
    }

    private fun setupView() {
        fragmentDonationBinding!!.donationList.layoutManager = LinearLayoutManager(context)
        fragmentDonationBinding!!.donationList.adapter = DonationAdapter()
        fragmentDonationBinding!!.refreshDonations.setOnRefreshListener {
            getDonations()
            fragmentDonationBinding!!.refreshDonations.isRefreshing = false
        }
    }

    @Subscribe
    fun addDonations(event: DonationsController.AddDonationsEvent) {
        (fragmentDonationBinding!!.donationList.adapter as DonationAdapter).addItems(event.donations)
        (activity as BaseActivity).dismissLoader()
    }

    @Subscribe
    fun reloadDonations(event: DonationActivity.ReloadDonations) { getDonations() }

    @Subscribe
    fun error(event: FailureEvent) {
        (activity as BaseActivity).dismissLoader()
        (activity as BaseActivity).showError(getString(R.string.default_error))
    }
}
