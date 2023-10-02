package com.example.marketapp.features.auth.data.entities.send_code_to_phone

data class SendCodeToPhoneResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)