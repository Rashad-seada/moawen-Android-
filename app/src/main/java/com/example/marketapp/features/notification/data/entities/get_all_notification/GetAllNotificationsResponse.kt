package com.example.marketapp.features.notification.data.entities.get_all_notification

data class GetAllNotificationsResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)