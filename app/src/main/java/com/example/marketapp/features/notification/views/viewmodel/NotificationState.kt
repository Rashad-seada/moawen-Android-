package com.example.marketapp.features.notification.views.viewmodel

import com.example.marketapp.features.notification.data.entities.get_all_notification.Notification

data class NotificationState(

    var notifications: List<Notification> = emptyList(),
    var notificationsError: String? = null,
    var notificationsIsLoading: Boolean = false,

    )
