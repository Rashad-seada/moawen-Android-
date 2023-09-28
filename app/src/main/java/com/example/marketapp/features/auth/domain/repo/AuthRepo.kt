package com.example.marketapp.features.auth.domain.repo

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.ActivateAccountEntity
import com.example.marketapp.features.auth.data.entities.LoginEntity
import com.example.marketapp.features.auth.data.entities.RegisterEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByEmailEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByPhoneEntity
import com.example.marketapp.features.auth.data.entities.SendSmsCodeEntity
import com.example.marketapp.features.auth.data.entities.ValidateEmailEntity
import com.example.marketapp.features.auth.data.entities.ValidatePhoneEntity
import com.example.marketapp.features.auth.data.entities.ValidateSmsCodeEntity
import com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref.UserInfo

interface AuthRepo {
    suspend fun login(
        email: String,
        password: String,
        context: Context,
        screenId: Int
    ): Resource<LoginEntity>

    suspend fun register(
        username: String,
        email: String,
        password: String,
        phone: String,
        context: Context,
        screenId: Int
    ): Resource<RegisterEntity>

    suspend fun activateAccount(
        phone: String,
        pin: String,
        expectedPin: String,
        context: Context,
        screenId: Int
    ): Resource<ActivateAccountEntity>

    suspend fun resetPasswordByEmail(
        email: String,
        context: Context,
        screenId: Int
    ): Resource<ResetPasswordByEmailEntity>

    suspend fun sendSmsCode(
        phone: String,
        context: Context,
        screenId: Int
    ): Resource<SendSmsCodeEntity>

    suspend fun validateSmsCode(
        phone: String,
        smsCode: String,
        context: Context,
        screenId: Int
    ): Resource<ValidateSmsCodeEntity>

    suspend fun resetPasswordByPhone(
        phone: String,
        smsCode: String,
        newPassword: String,
        context: Context,
        screenId: Int
    ): Resource<ResetPasswordByPhoneEntity>

    suspend fun validateEmail(
        email: String,
        context: Context,
        screenId: Int
    ): Resource<ValidateEmailEntity>

    suspend fun validatePhone(
        phone: String,
        context: Context,
        screenId: Int
    ): Resource<ValidatePhoneEntity>


    fun getUserInfo(
        context: Context,
        screenId: Int

    ) : Resource<UserInfo>

    fun saveUserInfo(
        context: Context,
        userInfo: UserInfo,
        screenId: Int
    ) : Resource.FailureData<UserInfo>?

    fun deleteUserInfo(
        context: Context,
        screenId: Int
    ) : Resource.FailureData<UserInfo>?

}