package com.example.marketapp.features.profile.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class UpdatePhoneNumberRequest(
    @SerializedName("phone") val phone: String,
    @SerializedName("country_code") val countryCode: String,
)
