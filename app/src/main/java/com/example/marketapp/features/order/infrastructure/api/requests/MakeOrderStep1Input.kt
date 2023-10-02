package com.example.marketapp.features.order.infrastructure.api.requests

import android.net.Uri
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Part
import java.io.File

data class MakeOrderStep1Input(
    @Part("images[]") val imageParts: List<Uri>,
    @Part("records[]") val recordParts: List<File>,
    @Part("files[]") val fileParts: List<Uri>,
    @SerializedName("text") val text: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
)
