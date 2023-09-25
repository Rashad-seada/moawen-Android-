package com.example.marketapp.features.auth.view.viewmodels.reset_password


data class ResetPasswordState (
    var email : String = "",
    var phone : String = "",
    var pinCode : String = "",
    var isSendingPinCode : Boolean = false,
    var isValidatingPinCode : Boolean = false,
)