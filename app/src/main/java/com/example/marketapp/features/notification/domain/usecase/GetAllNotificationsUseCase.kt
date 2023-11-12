package com.example.marketapp.features.notification.domain.usecase

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.features.auth.data.entities.login.LoginResponse
import com.example.marketapp.features.auth.data.entities.login.User
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.LoginRequest
import com.example.marketapp.features.notification.data.entities.get_all_notification.GetAllNotificationsResponse
import com.example.marketapp.features.notification.domain.repo.NotificationRepo
import javax.inject.Inject


class GetAllNotificationsUseCase @Inject constructor(
    val repo: NotificationRepo,
    ) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<GetAllNotificationsResponse> {

        return repo.getAllNotifications(
            token = token,
            context = context,
        )


    }

}