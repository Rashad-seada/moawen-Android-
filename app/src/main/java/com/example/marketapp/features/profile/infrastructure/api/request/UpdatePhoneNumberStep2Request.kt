package com.example.marketapp.features.profile.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class UpdatePhoneNumberStep2Request(
    @SerializedName("phone") val phone: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("otp") val otp: String,
)
