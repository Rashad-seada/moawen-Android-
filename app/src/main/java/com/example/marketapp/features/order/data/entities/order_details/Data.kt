package com.example.marketapp.features.order.data.entities.order_details

data class Data(
    val `file`: List<File>,
    val images: List<Image>,
    val orders: Orders,
    val records: List<Record>
)