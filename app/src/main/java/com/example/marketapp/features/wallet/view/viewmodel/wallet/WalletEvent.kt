package com.example.marketapp.features.wallet.view.viewmodel.wallet

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class WalletEvent {


    class OnBalanceRecharge(val navigator: DestinationsNavigator,val context: Context): WalletEvent()

    class OnGetBalance(val context: Context): WalletEvent()

}
