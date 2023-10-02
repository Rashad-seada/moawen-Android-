package com.example.marketapp.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class ConfirmCodeRequest(
    @SerializedName("phone") val phone: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("otp") val otp: String,
    @SerializedName("type") val type: String,
    @SerializedName("device_token") val deviceToken: String,
)
