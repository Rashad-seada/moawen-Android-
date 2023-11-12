package com.example.marketapp.features.wallet.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.marketapp.features.wallet.data.entities.get_balance.GetBalanceResponse
import com.example.marketapp.features.wallet.data.repo.WalletRepoImpl
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(private val repo : WalletRepoImpl) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<GetBalanceResponse> {

        return repo.getBalance(
            token, context
        )

    }

}