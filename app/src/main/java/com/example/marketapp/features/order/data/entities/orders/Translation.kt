package com.example.marketapp.features.order.data.entities.orders

data class Translation(
    val currency_id: Int,
    val id: Int,
    val locale: String,
    val name: String
)