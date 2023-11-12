package com.example.marketapp.features.wallet.data.data_source.remote

import android.util.Log
import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.marketapp.features.wallet.data.entities.get_balance.GetBalanceResponse
import com.example.marketapp.features.wallet.infrastructure.api.WalletApi
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject

interface WalletRemoteDataSource {

    suspend fun getBalance(
        token : String,
    ): Response<GetBalanceResponse>

    suspend fun chargeBalance(
        token : String,
        image : MultipartBody.Part
    ): Response<ChargeBalanceResponse>

}

class WalletRemoteDataSourceImpl @Inject constructor(private val walletApi : WalletApi) : WalletRemoteDataSource {


    override suspend fun getBalance(token: String): Response<GetBalanceResponse> {
        try {
            return walletApi.getBalance(
                token,
            )
        } catch (e: Exception){
            throw RemoteDataException(e.message.toString())
        }
    }

    override suspend fun chargeBalance(
        token: String,
        image: MultipartBody.Part
    ): Response<ChargeBalanceResponse> {
        try {
            return walletApi.chargeBalance(
                token,
                image
            )
        } catch (e: Exception){
            throw RemoteDataException(e.message.toString())
        }
    }

}