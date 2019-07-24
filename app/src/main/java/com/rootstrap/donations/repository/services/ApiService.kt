package com.rootstrap.donations.repository.services

import com.rootstrap.donations.models.Donation
import com.rootstrap.donations.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("donations?_expand=user")
    fun getDonations() : Call<ArrayList<Donation>>

    @GET("donations/{id}")
    fun getDonation(@Path("id") id: Int): Call<Donation>

    @GET("users")
    fun getUsers(): Call<ArrayList<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @PUT("donations")
    fun sendDonation(@Body donation: Donation): Call<Donation>
}
