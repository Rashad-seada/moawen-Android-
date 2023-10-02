package com.example.marketapp.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("phone") val phone: String,
    @SerializedName("fullname") val fullName: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirm_password") val confirmPassword: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("provider") val provider: String,
    @SerializedName("provider_id") val providerId: String,
    @SerializedName("terms") val terms: String,
)
