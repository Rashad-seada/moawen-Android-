package com.example.marketapp.features.notification.data.entities.get_all_notification

data class Notification(
    val created_at: String,
    val `data`: String,
    val id: Int,
    val is_read: Int,
    val read_at: String?,
    val title: String,
    val updated_at: String,
    val user_id: Int
)