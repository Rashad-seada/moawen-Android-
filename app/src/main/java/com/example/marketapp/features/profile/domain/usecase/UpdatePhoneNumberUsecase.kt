package com.example.marketapp.features.profile.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.profile.data.entities.update_phone_number.UpdatePhoneNumberResponse
import com.example.marketapp.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.marketapp.features.profile.data.repo.ProfileRepoImpl
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberRequest
import javax.inject.Inject

class UpdatePhoneNumberUsecase @Inject constructor(private val repo: ProfileRepoImpl) {

    suspend operator fun invoke(
        token: String,
        updatePhoneNumberRequest: UpdatePhoneNumberRequest,
        context: Context
    ): Resource<UpdatePhoneNumberResponse> {

        return repo.updatePhoneNumber(
            token, updatePhoneNumberRequest, context
        )

    }

}