package com.example.marketapp.features.home.data.entities.home

data class HomeResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)