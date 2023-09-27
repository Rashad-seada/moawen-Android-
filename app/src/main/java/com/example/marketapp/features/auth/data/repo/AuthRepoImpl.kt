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
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.data_source.remote.AuthRemoteDataSourceImpl
import com.example.marketapp.features.auth.data.entities.ActivateAccountEntity
import com.example.marketapp.features.auth.data.entities.LoginEntity
import com.example.marketapp.features.auth.data.entities.RegisterEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByEmailEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByPhoneEntity
import com.example.marketapp.features.auth.data.entities.SendSmsCodeEntity
import com.example.marketapp.features.auth.data.entities.ValidateEmailEntity
import com.example.marketapp.features.auth.data.entities.ValidatePhoneEntity
import com.example.marketapp.features.auth.data.entities.ValidateSmsCodeEntity
import com.example.marketapp.features.auth.domain.repo.AuthRepo
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
    ): Resource<LoginEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.login(username = email, password = password)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() != 1 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        phone : String,
        context: Context,
        screenId: Int
    ): Resource<RegisterEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.register(
                username, email, password, phone
            )


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun activateAccount(
        phone: String,
        pin: String,
        expectedPin: String,
        context: Context,
        screenId: Int
    ): Resource<ActivateAccountEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.activateAccount(phone, pin, expectedPin)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun resetPasswordByEmail(
        email: String,
        context: Context,
        screenId: Int
    ): Resource<ResetPasswordByEmailEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.resetPasswordByEmail(email)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun sendSmsCode(
        phone: String,
        context: Context,
        screenId: Int
    ): Resource<SendSmsCodeEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.sendSmsCode(phone)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun validateSmsCode(
        phone: String,
        smsCode: String,
        context: Context,
        screenId: Int
    ): Resource<ValidateSmsCodeEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.validateSmsCode(phone, smsCode)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun resetPasswordByPhone(
        phone: String,
        smsCode: String,
        newPassword: String,
        context: Context,
        screenId: Int
    ): Resource<ResetPasswordByPhoneEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.resetPasswordByPhone(phone, smsCode, newPassword)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun validateEmail(
        email: String,
        context: Context,
        screenId: Int
    ): Resource<ValidateEmailEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.validateEmail(email)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun validatePhone(
        phone: String,
        context: Context,
        screenId: Int
    ): Resource<ValidatePhoneEntity> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = screenId,
                        customCode = 1,
                    )
                )
            }

            val loginEntity = remoteDataSource.validatePhone(phone)


            when {

                !loginEntity.isSuccessful -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.server_is_down),
                            screenId = screenId,
                            customCode = 0,
                        )
                    )
                }

                loginEntity.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = screenId,
                            customCode = 1,
                        )
                    )
                }

                loginEntity.body()!!.res.toInt() <= 0 -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginEntity.body()!!.msg,
                            screenId = screenId,
                            customCode = 2,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginEntity.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId, customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }


}
