package com.rootstrap.donations.controllers

import com.rootstrap.donations.bus
import com.rootstrap.donations.models.User
import com.rootstrap.donations.repository.connection.ConnectionProvider
import com.rootstrap.donations.repository.services.ApiService
import com.rootstrap.donations.utils.ActionCallback
import com.rootstrap.donations.utils.FailureEvent
import retrofit2.Response

class UserController(val apiService: ApiService = ConnectionProvider.create(ApiService::class.java)) {

    fun logIn(phone: String, password: String) {
        val logInCallBack = LogInCallBack()
        apiService.logIn(phone = phone, password = password).enqueue(logInCallBack)
    }

    fun signUp(name: String, phone: String, password: String) {
        val logInCallBack = CheckUserCallBack(name, phone, password)
        apiService.logIn(phone = phone).enqueue(logInCallBack)
    }

    fun completeSignUp(name: String, phone: String, password: String) {
        val signUpCallBack = SignUpCallBack()
        apiService.signUp(User(phone = phone, name = name, password = password)).enqueue(signUpCallBack)
    }

    inner class LogInCallBack : ActionCallback<ArrayList<User>>() {

        override fun responseAction(response: Response<ArrayList<User>>) {
            if (response.body()!=null && response.body()!!.size > 0) {
                bus.post(SuccesfulSignUp(response.body()!![0]))
            } else {
                bus!!.post(FailureEvent())
            }
        }
    }

    inner class CheckUserCallBack(var name: String,var phone: String,var password: String) : ActionCallback<ArrayList<User>>() {
        override fun responseAction(response: Response<ArrayList<User>>) {
            if (response.body()!=null && response.body()!!.size > 0) {
                bus!!.post(UserAlreadyExist())
            } else {
                bus!!.post(SignUp(phone = phone, name = name, password = password))
            }
        }
    }

    inner class SignUpCallBack : ActionCallback<User>() {
        override fun responseAction(response: Response<User>) {
            bus.post(SuccesfulSignUp(response.body()))
        }
    }

    class SuccesfulSignUp(var user: User? = null)
    class UserAlreadyExist
    class SignUp(var name: String,var phone: String,var password: String)
}
