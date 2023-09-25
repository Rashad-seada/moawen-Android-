package com.example.marketapp.features.auth.data.repo

import android.content.Context
import com.example.marketapp.R
import com.example.marketapp.core.errors.InternalFailure
import com.example.marketapp.core.errors.LocalDataException
import com.example.marketapp.core.errors.LocalDataFailure
import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.core.errors.RemoteDataFailure
import com.example.marketapp.core.errors.ServiceException
import com.example.marketapp.core.errors.ServiceFailure
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.features.auth.data.data_source.remote.AuthRemoteDataSourceImpl
import com.example.marketapp.features.auth.domain.repo.AuthRepo
import com.example.marketapp.features.auth.infrastructure.Api.LoginResposne
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    val remoteDataSource: AuthRemoteDataSourceImpl,
    val networkService: NetworkServiceImpl,
) : AuthRepo {

    override suspend fun login(
        email: String,
        password: String,
        context: Context,
        screenId: Int
    ): LoginResposne {
        try {

            if (!networkService.isNetworkConnected(context)) {
                return LoginResposne(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.login(username = email, password = password)

            if (loginEntity.res.toInt() != 1) {
                return LoginResposne(
                    failure = RemoteDataFailure(
                        message = loginEntity.msg,
                        screenId = screenId,
                        customCode = 2,
                    )
                )
            }

            return LoginResposne(
                loginEntity = loginEntity
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(e.message.toString(), screenId, customCode = 0)
            }

            return LoginResposne(
                failure = failure
            )

        }
    }


}