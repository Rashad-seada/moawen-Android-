package com.example.marketapp.features.order.data.entities.cancel_order

data class CancelOrderResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)