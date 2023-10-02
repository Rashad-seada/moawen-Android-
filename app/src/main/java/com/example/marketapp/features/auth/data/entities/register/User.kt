package com.example.marketapp.features.auth.data.entities.register

data class User(
    val country_code: String,
    val id: Int,
    val otp: Int,
    val phone: String
)