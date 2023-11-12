package com.example.marketapp.features.wallet.view.viewmodel.wallet

import android.net.Uri

data class WalletState(
    var balance : Double = 0.0,
    var balanceIsLoading :Boolean = false,
    var balanceError : String? = null,


    var moneyTransferPhoto : Uri? = null,
    var chargeBalanceIsLoading :Boolean = false,
    var chargeBalanceError : String? = null,
)
