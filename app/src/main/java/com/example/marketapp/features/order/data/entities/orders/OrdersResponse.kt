package com.example.marketapp.features.order.data.entities.orders

data class OrdersResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)