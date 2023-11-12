package com.example.marketapp.features.profile.data.data_source.remote

import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.features.profile.data.entities.update_phone_number.UpdatePhoneNumberResponse
import com.example.marketapp.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.marketapp.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.marketapp.features.profile.infrastructure.api.ProfileApi
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberRequest
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Part
import retrofit2.http.Query
import javax.inject.Inject

interface ProfileRemoteDataSource {

    suspend fun updateProfileNameAndImage(
        token : String,
        queries : Map<String, Any>,
    ): Response<UpdateProfileNameAndImageResponse>

    suspend fun updatePhoneNumber(
        token: String,
        updatePhoneNumberRequest: UpdatePhoneNumberRequest,
    ) : Response<UpdatePhoneNumberResponse>

    suspend fun updatePhoneNumberStep2(
        token: String,
        updatePhoneNumberStep2Request: UpdatePhoneNumberStep2Request,
    ) : Response<UpdatePhoneNumberStep2Response>

}

class ProfileRemoteDataSourceImpl @Inject constructor(private val profileApi: ProfileApi) : ProfileRemoteDataSource {


    override suspend fun updateProfileNameAndImage(
        token: String,
        queries : Map<String, Any>,
    ): Response<UpdateProfileNameAndImageResponse> {

        try {
            return profileApi.updateProfileNameAndImage(
                token,
                queries
            )
        } catch (e: Exception){
            throw RemoteDataException(e.message.toString())
        }

    }

    override suspend fun updatePhoneNumber(
        token: String,
        updatePhoneNumberRequest: UpdatePhoneNumberRequest
    ): Response<UpdatePhoneNumberResponse> {
        try {
            return profileApi.updatePhoneNumber(
                token, updatePhoneNumberRequest
            )
        } catch (e: Exception){
            throw RemoteDataException(e.message.toString())
        }

    }

    override suspend fun updatePhoneNumberStep2(
        token: String,
        updatePhoneNumberStep2Request: UpdatePhoneNumberStep2Request
    ): Response<UpdatePhoneNumberStep2Response> {
        try {
            return profileApi.updatePhoneNumberStep2(
                token,updatePhoneNumberStep2Request
            )
        } catch (e: Exception){
            throw RemoteDataException(e.message.toString())
        }

    }

}