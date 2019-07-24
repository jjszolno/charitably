package com.rootstrap.donations.controllers

import com.rootstrap.donations.models.User
import com.rootstrap.donations.repository.connection.ConnectionProvider
import com.rootstrap.donations.repository.services.ApiService
import com.rootstrap.donations.utils.ActionCallback
import retrofit2.Response

class UserController {

    fun getUser(id: Int) {
        val apiService = ConnectionProvider.create(ApiService::class.java)
        val userCallback = UserCallBack()
        apiService.getUser(id).enqueue(userCallback)
    }

    inner class UserCallBack : ActionCallback<User>() {
        override fun responseAction(response: Response<User>) {

        }
    }
}
