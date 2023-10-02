package com.example.marketapp.features.auth.data.entities.resend_activition_code

data class User(
    val country_code: String,
    val otp: Int,
    val phone: String
)