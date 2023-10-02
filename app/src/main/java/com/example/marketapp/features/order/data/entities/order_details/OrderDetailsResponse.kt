package com.example.marketapp.features.order.data.entities.order_details

data class OrderDetailsResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)