package com.example.marketapp.features.auth.domain.repo

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.entities.login.LoginResponse
import com.example.marketapp.features.auth.data.entities.login.User
import com.example.marketapp.features.auth.data.entities.register.RegisterResponse
import com.example.marketapp.features.auth.data.entities.resend_activition_code.ResendActivitionCodeResponse
import com.example.marketapp.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.marketapp.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.marketapp.features.auth.infrastructure.api.request.*

interface AuthRepo {
    suspend fun login(
        loginRequest: LoginRequest,
        context: Context,
    ): Resource<LoginResponse>

    suspend fun register(
        registerRequest: RegisterRequest,
        context: Context
    ): Resource<RegisterResponse>

    suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest,
        context: Context
    ): Resource<LoginResponse>

    suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivitionCodeRequest,
        context: Context
    ): Resource<ResendActivitionCodeResponse>

    suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest,
        context: Context,
    ): Resource<SendCodeToPhoneResponse>

    suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest,
        context: Context,
    ): Resource<CheckCodeSentResponse>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest,
        context: Context,
    ): Resource<ResetPasswordResponse>

    fun getUserInfo(
        context: Context,
        screenId: Int

    ) : Resource<User>

    fun saveUserInfo(
        context: Context,
        user: User,
        screenId: Int
    ): Resource.FailureData<User>

    fun deleteUserInfo(
        context: Context,
        screenId: Int
    ) : Resource.FailureData<User>?

}