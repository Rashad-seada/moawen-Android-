package com.example.marketapp.features.auth.data.data_source.remote

import com.example.marketapp.R
import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.core.util.Consts.DEFAULT_EMAIL
import com.example.marketapp.core.util.Consts.DEFAULT_PASSWORD
import com.example.marketapp.core.util.Consts.DEFAULT_USER_ID
import com.example.marketapp.core.util.base64_converters.toBase64
import com.example.marketapp.features.auth.data.entities.ActivateAccountEntity
import com.example.marketapp.features.auth.data.entities.LoginEntity
import com.example.marketapp.features.auth.data.entities.RegisterEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByEmailEntity
import com.example.marketapp.features.auth.data.entities.ResetPasswordByPhoneEntity
import com.example.marketapp.features.auth.data.entities.SendSmsCodeEntity
import com.example.marketapp.features.auth.data.entities.ValidateEmailEntity
import com.example.marketapp.features.auth.data.entities.ValidatePhoneEntity
import com.example.marketapp.features.auth.data.entities.ValidateSmsCodeEntity
import com.example.marketapp.features.auth.infrastructure.api.AuthApi
import retrofit2.Response
import javax.inject.Inject

interface AuthRemoteDataSource {
    suspend fun login(
        username: String,
        password: String
    ): Response<LoginEntity>

    suspend fun register(
        username: String,
        email: String,
        password: String,
        mobile: String
    ): Response<RegisterEntity>

    suspend fun activateAccount(
        phoneNumber: String,
        pin: String,
        expectedPin: String
    ): Response<ActivateAccountEntity>

    suspend fun resetPasswordByEmail(
        email: String,
    ): Response<ResetPasswordByEmailEntity>

    suspend fun sendSmsCode(
        phone: String,
    ): Response<SendSmsCodeEntity>

    suspend fun validateSmsCode(
        phone: String,
        smsCode: String,
    ): Response<ValidateSmsCodeEntity>

    suspend fun resetPasswordByPhone(
        phone: String,
        smsCode: String,
        newPassword: String
    ): Response<ResetPasswordByPhoneEntity>

    suspend fun validateEmail(
        email: String,
    ): Response<ValidateEmailEntity>

    suspend fun validatePhone(
        phone: String,
    ): Response<ValidatePhoneEntity>


}

class AuthRemoteDataSourceImpl @Inject constructor(val api: AuthApi) : AuthRemoteDataSource {

    companion object {
        val DATA_SOURCE_ID = 0
    }

    override suspend fun login(
        username: String,
        password: String
    ): Response<LoginEntity> {

        try {
            val serviceData: String = ("[{" +
                    "\"uname\":\"$username\"," +
                    "\"upass\":\"$password\"," +
                    "\"ismob\":\"0\"" +
                    "}]").toBase64()

            return api.login(
                serviceCode = 10,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }

    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        phone: String
    ): Response<RegisterEntity> {

        try {

            val serviceData: String = ("[{\"id\":\"0\",\"atxt\":\"$username\",\"etxt\":\"$username\",\"uname\":\"$username\",\"umail\":\"$email\",\"upass\":\"$password\",\"mobile\":\"$phone\"}]").toBase64()

            return api.register(
                serviceCode = 20,
                folderId = 10,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun activateAccount(
        phoneNumber: String,
        pin: String,
        expectedPin: String
    ): Response<ActivateAccountEntity> {
        try {

            val serviceData: String = "[{" +
                    "\"mobile\":\"0\"," +
                    "\"mcode\":\"$expectedPin\"," +
                    "\"ncode\":\"$pin\"," +
                    "}]".toBase64()
            return api.activateAccount(
                serviceCode = 50,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun resetPasswordByEmail(
        email: String
    ): Response<ResetPasswordByEmailEntity> {
        try {

            val serviceData: String = "[{\"umail\":\"$email\"}]".toBase64()
            return api.resetPasswordByEmail(
                serviceCode = 90,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun sendSmsCode(
        phone: String
    ): Response<SendSmsCodeEntity> {
        try {

            val serviceData: String = ("[{" +
                    "\"mobile\":\"$phone\"" +
                    "\"is_code_sms\":\"0\"" +
                    "}]").toBase64()
            return api.sendSmsCode(
                serviceCode = 60,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun validateSmsCode(
        phone: String,
        smsCode: String
    ): Response<ValidateSmsCodeEntity> {
        try {

            val serviceData: String = ("[{" +
                    "\"mobile\":\"$phone\"" +
                    "\"mcode\":\"$smsCode\"" +
                    "}]").toBase64()
            return api.validateSmsCode(
                serviceCode = 70,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun resetPasswordByPhone(
        phone: String,
        smsCode: String,
        newPassword: String
    ): Response<ResetPasswordByPhoneEntity> {
        try {

            val serviceData: String = ("[{" +
                    "\"mobile\":\"$phone\"" +
                    "\"mcode\":\"$smsCode\"" +
                    "\"upass\":\"$newPassword\"" +
                    "}]").toBase64()
            return api.resetPasswordByPhone(
                serviceCode = 80,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun validateEmail(
        email: String
    ): Response<ValidateEmailEntity> {
        try {

            val serviceData: String = ("[{" +
                    "\"umail\":\"$email\"" +
                    "}]").toBase64()
            return api.validateEmail(
                serviceCode = 30,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }

    override suspend fun validatePhone(
        phone: String
    ): Response<ValidatePhoneEntity> {
        try {

            val serviceData: String = ("[{" +
                    "\"mobile\":\"$phone\"" +
                    "}]").toBase64()
            return api.validatePhone(
                serviceCode = 40,
                folderId = 0,
                userId = DEFAULT_USER_ID.toString(),
                username = DEFAULT_EMAIL,
                password = DEFAULT_PASSWORD,
                serviceData = serviceData,
            )

        } catch (e: Exception) {
            if (e is java.net.SocketTimeoutException) {
                throw RemoteDataException(R.string.internet_connection.toString())
            } else {
                throw RemoteDataException(e.message)
            }
        }
    }


}