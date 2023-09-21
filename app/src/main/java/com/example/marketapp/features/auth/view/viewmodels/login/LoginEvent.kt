package com.example.marketapp.features.auth.view.viewmodels.login

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class LoginEvent {
    object Login : LoginEvent()
    object LoginWithGoogle : LoginEvent()
    data class ForgotPassword(val navigator: DestinationsNavigator) : LoginEvent()
    object Register : LoginEvent()
    object RememberMe : LoginEvent()



}
