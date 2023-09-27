package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.ResetPasswordByEmailEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class ResetPasswordByEmailUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        email: String,
        context: Context,
        screenId: Int
    ): Resource<ResetPasswordByEmailEntity> {

        return repo.resetPasswordByEmail(email, context, screenId)

    }

}