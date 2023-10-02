package com.example.marketapp.features.auth.data.entities.send_code_to_phone

data class User(
    val country_code: String,
    val otp: Int,
    val phone: String
)