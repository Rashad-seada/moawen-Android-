package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.ActivateAccountEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class ActivateAccountUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        phone: String,
        pin: String,
        expectedPin: String,
        context: Context,
        screenId: Int
    ): Resource<ActivateAccountEntity> {

        return repo.activateAccount(phone,pin, expectedPin,context,screenId )

    }

}