package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.viewmodel.CoreViewModel.Companion.userInfo
import com.example.marketapp.features.auth.data.entities.LoginEntity
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref.UserInfo
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    val repo: AuthRepoImpl,
    val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) {

    suspend operator fun invoke(
        email: String,
        password: String,
        context: Context,
        screenId: Int
    ): Resource<LoginEntity> {

        val result = repo.login(
            email = email,
            password = password,
            context = context,
            screenId = screenId,
        )

        if(result.failure == null) {
            val userInfo = UserInfo(result.data!!.id.toInt(),password)
            CoreViewModel.userInfo = userInfo

            val saveResult = saveUserInfoUseCase(UserInfo(result.data.id.toInt(),password),context,screenId)

            return if(saveResult.failure == null) {
                result
            } else  {
                Resource.FailureData(saveResult.failure)
            }
        }

        return result


    }

}