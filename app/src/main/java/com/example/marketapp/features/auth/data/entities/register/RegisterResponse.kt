package com.example.marketapp.features.auth.data.entities.register

data class RegisterResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)