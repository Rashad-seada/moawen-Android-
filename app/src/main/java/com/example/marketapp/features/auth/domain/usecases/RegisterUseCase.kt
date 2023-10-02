package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.register.RegisterResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.RegisterRequest
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        registerRequest: RegisterRequest,
        context: Context
    ): Resource<RegisterResponse> {

        return repo.register(
            registerRequest,
            context
        )

    }

}