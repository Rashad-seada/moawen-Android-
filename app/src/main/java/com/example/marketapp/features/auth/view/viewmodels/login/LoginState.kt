package com.example.marketapp.features.auth.view.viewmodels.login

data class LoginState(
    var rememberMe: Boolean = true,
    var isLoginLoading: Boolean = false,
    var isLoginWithGoogleLoading: Boolean = false,
    var isPasswordSecure: Boolean = true,

    var countryCode: String = "",

    var phone: String = "",
    var phoneError : String? = null,

    var password: String = "",
    val passwordError : String? = null
)
