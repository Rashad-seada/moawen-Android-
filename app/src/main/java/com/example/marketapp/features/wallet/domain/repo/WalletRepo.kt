package com.example.marketapp.features.wallet.domain.repo

import android.content.Context
import android.net.Uri
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.marketapp.features.wallet.data.entities.get_balance.GetBalanceResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface WalletRepo {

    suspend fun getBalance(
        token : String,
        context: Context
    ): Resource<GetBalanceResponse>

    suspend fun chargeBalance(
        token : String,
        image: Uri,
        context: Context
    ): Resource<ChargeBalanceResponse>

}