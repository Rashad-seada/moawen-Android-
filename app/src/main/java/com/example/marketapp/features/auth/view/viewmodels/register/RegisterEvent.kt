package com.example.marketapp.features.auth.view.viewmodels.register

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class RegisterEvent {

    data class Register(var navigator: DestinationsNavigator,var context: Context) : RegisterEvent()
    data class OnLoginClick(var navigator: DestinationsNavigator) : RegisterEvent()
    data class OnBackClick(var navigator: DestinationsNavigator) : RegisterEvent()
    data class OnValidateClick(var navigator: DestinationsNavigator,var context: Context) : RegisterEvent()
    data class OnResendClick(var navigator: DestinationsNavigator, var context: Context) : RegisterEvent()

}
