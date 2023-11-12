package com.example.marketapp.features.profile.infrastructure.api

import com.example.marketapp.features.profile.data.entities.update_phone_number.UpdatePhoneNumberResponse
import com.example.marketapp.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.marketapp.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberRequest
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileApi  {

    @Multipart
    @JvmSuppressWildcards
    @Headers("Content-Type: multipart/form-data")
    @POST("api/update-profile")
    suspend fun updateProfileNameAndImage(
        @Header("Authorization") token : String,
        @PartMap queries : Map<String, Any>,
    ): Response<UpdateProfileNameAndImageResponse>


    @Headers("Content-Type: application/json")
    @POST("api/send-otp-to-check-phone")
    suspend fun updatePhoneNumber(
        @Header("Authorization") token : String,
        @Body updatePhoneNumberRequest : UpdatePhoneNumberRequest,
    ): Response<UpdatePhoneNumberResponse>


    @Headers("Content-Type: application/json")
    @POST("api/update-phone")
    suspend fun updatePhoneNumberStep2(
        @Header("Authorization") token : String,
        @Body updatePhoneNumberRequest : UpdatePhoneNumberStep2Request,
    ): Response<UpdatePhoneNumberStep2Response>

}