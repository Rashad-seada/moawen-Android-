package com.example.marketapp.features.wallet.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import com.example.marketapp.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.marketapp.features.wallet.data.repo.WalletRepoImpl
import javax.inject.Inject

class ChargeBalanceUseCase @Inject constructor(private val repo : WalletRepoImpl){

    suspend operator fun invoke(
        token: String,
        image: Uri,
        context: Context
    ): Resource<ChargeBalanceResponse> {

        return repo.chargeBalance(
            token, image, context
        )

    }

}