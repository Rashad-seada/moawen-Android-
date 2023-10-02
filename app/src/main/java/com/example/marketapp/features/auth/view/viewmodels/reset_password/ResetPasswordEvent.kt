package com.example.marketapp.features.auth.view.viewmodels.reset_password

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class ResetPasswordMethodsEvent {
    class OnBackButtonClick(val navigator: DestinationsNavigator?) : ResetPasswordMethodsEvent()
    class OnResetWithPhoneClick(val navigator: DestinationsNavigator?) : ResetPasswordMethodsEvent()
    class OnSendCodeToPhoneClick(val navigator: DestinationsNavigator?,val context: Context) : ResetPasswordMethodsEvent()
    class OnValidateClick(val navigator: DestinationsNavigator?,val context: Context) : ResetPasswordMethodsEvent()
    class OnSettingNewPasswordClick(val navigator: DestinationsNavigator?,val context: Context) : ResetPasswordMethodsEvent()
    class OnDoneMessageScreenClick(val navigator: DestinationsNavigator) : ResetPasswordMethodsEvent()
    class OnResendClickClick(val navigator: DestinationsNavigator?,val context: Context) : ResetPasswordMethodsEvent()


}