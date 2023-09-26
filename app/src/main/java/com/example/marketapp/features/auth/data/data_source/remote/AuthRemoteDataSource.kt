package com.example.marketapp.features.auth.data.data_source.remote

import com.example.marketapp.R
import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.core.util.Consts.DEFAULT_EMAIL
import com.example.marketapp.core.util.Consts.DEFAULT_PASSWORD
import com.example.marketapp.core.util.Consts.DEFAULT_USER_ID
import com.example.marketapp.core.util.base64_converters.toBase64
import com.example.marketapp.features.auth.data.entities.LoginEntity
import com.example.marketapp.features.auth.infrastructure.Api.AuthApi
import retrofit2.Response
import javax.inject.Inject

interface AuthRemoteDataSource {
    suspend fun login(username : String, password : String) : Response<LoginEntity>

}

class AuthRemoteDataSourceImpl @Inject constructor(val api: AuthApi): AuthRemoteDataSource {
    companion object {
        val DATA_SOURCE_ID = 0
    }

    private val serviceCode = 10
    private val folderId = 0

    override suspend fun login(username: String, password: String) : Response<LoginEntity> {

        try {
            val serviceData : String = "[{\"uname\":\"$username\",\"upass\":\"$password\",\"ismob\":\"0\"}]".toBase64()
            return  api.login(
                serviceCode = serviceCode,
                folderId = folderId,
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