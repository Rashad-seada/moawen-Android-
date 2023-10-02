package com.example.marketapp.features.home.data.entities.home

data class Order(
    val delivery_image: String,
    val delivery_name: String,
    val user_image: String,
    val user_name: String,
    val user_wallet_balance: Int
)