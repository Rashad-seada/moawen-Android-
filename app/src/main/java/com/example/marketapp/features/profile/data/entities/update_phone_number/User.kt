package com.example.marketapp.features.profile.data.entities.update_phone_number

data class User(
    val country_code: String,
    val id: Int,
    val otp: Int,
    val phone: String
)