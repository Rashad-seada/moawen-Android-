package com.example.marketapp.features.notification.data.entities.get_notification

data class GetNotificationResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)