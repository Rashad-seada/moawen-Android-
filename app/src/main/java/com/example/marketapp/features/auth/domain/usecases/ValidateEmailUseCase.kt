package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.ValidateEmailEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class ValidateEmailUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        email: String,
        context: Context,
        screenId: Int
    ): Resource<ValidateEmailEntity> {

        return repo.validateEmail(email, context, screenId)

    }

}