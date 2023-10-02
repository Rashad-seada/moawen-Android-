package com.example.marketapp.features.auth.data.entities.check_code_sent

data class CheckCodeSentResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)