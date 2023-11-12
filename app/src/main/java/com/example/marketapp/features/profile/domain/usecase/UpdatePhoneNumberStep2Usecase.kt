package com.example.marketapp.features.profile.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.marketapp.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.marketapp.features.profile.data.repo.ProfileRepoImpl
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import javax.inject.Inject

class UpdatePhoneNumberStep2Usecase @Inject constructor(private val repo: ProfileRepoImpl) {

    suspend operator fun invoke(
        token: String,
        updatePhoneNumberStep2Request: UpdatePhoneNumberStep2Request,
        context: Context
    ): Resource<UpdatePhoneNumberStep2Response> {

        return repo.updatePhoneNumberStep2(
            token, updatePhoneNumberStep2Request, context
        )

    }

}