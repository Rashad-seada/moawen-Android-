package com.example.marketapp.features.profile.domain.repo

import android.content.Context
import android.net.Uri
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.profile.data.entities.update_phone_number.UpdatePhoneNumberResponse
import com.example.marketapp.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.marketapp.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberRequest
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import okhttp3.MultipartBody
import retrofit2.Response

interface ProfileRepo {

    suspend fun updateProfileNameAndImage(
        token: String,
        fullName: String?,
        imagePart: Uri?,
        context : Context
    ): Resource<UpdateProfileNameAndImageResponse>

    suspend fun updatePhoneNumber(
        token: String,
        updatePhoneNumberRequest: UpdatePhoneNumberRequest,
        context : Context

    ) : Resource<UpdatePhoneNumberResponse>

    suspend fun updatePhoneNumberStep2(
        token: String,
        updatePhoneNumberStep2Request: UpdatePhoneNumberStep2Request,
        context : Context
    ) : Resource<UpdatePhoneNumberStep2Response>

}