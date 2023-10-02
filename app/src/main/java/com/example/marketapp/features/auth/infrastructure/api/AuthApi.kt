package com.example.marketapp.features.auth.infrastructure.api

import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.entities.login.LoginResponse
import com.example.marketapp.features.auth.data.entities.register.RegisterResponse
import com.example.marketapp.features.auth.data.entities.resend_activition_code.ResendActivitionCodeResponse
import com.example.marketapp.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.marketapp.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.marketapp.features.auth.infrastructure.api.request.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("api/user-login")
    suspend fun login(
        @Body registerRequest: LoginRequest
    ): Response<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("api/user-register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>


    @Headers("Content-Type: application/json")
    @POST("api/confirm-code")
    suspend fun confirmCode(
        @Body confirmCodeRequest: ConfirmCodeRequest
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("api/resend-otp")
    suspend fun resendActivitionCode(
        @Body resendActivitionCodeRequest: ResendActivitionCodeRequest
    ): Response<ResendActivitionCodeResponse>

    @Headers("Content-Type: application/json")
    @POST("api/send-code")
    suspend fun sendSmsCode(
        @Body sendCodeToPhoneRequest: SendCodeToPhoneRequest
    ): Response<SendCodeToPhoneResponse>

    @Headers("Content-Type: application/json")
    @POST("api/check-code")
    suspend fun checkCodeSent(
        @Body checkCodeSentRequest: CheckCodeSentRequest
    ): Response<CheckCodeSentResponse>

    @Headers("Content-Type: application/json")
    @POST("api/reset-password")
    suspend fun resetPassword(
        @Body resetPasswordRequest: ResetPasswordRequest,
    ): Response<ResetPasswordResponse>



}