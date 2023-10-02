package com.example.marketapp.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("phone") val phone: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("otp") val otp: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirm_password") val confirmPassword: String,
)
