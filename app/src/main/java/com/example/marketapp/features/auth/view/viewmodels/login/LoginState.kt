package com.example.marketapp.features.auth.view.viewmodels.login

data class LoginState(
    var rememberMe: Boolean = true,
    var isLoginLoading: Boolean = false,
    var isLoginWithGoogleLoading: Boolean = false,
    var isPasswordSecure: Boolean = true,

    var username: String = "",
    var usernameError : String? = null,

    var password: String = "",
    val passwordError : String? = null
)
