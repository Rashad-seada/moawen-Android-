package com.example.marketapp.features.notification.domain.repo

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.notification.data.entities.get_all_notification.GetAllNotificationsResponse
import com.example.marketapp.features.notification.data.entities.get_notification.GetNotificationResponse
import com.example.marketapp.features.notification.data.entities.get_notifications_count.GetNotificationsCountResponse
import retrofit2.Response

interface NotificationRepo {

    suspend fun getAllNotifications(
        token: String,
        context: Context
    ): Resource<GetAllNotificationsResponse>


    suspend fun getNotification(
        id: Int,
        token: String,
        context: Context
    ): Resource<GetNotificationResponse>


    suspend fun getNotificationCount(
        token: String,
        context: Context
    ): Resource<GetNotificationsCountResponse>

}