package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.Api.LoginResposne
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        context: Context,
        screenId: Int
    ): LoginResposne {

        return repo.login(
            email = email,
            password = password,
            context = context,
            screenId = screenId,
        )

    }

}