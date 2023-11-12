package com.example.marketapp.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone") val phone: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("password") val password: String,
    @SerializedName("device_token") val deviceToken: String,
    @SerializedName("type") val deviceType: String,
)
