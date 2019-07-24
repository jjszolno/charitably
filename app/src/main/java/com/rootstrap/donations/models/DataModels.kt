package com.rootstrap.donations.models

import com.squareup.moshi.Json

data class Donation(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "title") val title: String = "Title",
    @Json(name = "description") val description: String = "Description",
    @Json(name = "url") val url: String = "URL",
    @Json(name = "status") val status: String = "Donated",
    @Json(name = "delivererUserId") val delivererUserId: Int = 1,
    @Json(name = "user") val user: User? = null,
    @Json(name = "userId") val userId: Int? = 1)//todo change for real user

data class User(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String = "Name",
    @Json(name = "phone") val phone: String = "Phone",
    @Json(name = "password") val password: String = "")

data class Comment(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "comment") val comment: String = "Comment",
    @Json(name = "userId") val userId: Int? = null,
    @Json(name = "user") val user: User? = null,
    @Json(name = "donationId") val donationId: Int? = null)
