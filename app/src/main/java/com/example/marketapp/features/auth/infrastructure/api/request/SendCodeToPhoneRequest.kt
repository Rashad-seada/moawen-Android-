package com.example.marketapp.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class SendCodeToPhoneRequest (
    @SerializedName("phone") val phone: String,
    @SerializedName("country_code") val countryCode: String,
)
