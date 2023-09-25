package com.example.marketapp.features.auth.view.viewmodels.login

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class LoginEvent {
    data class Login(val navigator: DestinationsNavigator,val context: Context) : LoginEvent()
    object LoginWithGoogle : LoginEvent()
    data class ForgotPassword(val navigator: DestinationsNavigator) : LoginEvent()
    data class Register(val navigator: DestinationsNavigator) : LoginEvent()
    object RememberMe : LoginEvent()

    data class OnBackClick(val navigator: DestinationsNavigator) : LoginEvent()

}
