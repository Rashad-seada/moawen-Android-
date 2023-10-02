package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.ResetPasswordRequest
import javax.inject.Inject


class ResetPasswordUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        resetPasswordRequest: ResetPasswordRequest,
        context: Context,
    ): Resource<ResetPasswordResponse> {

        return repo.resetPassword(resetPasswordRequest, context)

    }

}