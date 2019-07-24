package com.rootstrap.donations.models

import com.squareup.moshi.Json

data class Donation(
    @Json(name = "id") val id: Int = 1,
    @Json(name = "title") val title: String = "Title",
    @Json(name = "description") val description: String = "Description",
    @Json(name = "url") val url: String = "URL",
    @Json(name = "status") val status: String = "Donated",
    @Json(name = "delivererUserId") val delivererUserId: Int = 1,
    @Json(name = "user") val user: User,
    @Json(name = "userId") val userId: Int = 1)

data class User(
    @Json(name = "id") val id: Int = 1,
    @Json(name = "name") val name: String = "Name",
    @Json(name = "phone") val phone: String = "Phone",
    @Json(name = "password") val password: String = "")

data class Comment(
    @Json(name = "id") val id: Int = 1,
    @Json(name = "comment") val comment: String = "Comment",
    @Json(name = "userId") val userId: Int = 1,
    @Json(name = "user") val user: User,
    @Json(name = "donationId") val donationId: Int = 1)
