package com.example.marketapp.features.auth.data.data_source.remote

import com.example.marketapp.R
import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.core.util.Consts.DEFAULT_EMAIL
import com.example.marketapp.core.util.Consts.DEFAULT_PASSWORD
import com.example.marketapp.core.util.Consts.DEFAULT_USER_ID
import com.example.marketapp.core.util.base64_converters.toBase64
import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.entities.login.LoginResponse
import com.example.marketapp.features.auth.data.entities.register.RegisterResponse
import com.example.marketapp.features.auth.data.entities.resend_activition_code.ResendActivitionCodeResponse
import com.example.marketapp.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.marketapp.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.marketapp.features.auth.infrastructure.api.AuthApi
import com.example.marketapp.features.auth.infrastructure.api.request.*
import retrofit2.Response
import javax.inject.Inject

interface AuthRemoteDataSource {
    suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse>

    suspend fun register(
        registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest
    ): Response<LoginResponse>

    suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivitionCodeRequest
    ): Response<ResendActivitionCodeResponse>

    suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest
    ): Response<SendCodeToPhoneResponse>

    suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest
    ): Response<CheckCodeSentResponse>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Response<ResetPasswordResponse>


}

class AuthRemoteDataSourceImpl @Inject constructor(val api: AuthApi) : AuthRemoteDataSource {

    companion object {
        val DATA_SOURCE_ID = 0
    }

    override suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse> {

        try {

            return api.login(
                loginRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }

    }

    override suspend fun register(
        registerRequest: RegisterRequest
    ): Response<RegisterResponse> {

        try {

            return api.register(
                registerRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest
    ): Response<LoginResponse> {
        try {
            return api.confirmCode(
                confirmCodeRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }
    }

    override suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivitionCodeRequest
    ): Response<ResendActivitionCodeResponse> {
        try {
            return api.resendActivitionCode(
                resendActivitionCodeRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }
    }


    override suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest
    ): Response<SendCodeToPhoneResponse>{
        try {
            return api.sendSmsCode(
                sendCodeToPhoneRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest
    ): Response<CheckCodeSentResponse> {
        try {

            return api.checkCodeSent(
                checkCodeSentRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Response<ResetPasswordResponse> {
        try {

            return api.resetPassword(
                resetPasswordRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }
    }

}