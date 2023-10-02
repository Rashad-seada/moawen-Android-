package com.example.marketapp.features.auth.data.entities.check_code_sent

data class User(
    val country_code: String,
    val otp: Int,
    val phone: String
)