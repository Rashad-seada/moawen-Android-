package com.example.marketapp.features.auth.data.entities.login

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)