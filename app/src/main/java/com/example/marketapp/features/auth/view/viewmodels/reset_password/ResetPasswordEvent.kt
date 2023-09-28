package com.example.marketapp.features.auth.view.viewmodels.reset_password

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class ResetPasswordMethodsEvent {
    class OnBackButtonClick(val navigator: DestinationsNavigator?) : ResetPasswordMethodsEvent()
    class OnResetWithEmailClick(val navigator: DestinationsNavigator?) : ResetPasswordMethodsEvent()
    class OnResetWithPhoneClick(val navigator: DestinationsNavigator?) : ResetPasswordMethodsEvent()
    class OnSendCodeToEmailClick(val navigator: DestinationsNavigator?,val context: Context) : ResetPasswordMethodsEvent()
    class OnSendCodeToPhoneClick(val navigator: DestinationsNavigator?,val context: Context) : ResetPasswordMethodsEvent()

    class OnValidateClick(val navigator: DestinationsNavigator?) : ResetPasswordMethodsEvent()
    class OnDoneMessageScreenClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()

    object OnResendClickClick : ResetPasswordMethodsEvent()


}