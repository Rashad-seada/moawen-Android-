package com.example.marketapp.features.auth.data.repo

import android.content.Context
import android.util.Log
import com.example.marketapp.R
import com.example.marketapp.core.errors.*
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.data_source.remote.AuthRemoteDataSourceImpl
import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.entities.login.LoginResponse
import com.example.marketapp.features.auth.data.entities.login.User
import com.example.marketapp.features.auth.data.entities.register.RegisterResponse
import com.example.marketapp.features.auth.data.entities.resend_activition_code.ResendActivitionCodeResponse
import com.example.marketapp.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.marketapp.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.marketapp.features.auth.domain.repo.AuthRepo
import com.example.marketapp.features.auth.infrastructure.api.request.*
import com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref.UserInfoSharedPrefImpl
import org.json.JSONObject
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    val remoteDataSource: AuthRemoteDataSourceImpl,
    val sharedPref: UserInfoSharedPrefImpl,
    val networkService: NetworkServiceImpl,
) : AuthRepo {

    override suspend fun login(
        loginRequest: LoginRequest,
        context: Context,
    ): Resource<LoginResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val loginResponse = remoteDataSource.login(loginRequest)


            when {

                !loginResponse.isSuccessful -> {

                    val errorBody = loginResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
                            val message = jsonObjectError.getString("message")
                            errorMessage = message
                        } else {
                            errorMessage = context.getString(R.string.unknown_error)
                        }
                        return Resource.FailureData(
                            failure = ServiceFailure(
                                message = errorMessage,
                                screenId = 0,
                                customCode = 0,
                            )
                        )
                    }


                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = loginResponse.body()?.message ?: "",
                            screenId = 0,
                            customCode = 0,
                        )
                    )
                }

                loginResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 0,
                        )
                    )
                }
            }


            return Resource.SuccessData(
                data = loginResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun register(
        registerRequest: RegisterRequest,
        context: Context
    ): Resource<RegisterResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val registerResponse = remoteDataSource.register(
                registerRequest
            )

            Log.v("Register", "${registerResponse.raw().request.url}")


            when {

                !registerResponse.isSuccessful -> {


                    val errorBody = registerResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("fullname")) {
                                errorMessage = errorObj.getJSONArray("fullname").join(" and ")


                            } else if (errorObj.has("phone")) {
                                errorMessage = errorObj.getJSONArray("phone").join(" and ")

                            } else if (errorObj.has("password")) {
                                errorMessage = errorObj.getJSONArray("password").join(" and ")

                            } else if (errorObj.has("confirm_password")) {
                                errorMessage =
                                    errorObj.getJSONArray("confirm_password").join(" and ")


                            } else if (errorObj.has("terms")) {
                                errorMessage = errorObj.getJSONArray("terms").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }
                }

                registerResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = registerResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest,
        context: Context,
    ): Resource<LoginResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val confirmResponse = remoteDataSource.confirmCode(confirmCodeRequest)


            when {

                !confirmResponse.isSuccessful -> {

                    val errorBody = confirmResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {

                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("otp")) {
                                errorMessage = errorObj.getJSONArray("otp").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }

                }

                confirmResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = confirmResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivitionCodeRequest,
        context: Context
    ): Resource<ResendActivitionCodeResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val confirmResponse = remoteDataSource.resendActivitionCode(resendActivitionCodeRequest)


            when {

                !confirmResponse.isSuccessful -> {

                    val errorBody = confirmResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("phone")) {
                                errorMessage = errorObj.getJSONArray("phone").join(" and ")

                            } else if (errorObj.has("country_code")) {
                                errorMessage = errorObj.getJSONArray("country_code").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }

                }

                confirmResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = confirmResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }


    override suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest,
        context: Context,
    ): Resource<SendCodeToPhoneResponse>{
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val sendCodeToPhoneResponse = remoteDataSource.sendCodeToPhone(sendCodeToPhoneRequest)


            when {

                !sendCodeToPhoneResponse.isSuccessful -> {

                    val errorBody = sendCodeToPhoneResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("phone")) {
                                errorMessage = errorObj.getJSONArray("phone").join(" and ")

                            } else if (errorObj.has("country_code")) {
                                errorMessage = errorObj.getJSONArray("country_code").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }

                }

                sendCodeToPhoneResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 0,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = sendCodeToPhoneResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId  = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId  = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId  = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId  = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest,
        context: Context,
    ): Resource<CheckCodeSentResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val checkCodeSent = remoteDataSource.checkCodeSent(checkCodeSentRequest)


            when {

                !checkCodeSent.isSuccessful -> {
                    val errorBody = checkCodeSent.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("phone")) {
                                errorMessage = errorObj.getJSONArray("phone").join(" and ")

                            } else if (errorObj.has("country_code")) {
                                errorMessage = errorObj.getJSONArray("country_code").join(" and ")

                            } else if (errorObj.has("otp")) {
                                errorMessage = errorObj.getJSONArray("otp").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }
                }

                checkCodeSent.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 0,
                        )
                    )
                }


            }


            return Resource.SuccessData(
                data = checkCodeSent.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest,
        context: Context,
    ): Resource<ResetPasswordResponse> {
        try {

            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val resetPasswordResponse = remoteDataSource.resetPassword(resetPasswordRequest)


            when {

                !resetPasswordResponse.isSuccessful -> {
                    val errorBody = resetPasswordResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {

                        val errorJson = errorBody.string()
                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message")
                        } else {
                            errorMessage = context.getString(R.string.unknown_error)
                        }

                        return Resource.FailureData(
                            failure = ServiceFailure(
                                message = errorMessage,
                                screenId = 0,
                                customCode = 0,
                            )
                        )
                    }
                }

                resetPasswordResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 0,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = resetPasswordResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    context.getString(R.string.internet_connection),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override fun getUserInfo(
        context: Context,
        screenId: Int
    ): Resource<User> {
        try {

            val user = sharedPref.getUserInfo(context)
                ?: return Resource.FailureData(
                    failure = RemoteDataFailure(
                        message = context.getString(R.string.the_server_returned_null),
                        screenId = screenId,
                        customCode = 1,
                    )
                )

            return Resource.SuccessData(
                data = user,
            )

        } catch (e: Exception) {
            val failure = when (e) {

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

    override fun saveUserInfo(
        context: Context,
        user: User,
        screenId: Int
    ): Resource.FailureData<User> {
        try {

            val userInfo = sharedPref.saveUserInfo(context, user)

            return Resource.FailureData(
                failure = null
            )

        } catch (e: Exception) {
            val failure = when (e) {

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

    override fun deleteUserInfo(
        context: Context,
        screenId: Int
    ): Resource.FailureData<User> {
        try {

            val userInfo = sharedPref.deleteUserInfo(context)

            return Resource.FailureData(
                failure = null
            )

        } catch (e: Exception) {
            val failure = when (e) {

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
