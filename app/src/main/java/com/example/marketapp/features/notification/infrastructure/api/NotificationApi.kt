package com.example.marketapp.features.notification.infrastructure.api

import com.example.marketapp.features.notification.data.entities.get_all_notification.GetAllNotificationsResponse
import com.example.marketapp.features.notification.data.entities.get_notification.GetNotificationResponse
import com.example.marketapp.features.notification.data.entities.get_notifications_count.GetNotificationsCountResponse
import retrofit2.Response
import retrofit2.http.*

interface NotificationApi {


    @Headers("Content-Type: application/json")
    @GET("api/notifications")
    suspend fun getAllNotifications(
        @Header("Authorization") token : String
    ): Response<GetAllNotificationsResponse>


    @Headers("Content-Type: application/json")
    @POST("api/showNotifications/{id}")
    suspend fun getNotification(
        @Path("id") id : Int,
        @Header("Authorization") token : String
    ): Response<GetNotificationResponse>


    @Headers("Content-Type: application/json")
    @POST("api/count_notifications")
    suspend fun getNotificationCount(
        @Header("Authorization") token : String
    ): Response<GetNotificationsCountResponse>

}