package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.CheckCodeSentRequest
import javax.inject.Inject


class CheckCodeSentUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        checkCodeSentRequest: CheckCodeSentRequest,
        context: Context
    ): Resource<CheckCodeSentResponse> {

        return repo.checkCodeSent(checkCodeSentRequest, context)

    }

}