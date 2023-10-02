package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.resend_activition_code.ResendActivitionCodeResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.ResendActivitionCodeRequest
import javax.inject.Inject


class ResendActivitionCodeUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        confirmCodeRequest: ResendActivitionCodeRequest,
        context: Context
    ): Resource<ResendActivitionCodeResponse> {
        return repo.resendActivitionCode(
            confirmCodeRequest,
            context
        )
    }

}