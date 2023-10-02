package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.login.User
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject

class DeleteUserInfoUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    operator fun invoke(
        context: Context,
        screenId: Int
    ): Resource.FailureData<User> {

        return repo.deleteUserInfo(
            context, screenId
        )

    }
}
