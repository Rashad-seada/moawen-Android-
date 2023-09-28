package com.example.marketapp.features.auth.view.viewmodels.reset_password


data class ResetPasswordState (
    var email : String = "",
    var emailError : String? = null,

    var phone : String = "",
    var phoneError : String? = null,

    var pinCode : String = "",

    var isSendingPinCode : Boolean = false,
    var isSendingEmail : Boolean = false,

    var isValidatingPinCode : Boolean = false,
)