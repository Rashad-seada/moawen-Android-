package com.example.marketapp.features.order.infrastructure.api.requests

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Part

data class MakeOrderStep1Request(
    @Part val imageParts: List<MultipartBody.Part>,
    @Part val recordParts: List<MultipartBody.Part>,
    @Part val fileParts: List<MultipartBody.Part>,
    @SerializedName("text") val text: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
)
