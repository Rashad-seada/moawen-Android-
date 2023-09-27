package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.ValidateSmsCodeEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class ValidateSmsUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        phone: String,
        smsCode: String,
        context: Context,
        screenId: Int
    ): Resource<ValidateSmsCodeEntity> {

        return repo.validateSmsCode(phone, smsCode, context, screenId)

    }

}