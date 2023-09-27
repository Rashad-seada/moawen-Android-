package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.RegisterEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        username: String,
        email: String,
        phone: String,
        password: String,
        context: Context,
        screenId: Int
    ): Resource<RegisterEntity> {

        return repo.register(username,email,password,phone, context, screenId)

    }

}