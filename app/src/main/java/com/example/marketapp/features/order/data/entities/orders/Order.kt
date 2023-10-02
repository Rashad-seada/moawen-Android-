package com.example.marketapp.features.order.data.entities.orders

data class Order(
    val created_at: String,
    val default_currency: DefaultCurrency,
    val drivaer_name: String,
    val driver_image: String,
    val id: Int,
    val lat: Double,
    val lng: Double,
    val order_number: String,
    val the_time_the_delivery_confirm_order: Any,
    val total: Any
)