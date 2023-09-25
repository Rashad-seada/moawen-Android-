package com.example.marketapp.features.auth.infrastructure.Api

import com.example.marketapp.core.errors.Failure
import com.example.marketapp.features.auth.data.entities.LoginEntity

data class LoginResposne(
    var loginEntity: LoginEntity? = null,
    var failure : Failure? = null,
)
