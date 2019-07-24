package com.rootstrap.donations.controllers

import com.rootstrap.ava.util.ActionCallback
import com.rootstrap.donations.models.User
import com.rootstrap.donations.repository.connection.ConnectionProvider
import com.rootstrap.donations.repository.service.ApiService
import retrofit2.Response

class UserController(var repository: ConnectionProvider = ConnectionProvider()) {

    open fun getUser(id: Int) {
        val apiService = repository.create(ApiService::class.java)
        val userCallback = UserCallBack()
        apiService.getUser(id).enqueue(userCallback)
    }

    inner class UserCallBack : ActionCallback<User>() {
        override fun responseAction(response: Response<User>) {

        }
    }
}
