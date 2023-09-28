package com.example.marketapp.features.auth.view.viewmodels.register

data class RegisterState(
    var username : String = "",
    var usernameError : String? = null,

    var email : String = "",
    var emailError : String? = null,

    var phone : String = "",
    var phoneWithCountryCode : String = "",
    var phoneError : String? = null,

    var password : String = "",
    var passwordError : String? = null,

    var passwordRenter : String = "",
    var passwordRenterError : String? = null,

    val isEmailValid: Boolean = false,
    val isValidatingEmail: Boolean = false,

    val isPhoneValid: Boolean = false,
    val isValidatingPhone: Boolean = false,

    var isRegisterLoading : Boolean = false,
    var isPasswordSecure : Boolean = true,
    var isPasswordRenterSecure : Boolean = true

)
