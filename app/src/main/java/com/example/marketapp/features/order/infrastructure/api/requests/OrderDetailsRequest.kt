package com.example.marketapp.features.order.infrastructure.api.requests

import com.google.gson.annotations.SerializedName

data class OrderDetailsRequest(
    @SerializedName("order_id") val orderId: Int,
)
