package com.example.marketapp.features.order.data.entities.cancel_order

data class DefaultCurrency(
    val created_at: String,
    val default: Int,
    val id: Int,
    val isocode: String,
    val name: String,
    val translations: List<Translation>,
    val updated_at: String
)