package com.rootstrap.donations.controllers

import com.rootstrap.ava.util.ActionCallback
import com.rootstrap.donations.bus
import com.rootstrap.donations.databinding.FragmentDonationsBinding
import com.rootstrap.donations.models.Donation
import com.rootstrap.donations.repository.connection.ConnectionProvider
import com.rootstrap.donations.repository.service.ApiService
import retrofit2.Response

class DonationsController(var repository: ConnectionProvider = ConnectionProvider()) {

    open fun getDonations() {
        val apiService = repository.create(ApiService::class.java)
        val callBack = DonationsCallBack()
        apiService.getDonations().enqueue(callBack)
    }

    inner class DonationsCallBack : ActionCallback<ArrayList<Donation>>() {
        override fun responseAction(response: Response<ArrayList<Donation>>) {
            bus.post(AddDonationsEvent(response.body()))
        }
    }
}

open class AddDonationsEvent(var donations: ArrayList<Donation>? = ArrayList())
