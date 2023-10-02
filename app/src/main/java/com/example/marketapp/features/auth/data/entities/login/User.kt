package com.example.marketapp.features.auth.data.entities.login

data class User(
    val country_code: String,
    val currency: String,
    val flag: String,
    val fullname: String,
    val id: Int,
    val image: String,
    val is_active: Boolean,
    val phone: String,
    val token: String,
)