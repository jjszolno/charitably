package com.rootstrap.donations.viewmodel.fragmentDonations

import androidx.recyclerview.widget.LinearLayoutManager
import com.rootstrap.donations.R
import com.rootstrap.donations.activities.BaseActivity
import com.rootstrap.donations.activities.DonationActivity
import com.rootstrap.donations.adapters.DonationAdapter
import com.rootstrap.donations.controllers.DonationsController
import com.rootstrap.donations.databinding.FragmentDonationsBinding
import com.rootstrap.donations.utils.FailureEvent
import com.rootstrap.donations.viewmodel.BaseViewModel
import com.squareup.otto.Subscribe

open class FragmentDonationsVM(private val fragmentDonationBinding: FragmentDonationsBinding,
                               private val baseActivity: BaseActivity,
                               var layoutManager: LinearLayoutManager? = LinearLayoutManager(baseActivity),
                               var donationsAdapter: DonationAdapter? = DonationAdapter(),
                               var donationsController: DonationsController? = DonationsController()) : BaseViewModel(), FragmentDonationsVMDeclaration {

    init {
        setupView()
        getDonations()
    }

    override fun reloadItems() {
        getDonations()
        fragmentDonationBinding.refreshDonations.isRefreshing = false
    }

    private fun setupView() {
        fragmentDonationBinding.donationList.layoutManager = layoutManager
        fragmentDonationBinding.donationList.adapter = donationsAdapter
        fragmentDonationBinding.refreshDonations.setOnRefreshListener { reloadItems() }
    }

    final override fun getDonations() {
        baseActivity.showLoader()
        donationsController!!.getDonations()
    }

    @Subscribe
    fun addDonations(event: DonationsController.AddDonationsEvent) {
        donationsAdapter!!.addItems(event.donations)
        baseActivity.dismissLoader()
    }

    @Subscribe
    fun reloadDonations(event: DonationActivity.ReloadDonations) { reloadItems() }

    @Subscribe
    fun error(event: FailureEvent) {
        baseActivity.dismissLoader()
        baseActivity.showError(baseActivity.getString(R.string.default_error))
    }

}