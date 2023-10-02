package com.example.marketapp.features.auth.view.viewmodels.register

data class RegisterState(



    var countryCode : String = "",

    var phone : String = "",
    var phoneError : String? = null,

    var fullName : String = "",
    var fullNameError : String? = null,

    var password : String = "",
    var passwordError : String? = null,

    var passwordRenter : String = "",
    var passwordRenterError : String? = null,

    var terms : Boolean = false,


    var pinCode : String = "",
    var isResendingPinCode : Boolean = false,
    var isValidatingPinCode : Boolean = false,

    val isPhoneValid: Boolean = false,
    var isRegisterLoading : Boolean = false,
    var isPasswordSecure : Boolean = true,
    var isPasswordRenterSecure : Boolean = true,



)
