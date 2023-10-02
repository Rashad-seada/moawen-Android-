package com.example.marketapp.features.auth.domain.usecases

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.features.auth.data.entities.login.LoginResponse
import com.example.marketapp.features.auth.data.entities.login.User
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.LoginRequest
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    val repo: AuthRepoImpl,
    val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) {

    suspend operator fun invoke(
        loginRequest: LoginRequest,
        context: Context
    ): Resource<LoginResponse> {

        val result = repo.login(
            loginRequest = loginRequest,
            context = context,
        )

        if(result.failure == null) {

            val user = User(
                result.data!!.data.user.country_code,
                result.data!!.data.user.currency,
                result.data!!.data.user.flag,
                result.data!!.data.user.fullname,
                result.data!!.data.user.id,
                result.data!!.data.user.image,
                result.data!!.data.user.is_active,
                result.data!!.data.user.phone,
                result.data!!.data.user.token,
                )

            val saveResult = saveUserInfoUseCase(user,context,0)

            CoreViewModel.user = user


            return if(saveResult.failure == null) {
                result
            } else  {
                Resource.FailureData(saveResult.failure)
            }
        }

        return result


    }

}