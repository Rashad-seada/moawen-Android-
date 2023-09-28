package com.example.marketapp.features.auth.infrastructure.api

import com.example.marketapp.features.auth.data.entities.ActivateAccountEntity
import com.example.marketapp.features.auth.data.entities.LoginEntity
import com.example.marketapp.features.auth.data.entities.RegisterEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByEmailEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByPhoneEntity
import com.example.marketapp.features.auth.data.entities.SendSmsCodeEntity
import com.example.marketapp.features.auth.data.entities.ValidateEmailEntity
import com.example.marketapp.features.auth.data.entities.ValidatePhoneEntity
import com.example.marketapp.features.auth.data.entities.ValidateSmsCodeEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AuthApi {

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun login(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<LoginEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun register(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<RegisterEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun activateAccount(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<ActivateAccountEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun resetPasswordByEmail(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<ResetPasswordByEmailEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun sendSmsCode(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<SendSmsCodeEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun validateSmsCode(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<ValidateSmsCodeEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun resetPasswordByPhone(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<ResetPasswordByPhoneEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun validateEmail(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<ValidateEmailEntity>

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun validatePhone(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<ValidatePhoneEntity>



//    @GET("/products/{id}")
//    suspend fun getProducts(@Query("id") id : Int) : ProductsResponse

}