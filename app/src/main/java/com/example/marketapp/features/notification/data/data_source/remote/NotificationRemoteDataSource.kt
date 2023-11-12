package com.example.marketapp.features.notification.data.data_source.remote

import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.features.notification.data.entities.get_all_notification.GetAllNotificationsResponse
import com.example.marketapp.features.notification.data.entities.get_notification.GetNotificationResponse
import com.example.marketapp.features.notification.data.entities.get_notifications_count.GetNotificationsCountResponse
import com.example.marketapp.features.notification.infrastructure.api.NotificationApi
import retrofit2.Response
import javax.inject.Inject

interface NotificationRemoteDataSource {

    suspend fun getAllNotifications(
        token: String
    ): Response<GetAllNotificationsResponse>


    suspend fun getNotification(
        id: Int,
        token: String
    ): Response<GetNotificationResponse>


    suspend fun getNotificationCount(
        token: String
    ): Response<GetNotificationsCountResponse>

}

class NotificationRemoteDataSourceImpl @Inject constructor(
    private val api: NotificationApi
) : NotificationRemoteDataSource {


    override suspend fun getAllNotifications(token: String): Response<GetAllNotificationsResponse> {
        try {
            return api.getAllNotifications(
                token = token
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun getNotification(
        id: Int,
        token: String
    ): Response<GetNotificationResponse> {
        try {
            return api.getNotification(
                id = id,
                token = token
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun getNotificationCount(token: String): Response<GetNotificationsCountResponse> {
        try {
            return api.getNotificationCount(
                token = token
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }


}

