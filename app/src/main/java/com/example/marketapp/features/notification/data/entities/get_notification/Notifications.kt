package com.example.marketapp.features.notification.data.entities.get_notification

data class Notifications(
    val created_at: String,
    val `data`: String,
    val id: Int,
    val is_read: Int,
    val read_at: Any,
    val title: String,
    val updated_at: String,
    val user_id: Int
)