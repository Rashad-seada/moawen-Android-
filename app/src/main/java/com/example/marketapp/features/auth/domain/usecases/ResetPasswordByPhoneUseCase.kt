package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.ResetPasswordByPhoneEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class ResetPasswordByPhoneUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        phone: String,
        smsCode: String,
        newPassword: String,
        context: Context,
        screenId: Int
    ): Resource<ResetPasswordByPhoneEntity> {

        return repo.resetPasswordByPhone(phone, smsCode, newPassword, context, screenId)

    }

}