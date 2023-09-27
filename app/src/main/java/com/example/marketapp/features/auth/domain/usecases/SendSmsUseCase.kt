package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.SendSmsCodeEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class SendSmsUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        phone: String,
        context: Context,
        screenId: Int
    ): Resource<SendSmsCodeEntity> {

        return repo.sendSmsCode(phone, context, screenId)

    }

}