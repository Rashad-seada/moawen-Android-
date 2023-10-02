package com.example.marketapp.features.auth.view.viewmodels.reset_password


data class ResetPasswordState (
    var email : String = "",
    var emailError : String? = null,

    var countryCode : String = "",

    var phone : String = "",
    var phoneError : String? = null,

    var pinCode : String = "",

    var newPassword : String = "",
    var newPasswordError : String? = null,

    var newPasswordRepeated : String = "",
    var newPasswordRepeatedError : String? = null,

    var isSendingPinCode : Boolean = false,
    var isSendingEmail : Boolean = false,
    var isValidatingPinCode : Boolean = false,
    var isResettingPassword : Boolean = false,

    )