package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.SendCodeToPhoneRequest
import javax.inject.Inject


class SendCodeToPhoneUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest,
        context: Context
    ): Resource<SendCodeToPhoneResponse> {

        return repo.sendCodeToPhone(sendCodeToPhoneRequest, context)

    }

}