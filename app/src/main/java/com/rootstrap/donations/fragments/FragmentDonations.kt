package com.rootstrap.donations.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rootstrap.donations.R
import com.rootstrap.donations.activities.BaseActivity
import com.rootstrap.donations.adapters.DonationAdapter
import com.rootstrap.donations.bus
import com.rootstrap.donations.controllers.DonationsController
import com.rootstrap.donations.databinding.FragmentDonationsBinding
import com.squareup.otto.Subscribe

class FragmentDonations(
    var fragmentDonationBinding: FragmentDonationsBinding? = null,
    var donationsController: DonationsController? = DonationsController()) : Fragment() {

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
    }

    @Subscribe
    fun addDonations(event: DonationsController.AddDonationsEvent) {
        (fragmentDonationBinding!!.donationList.adapter as DonationAdapter).addItems(event.donations)
        (activity as BaseActivity).dismissLoader()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as BaseActivity).dismissLoader()
    }

    override fun onResume() {
        super.onResume()
        bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        bus.unregister(this)
    }
}
