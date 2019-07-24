package com.rootstrap.donations.controllers

import com.rootstrap.donations.bus
import com.rootstrap.donations.models.Donation
import com.rootstrap.donations.repository.connection.ConnectionProvider
import com.rootstrap.donations.repository.services.ApiService
import com.rootstrap.donations.utils.ActionCallback
import retrofit2.Response

class DonationsController {

    open fun getDonations() {
        val apiService = ConnectionProvider.create(ApiService::class.java)
        val callBack = DonationsCallBack()
        apiService.getDonations().enqueue(callBack)
    }

    inner class DonationsCallBack : ActionCallback<ArrayList<Donation>>() {
        override fun responseAction(response: Response<ArrayList<Donation>>) {
            bus.post(AddDonationsEvent(response.body()))
        }
    }

    fun sendDonation(donation: Donation) {
        val service = ConnectionProvider.create(ApiService::class.java)
        val sendDonation = service.sendDonation(donation)
        sendDonation.enqueue(DonationCallback())
    }

    private inner class DonationCallback : ActionCallback<Donation>() {
        override fun responseAction(response: Response<Donation>) {
            super.responseAction(response)
            bus.post(DonationCallbackEvent(response.body()))
        }
    }

    class DonationCallbackEvent(val donation: Donation?)
    class AddDonationsEvent(var donations: ArrayList<Donation>? = ArrayList())
}
