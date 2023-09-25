package com.example.marketapp.features.auth.view.viewmodels.register

data class RegisterState(
    var username : String = "",
    var email : String = "",
    var phone : String = "",
    var password : String = "",
    var passwordRenter : String = "",
    var isRegisterLoading : Boolean = false,
    var isPasswordSecure : Boolean = true,
    var isPasswordRenterSecure : Boolean = true

)
