package com.example.marketapp.features.auth.view.viewmodels.reset_password

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class ResetPasswordMethodsEvent {
    class OnBackButtonClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()
    class OnResetWithEmailClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()
    class OnResetWithPhoneClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()
    class OnSendCodeToEmailClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()
    class OnSendCodeToPhoneClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()
}